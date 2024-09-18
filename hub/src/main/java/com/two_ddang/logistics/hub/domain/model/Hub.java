package com.two_ddang.logistics.hub.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import com.two_ddang.logistics.hub.domain.vo.HubAgentVO;
import com.two_ddang.logistics.hub.domain.vo.HubProductVO;
import com.two_ddang.logistics.hub.domain.vo.HubRouteVO;
import com.two_ddang.logistics.hub.domain.vo.HubVO;
import com.two_ddang.logistics.hub.infrastructrure.exception.NoSuchElementApplicationException;
import com.two_ddang.logistics.hub.infrastructrure.exception.NotEnoughQuantityApplicationException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity(name = "p_hubs")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("허브")
public class Hub extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Comment("허브 ID")
    private UUID id;

    @Column(nullable = false)
    @Comment("허브 명")
    private String name;

    @Column(nullable = false)
    @Comment("허브 주소")
    private String address;

    @Column(nullable = false)
    @Comment("위도")
    private String latitude;

    @Column(nullable = false)
    @Comment("경도")
    private String longitude;

    @OneToMany(mappedBy = "hub", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HubAgent> hubAgents = new ArrayList<>();

    @OneToMany(mappedBy = "hub", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HubProduct> hubProducts = new ArrayList<>();

    @OneToMany(mappedBy = "arriveHub", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HubRoute> arriveRoutes = new ArrayList<>();

    @OneToMany(mappedBy = "departmentHub", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HubRoute> departmentRoutes = new ArrayList<>();

    private Hub(String name, String address, String latitude, String longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Hub of(String name, String address, String latitude, String longitude, User manager) {

        Hub hub = new Hub(name, address, latitude, longitude);

        HubAgent hubAgent = HubAgent.of(manager, hub);

        hub.hubAgents.add(hubAgent);

        return hub;
    }

    public void migration(String address, String latitude, String longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void changeName(String name) {
        this.name = name;

    }

    public static Hub emptyObject() {
        return new Hub();
    }

    public HubProduct inbound(UUID productId, UUID companyId, String productName, int quantity) {

        HubProduct result;

        Optional<HubProduct> first = this.hubProducts.stream()
                .filter(p ->
                        (p.getProductId().equals(productId)
                                && p.getCompanyId().equals(companyId))
                )
                .findFirst();

        if(first.isPresent()) {
            first.ifPresent(p -> p.inbound(quantity));
            result = first.get();
        }
        else {
            HubProduct hubProduct = HubProduct.of(productName, productId, companyId, quantity, this);
            hubProducts.add(hubProduct);
            result = hubProduct;

        }

        return result;

    }

    public HubProduct findHubProductById(UUID productId) {
        return hubProducts.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst().orElseThrow(NoSuchElementApplicationException::new);
    }

    public HubProduct outbound(UUID productId, UUID companyId, int quantity) {

        HubProduct hubProduct = findHubProductById(productId);

        if(hubProduct.isEnoughStock(quantity)) {
            hubProduct.outbound(quantity);
            return hubProduct;
        }
        else {
            throw new NotEnoughQuantityApplicationException();
        }
    }

    public Optional<HubRoute> findRouteToHub(UUID arriveHubId) {

        return arriveRoutes.stream()
                .filter(p -> p.getArriveHub().getId().equals(arriveHubId))
                .findFirst();

    }


    public HubRoute updateRouteBetweenRoutes(Hub arriveHub, String route, int takeTime) {

        Optional<HubRoute> hubRoute = arriveRoutes.stream()
                .filter(p -> p.getArriveHub().getId().equals(arriveHub))
                .findFirst();

        HubRoute result;

        if(hubRoute.isPresent()) {

            HubRoute item = hubRoute.get();

            if(item.getTakeTime() > takeTime) {
                item.modifyRoute(route, takeTime);
                arriveRoutes.add(item);
            }
            result = item;
        }
        else {
            HubRoute item = HubRoute.of(address, this, arriveHub.getAddress(), arriveHub, takeTime, route);
            arriveRoutes.add(item);
            result = item;
        }

        return result;
    }
}
