package com.two_ddang.logistics.hub.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import com.two_ddang.logistics.hub.domain.vo.HubRouteVO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Entity(name = "p_hub_routes")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"departmentHub", "arriveHub"})
@Comment("")
public class HubRoute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Comment("출발 주소")
    private String departmentAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "depart_hub_id", nullable = false
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Hub departmentHub;

    @Column(nullable = false)
    @Comment("도착 주소")
    private String arriveAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "arrive_hub_id", nullable = false
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Hub arriveHub;

    @Column(nullable = false)
    @Comment("소요 시간")
    private int takeTime;
    
    @Column(nullable = false)
    @Comment("경로")
    private String route;

    private HubRoute(String departmentAddress, Hub departmentHub, String arriveAddress, Hub arriveHub, int takeTime, String route) {
        this.departmentAddress = departmentAddress;
        this.departmentHub = departmentHub;
        this.arriveAddress = arriveAddress;
        this.arriveHub = arriveHub;
        this.takeTime = takeTime;
        this.route = route;
    }

    public static HubRoute emptyObject() {
        return new HubRoute();
    }

    public static HubRoute of(String departmentAddress, Hub departmentHub, String arriveAddress, Hub arriveHub, int takeTime, String route) {
        return new HubRoute(departmentAddress, departmentHub, arriveAddress, arriveHub, takeTime, route);
    }

    public HubRouteVO toVO() {

        return new HubRouteVO(id, departmentAddress, departmentHub.toVO(), arriveAddress, arriveHub.toVO(), takeTime, route,
            getCreatedAt(), getUpdatedAt(), getDeletedAt(), getCreatedBy(), getUpdatedBy(), getDeletedBy(), isDeleted());

    }
}
