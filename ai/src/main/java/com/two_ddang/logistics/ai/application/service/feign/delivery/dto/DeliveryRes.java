package com.two_ddang.logistics.ai.application.service.feign.delivery.dto;

import java.util.UUID;

public record DeliveryRes(

        Integer useId,
        String address,
        UUID slackID
) {

}
