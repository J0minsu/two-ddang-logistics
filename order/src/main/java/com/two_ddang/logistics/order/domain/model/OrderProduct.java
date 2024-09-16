package com.two_ddang.logistics.order.domain.model;


import com.two_ddang.logistics.order.infrastructure.dtos.CompanyProductResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "p_order_products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "order_id")
    private Order order;

    @Column(nullable = false)
    private UUID productId;


    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private Integer quantity;

    public static OrderProduct create(CompanyProductResponse product, Integer quantity) {
        return OrderProduct.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
    }

    public Long sumPrice() {
        return price * quantity;
    }


    public void addOrder(Order order) {
        this.order = order;
    }
}
