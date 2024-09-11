package com.two_ddang.logistics.core.entity;

import lombok.Getter;

@Getter
public enum DeliveryStatus {

    WAITING,
    ARRIVE_HUB,
    DELIVERING,
    CANCELED,
    DELIVERED,
    ;

}
