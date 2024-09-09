package com.two_ddang.logistics.delivery.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
public enum TransitSortStandard {

    CREATED_DESC(Sort.by(Sort.Direction.DESC, "createdAt")),
    CREATED_ASC(Sort.by(Sort.Direction.ASC, "createdAt")),
    UPDATED_DESC(Sort.by(Sort.Direction.DESC, "updatedAt")),
    UPDATED_ASC(Sort.by(Sort.Direction.ASC, "updatedAt")),
    ;

    private final Sort sort;

}