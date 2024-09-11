package com.two_ddang.logistics.company.domain.model.product;

import com.two_ddang.logistics.company.presentation.dtos.product.CreateProductRequest;
import com.two_ddang.logistics.company.presentation.dtos.product.RollbackStockRequest;
import com.two_ddang.logistics.company.presentation.dtos.product.UpdateProductInfoRequest;
import com.two_ddang.logistics.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "p_products")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private UUID companyId;
    private UUID hubId;
    private String productName;
    private Long price;
    private String description;
    private Boolean isSoldOut;


    public static Product create(CreateProductRequest createProductRequest) {
        return Product.builder()
                .companyId(createProductRequest.getCompanyId())
                .hubId(createProductRequest.getHubId())
                .productName(createProductRequest.getProductName())
                .description(createProductRequest.getDescription())
                .price(createProductRequest.getPrice())
                .isSoldOut(false)
                .build();
    }

    public void updateInfo(UpdateProductInfoRequest updateProductInfoRequest) {
        this.productName = updateProductInfoRequest.getProductName();
        this.description = updateProductInfoRequest.getDescription();
        this.price = updateProductInfoRequest.getPrice();
        this.isSoldOut = updateProductInfoRequest.getIsSoldOut();
    }

    public void rollbackStock(RollbackStockRequest rollbackStockRequest) {
//        this.stock += rollbackStockRequest.getRollbackQuantity();
    }
}
