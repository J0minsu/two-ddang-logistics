package com.two_ddang.logistics.delivery.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import com.two_ddang.logistics.core.entity.TransitStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "p_transit_route")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"transit", "delivery"})
@Comment("")
public class TransitRoute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_id"
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "transit_id"
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Transit transit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transit_agent_id"
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private DeliveryAgent transitAgent;

    @Column(nullable = false)
    @Comment("운송 순서")
    private int sequence;

    @Column(nullable = false)
    @Comment("운송 상태")
    private TransitStatus transitStatus;
    
    @Column(nullable = false)
    @Comment("출발 허브 ID")
    private UUID departmentHubId;
    
    @Column(nullable = false)
    @Comment("도착 허브 ID")
    private UUID arriveHubId;
    
    @Column(nullable = false)
    @Comment("경로")
    private String route;
    
    @Column(nullable = false)
    @Comment("예상 운송 거리(m)")
    private int estimateDistance;

    @Column(nullable = false)
    @Comment("예상 소요 시간(분)")
    private int estimateTime;

    @Column(nullable = false)
    @Comment("실제 운송 거리(m)")
    private int actualDistance;

    @Column(nullable = false)
    @Comment("실제 소요 시간(분)")
    private int actualTime;

    @Column
    @Comment("출발 시간")
    private LocalDateTime departmentAt;

    @Column
    @Comment("도착 시간")
    private LocalDateTime arriveAt;

    protected TransitRoute(
            Delivery delivery, Transit transit, DeliveryAgent transitAgent, int sequence, TransitStatus transitStatus,
            UUID arriveHubId, String route, UUID departmentHubId, int estimateDistance, int estimateTime) {
        this.delivery = delivery;
        this.transit = transit;
        this.transitAgent = transitAgent;
        this.sequence = sequence;
        this.transitStatus = transitStatus;
        this.arriveHubId = arriveHubId;
        this.route = route;
        this.departmentHubId = departmentHubId;
        this.estimateDistance = estimateDistance;
        this.estimateTime = estimateTime;
    }

    public static TransitRoute of(
            Delivery delivery, Transit transit, DeliveryAgent transitAgent, int sequence, TransitStatus transitStatus,
                        UUID arriveHubId, String route, UUID departmentHubId, int estimateDistance, int estimateTime) {

        return new TransitRoute(delivery, transit, transitAgent, sequence, TransitStatus.WAIT,
                arriveHubId, route, departmentHubId, estimateDistance, estimateTime);

    }

    public static TransitRoute empty() {
        return new TransitRoute();
    }

    public void startTransit() {
        this.transitStatus = TransitStatus.TRANSITING;
        this.departmentAt = LocalDateTime.now();
    }

    public void arriveTransit(int actualDistance, int actualTime) {
        this.transitStatus = TransitStatus.FINISH;
        this.delivery.arriveHub();
        this.actualDistance = actualDistance;
        this.actualTime = actualTime;
        this.transit.arriveHub(arriveHubId);
        this.arriveAt = LocalDateTime.now();
    }

}
