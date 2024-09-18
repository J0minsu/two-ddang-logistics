package com.two_ddang.logistics.ai.application.service.feign.delivery;

import com.two_ddang.logistics.ai.application.service.feign.delivery.dto.DeliveryRes;
import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.core.entity.DriverAgentType;

import java.util.List;

public interface DeliveryService {

    List<DeliveryRes> getTransitAddressAndSlackId(DriverAgentType driverAgentType,
                                                  DeliveryStatus deliveryStatus);

}
