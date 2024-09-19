package com.two_ddang.logistics.hub.domain.vo;

import com.two_ddang.logistics.hub.domain.model.Hub;
import com.two_ddang.logistics.hub.domain.model.HubProduct;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class HubProductVO implements Serializable {

    private final UUID id;
    private final String productName;
    private final UUID productId;
    private final UUID companyId;
    private final int stock;
    private final UUID hubId;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Integer createdBy;
    private final Integer updatedBy;
    private final Integer deletedBy;

    private final boolean isDeleted;

    public static HubProductVO fromEntity(HubProduct hubProduct) {

        hubProduct = Objects.isNull(hubProduct) ? HubProduct.emptyObject() : hubProduct;


        Hub hub = Objects.isNull(hubProduct.getHub()) ? Hub.emptyObject() : hubProduct.getHub();
        return new HubProductVO(hubProduct.getId(), hubProduct.getProductName(), hubProduct.getProductId(), hubProduct.getCompanyId(), hubProduct.getStock(), hub.getId(),
                hubProduct.getCreatedAt(), hubProduct.getUpdatedAt(), hubProduct.getDeletedAt(), hubProduct.getCreatedBy(), hubProduct.getUpdatedBy(), hubProduct.getDeletedBy(), hubProduct.isDeleted());

    }

}
