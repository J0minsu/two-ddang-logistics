package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.infrastructure.dtos.DeliveryCreateRequest;
import com.two_ddang.logistics.order.infrastructure.dtos.DeliveryRes;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryService {

    ResponseDTO<DeliveryRes> create(DeliveryCreateRequest request);
}