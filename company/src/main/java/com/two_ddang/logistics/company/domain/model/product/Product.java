package com.two_ddang.logistics.company.domain.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "p_products")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;


    private UUID companyId;
    private UUID hubId;
    private String productName;
    private Long price;
    private String description;
    private Boolean isSoldOut;

}
