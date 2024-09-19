package com.two_ddang.logistics.order.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HubRes {

    private UUID hubId;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private LocalDateTime createdAt;
}
