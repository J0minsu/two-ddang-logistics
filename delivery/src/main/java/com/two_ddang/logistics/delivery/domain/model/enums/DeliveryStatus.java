package com.two_ddang.logistics.delivery.domain.model.enums;

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
