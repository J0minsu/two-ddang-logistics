package com.two_ddang.logistics.order.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import com.two_ddang.logistics.order.presentation.dtos.CreateOrderRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "p_orders")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@SQLRestriction("is_deleted = false")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID reqCompanyId;

    @Column(nullable = false)
    private UUID resCompanyId;

    private UUID deliveryId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    private Long totalPrice;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;


    public static Order create(CreateOrderRequest createOrderRequest, List<OrderProduct> orderProducts) {
        return Order.builder()
                .reqCompanyId(createOrderRequest.getReqCompanyId())
                .resCompanyId(createOrderRequest.getResCompanyId())
                .orderStatus(OrderStatus.CREATED)
                .orderProducts(orderProducts)
                .totalPrice(orderProducts.stream().mapToLong(OrderProduct::sumPrice).sum())
                .build();
    }

    public void addDeliveryId(UUID deliveryId) {
        this.deliveryId = deliveryId;
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void cancel() {
        this.orderStatus = OrderStatus.CANCELLED;
    }
}
