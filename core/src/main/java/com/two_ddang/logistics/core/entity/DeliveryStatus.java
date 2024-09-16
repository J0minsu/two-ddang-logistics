package com.two_ddang.logistics.core.entity;

import lombok.Getter;

@Getter
public enum DeliveryStatus {

    BEFORE_TRANSIT,
    TRANSITING,
    ARRIVE_HUB,
    WAITING,
    DELIVERING,
    CANCELED,
    DELIVERED,
    ;

}
