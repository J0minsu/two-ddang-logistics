package com.two_ddang.logistics.ai.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_slacks")
public class SlackEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "receiver_id")
    private UUID receiverID;

    @Column
    private String message;

    @Column(name = "send_time")
    private LocalDateTime sendTime;

    public SlackEntity(String message, LocalDateTime sendTime) {
        this.message = message;
        this.sendTime = sendTime;
    }





}
