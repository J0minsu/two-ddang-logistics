package com.two_ddang.logistics.company.infrastrucuture.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HubInfo {

    private UUID hubId;
    private String name;
}
