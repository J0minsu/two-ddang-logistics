package com.two_ddang.logistics.delivery.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.res.TransitRouteResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "p_transits")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "transitAgent")
@Comment("")
public class Transit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "transit_agent_id", nullable = false
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private DeliveryAgent transitAgent;
    
    @Column(nullable = false)
    @Comment("총 예상 거리(m)")
    private int totalEstimateDistance;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("운송 상태")
    private TransitStatus transitStatus;
    
    @Column(nullable = false)
    @Comment("총 경유지 수")
    private int totalStopCount = 0;

    @Column(nullable = false)
    @Comment("도착 경유지 수")
    private int arrivedStopCount = 0;

    @OneToMany(mappedBy = "transit", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransitRoute> transitRoutes = new ArrayList<>();

    public Transit(DeliveryAgent transitAgent, int totalEstimateDistance, TransitStatus transitStatus) {
        this.transitAgent = transitAgent;
        this.totalEstimateDistance = totalEstimateDistance;
        this.transitStatus = transitStatus;
    }

    public static Transit empty() {
        return new Transit();
    }

    public static Transit of(DeliveryAgent transitAgent, int totalEstimateDistance, TransitStatus transitStatus, List<Delivery> deliveries, Map<Integer, TransitRouteResponse> routes) {

        Transit transit = new Transit(transitAgent, totalEstimateDistance, TransitStatus.WAIT);

        Map<UUID, TransitRouteResponse> routeMap = routes.values()
                .stream()
                .collect(Collectors.toMap(TransitRouteResponse::getArriveHubId, t -> t));

        List<TransitRoute> routeList =  deliveries.stream().map(d -> {

            TransitRouteResponse arriveInfo = routeMap.get(d.getArriveHubId());

            return TransitRoute.of(
                    d, transit, transitAgent, arriveInfo.getSequence(), transitStatus,
                    arriveInfo.getArriveHubId(), arriveInfo.getRoute(), d.getDepartmentHubId(),
                    arriveInfo.getEstimateDistance(), arriveInfo.getEstimateTime());
        }
        ).toList();

        transit.getTransitRoutes().addAll(routeList);

        return transit;

    }

    public void arriveHub(UUID arrivedHubId) {

        arrivedStopCount++;

        if(arrivedStopCount >= totalStopCount) {
            this.transitStatus = TransitStatus.FINISH;
            this.transitAgent.finishTransit(arrivedHubId);
        }
        else {
            this.transitAgent.stayHub(arrivedHubId);
        }

    }

    public List<TransitRoute> getSpecificSequence(int sequence) {

        return transitRoutes.stream().filter(i -> i.getSequence() == sequence).toList();

    }

    public Optional<TransitRoute> getSpecificRouteId(UUID routeId) {

        return transitRoutes.stream().filter(i -> i.getId().equals(routeId)).findFirst();

    }

    public void startTransitSequence(int sequence) {
        transitRoutes.stream()
                .filter(i -> i.getSequence() == sequence)
                .forEach(TransitRoute::startTransit);


    }
}
