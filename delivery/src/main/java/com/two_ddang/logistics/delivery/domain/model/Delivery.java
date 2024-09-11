package com.two_ddang.logistics.delivery.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import com.two_ddang.logistics.core.entity.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Entity(name = "p_deliveries")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "")
@Comment("배송")
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    
    @Column(nullable = false)
    @Comment("주문 ID")
    private UUID orderId;
    
    @Column(nullable = false)
    @Comment("배송 상태")
    private DeliveryStatus deliveryStatus;

    @Column(nullable = false)
    @Comment("출고 허브 ID")
    private UUID departmentHubId;

    @Column(nullable = false)
    @Comment("도착 허브 ID")
    private UUID arriveHubId;

    @Column(nullable = false)
    @Comment("배송지 주소")
    private String deliveryAddress;

    @Column(nullable = false)
    @Comment("수령 업체 ID")
    private UUID receiveCompanyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delvery_agent_id")
    private DeliveryAgent deliveryAgent;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private TransitRoute transitRoute;

    public void assignmentAgent(DeliveryAgent deliveryAgent) {
        this.deliveryAgent = deliveryAgent;
    }

    private Delivery(UUID orderId, DeliveryStatus deliveryStatus, UUID departmentHubId, UUID arriveHubId, String deliveryAddress, UUID receiveCompanyId) {
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
        this.departmentHubId = departmentHubId;
        this.arriveHubId = arriveHubId;
        this.deliveryAddress = deliveryAddress;
        this.receiveCompanyId = receiveCompanyId;
    }

    public static Delivery of(UUID orderId, UUID departmentHubId, UUID arriveHubId, String deliveryAddress, UUID receiveCompanyId) {
        return new Delivery(orderId, DeliveryStatus.WAITING, departmentHubId, arriveHubId, deliveryAddress, receiveCompanyId);
    }

    public void startDelivery() {
        this.deliveryStatus = DeliveryStatus.DELIVERING;
        this.deliveryAgent.startDelivery();
    }

    public void finishDelivery() {
        this.deliveryStatus = DeliveryStatus.DELIVERED;
        this.deliveryAgent.finishDelivery();
    }

    public void arriveHub() {
        this.deliveryStatus = DeliveryStatus.ARRIVE_HUB;
    }

    public void cancel(String reason) {
        this.deliveryStatus = DeliveryStatus.CANCELED;
    }

}
