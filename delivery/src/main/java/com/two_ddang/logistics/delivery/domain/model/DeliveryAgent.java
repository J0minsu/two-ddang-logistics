package com.two_ddang.logistics.delivery.domain.model;

import com.two_ddang.logistics.delivery.domain.model.enums.AgentType;
import com.two_ddang.logistics.delivery.domain.model.enums.DriveStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Entity(name = "p_delivery_agents")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "delivery")
@Comment("베(운)송 담당자 테이블")
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Comment("사용자 ID")
    private Integer userId;

    @Column
    @Comment("소속 허브 ID")
    private UUID registeredHubId;

    @Column(nullable = false)
    @Comment("배송 담당자 타입")
    private AgentType type;

    @Column(nullable = false)
    @Comment("슬랙 ID")
    private UUID slackId;

    @Column(nullable = false)
    @Comment("현재 운행 상태")
    private DriveStatus driveStatus;

    @Column
    @Comment("현재 정차 허브 ID")
    private UUID stayHubId;

    private DeliveryAgent(Integer userId, UUID registeredHubId, AgentType type, UUID slackId, DriveStatus driveStatus, UUID stayHubId) {
        this.userId = userId;
        this.registeredHubId = registeredHubId;
        this.type = type;
        this.slackId = slackId;
        this.driveStatus = driveStatus;
        this.stayHubId = stayHubId;
    }

    /**
     *
     * @param userId
     * @param registeredHubId
     * @param slackId
     * @desciption 배송 담당자 생성
     */
    public static DeliveryAgent ofDeliveryAgent(Integer userId, UUID registeredHubId, UUID slackId) {
        return new DeliveryAgent(userId, registeredHubId, AgentType.DELIVERY, slackId, DriveStatus.WAITING, null);
    }

    /**
     *
     * @param userId
     * @param slackId
     * @param stayHubId
     * @desciption 운송 담당자 생성
     */
    public static DeliveryAgent ofTransitAgent(Integer userId, UUID slackId, UUID stayHubId) {
        return new DeliveryAgent(userId, null, AgentType.TRANSIT, slackId, DriveStatus.WAITING, stayHubId);
    }

    /**
     * @description 배송 시작
     */
    public void startDelivery() {
        this.driveStatus = DriveStatus.DRIVING;
    }

    /**
     * @description 배송 종료
     */
    public void finishDelivery() {
        this.driveStatus = DriveStatus.WAITING;
    }

    /**
     *
     * @description 허브 정차
     */
    public void stayHub(UUID stayHubId) {
        this.stayHubId = stayHubId;
    }

    /**
     * @description 운송 시작
     */
    public void startTransit() {
        this.driveStatus = DriveStatus.DRIVING;
    }

    /**
     * @description 운송 종료
     */
    public void finishTransit(UUID stayHubId) {
        this.driveStatus = DriveStatus.WAITING;
        this.stayHubId = stayHubId;
    }

    /**
     * @descrption 퇴근
     */
    public void leaveWork() {
        this.driveStatus = DriveStatus.NOT_WORKING;
    }

    /**
     * @descrption 출근
     */
    public void goToWork() {
        this.driveStatus = DriveStatus.WAITING;
    }

}
