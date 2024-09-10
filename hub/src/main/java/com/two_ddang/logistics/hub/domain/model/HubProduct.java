package com.two_ddang.logistics.hub.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Entity(name = "p_hub_products")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "hub")
@Comment("허브 상품")
public class HubProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Comment("상품 명")
    private String productName;

    @Column(nullable = false)
    @Comment("상품 ID")
    private UUID productId;

    @Column(nullable = false)
    @Comment("업체 ID")
    private UUID companyId;

    @Column(nullable = false)
    @Comment("재고")
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false
            , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Hub hub;

    private HubProduct(String productName, UUID productId, UUID companyId, int stock, Hub hub) {
        this.productName = productName;
        this.productId = productId;
        this.companyId = companyId;
        this.stock = stock;
        this.hub = hub;
    }

    public static HubProduct of(String productName, UUID productId, UUID companyId, int stock, Hub hub) {
        return new HubProduct(productName, productId, companyId, stock, hub);
    }

    public boolean isEnoughStock(int quantity) {
        return 0 > stock - quantity;
    }

    public void inbound(int quantity) {
        stock += quantity;
    }

    public void outbound(int quantity) {
        stock -= quantity;
    }

}
