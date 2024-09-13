package com.two_ddang.logistics.delivery.application.service;

import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.core.entity.DriveStatus;
import com.two_ddang.logistics.delivery.domain.model.Delivery;
import com.two_ddang.logistics.delivery.domain.model.DeliveryAgent;
import com.two_ddang.logistics.delivery.domain.repository.DeliveryAgentRepository;
import com.two_ddang.logistics.delivery.domain.repository.DeliveryRepository;
import com.two_ddang.logistics.delivery.domain.vo.DeliveryVO;
import com.two_ddang.logistics.delivery.infrastructrure.exception.AlreadyWorkOutApplicationException;
import com.two_ddang.logistics.delivery.infrastructrure.exception.NoSuchElementApplicationException;
import com.two_ddang.logistics.delivery.presentation.request.DeliveryCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryAgentRepository deliveryAgentRepository;

    @Transactional
    public DeliveryVO createDelivery(DeliveryCreateRequest request) {

        Delivery delivery = Delivery.of(request.getOrderId(), request.getDepartHubId(), request.getArrivedHubId()
                , request.getDeliveryAddress(), request.getReceiveCompanyId());

        Delivery savedDelivery = deliveryRepository.save(delivery);

        return DeliveryVO.fromEntity(savedDelivery);

    }

    public DeliveryVO findById(UUID deliveryId) {

        Delivery delivery = deliveryRepository.findByIdAndIsDeletedIsFalse(deliveryId)
                .orElseThrow(NoSuchElementApplicationException::new);

        return DeliveryVO.fromEntity(delivery);

    }

    public Page<DeliveryVO> search(DeliveryStatus status, PageRequest page) {


        Page<Delivery> deliveries = deliveryRepository.findByDeliveryStatus(status, page);

        return deliveries.map(DeliveryVO::fromEntity);

    }

    @Transactional
    public DeliveryVO startDelivery(Integer userId, UUID deliveryId) {

        DeliveryAgent deliveryAgent = deliveryAgentRepository.findByUserIdAndIsDeletedIsFalse(userId)
                                    .orElseThrow(NoSuchElementApplicationException::new);

        Delivery delivery = deliveryRepository.findByIdAndIsDeletedIsFalse(deliveryId)
                .orElseThrow(NoSuchElementApplicationException::new);

        if(deliveryAgent.getDriveStatus() == DriveStatus.NOT_WORKING) {
            throw new AlreadyWorkOutApplicationException();
        }

        delivery.startDelivery(deliveryAgent);

        return DeliveryVO.fromEntity(delivery);

    }

    @Transactional
    public DeliveryVO finishDelivery(UUID deliveryId) {

        Delivery delivery = deliveryRepository.findByIdAndIsDeletedIsFalse(deliveryId)
                .orElseThrow(NoSuchElementApplicationException::new);


        delivery.finishDelivery();

        return DeliveryVO.fromEntity(delivery);

    }

    @Transactional
    public void softDelete(UUID deliveryId) {

        /**
         * TODO Auditing
         */
        Integer userId = 1;

        Delivery delivery = deliveryRepository.findByIdAndIsDeletedIsFalse(deliveryId)
                .orElseThrow(NoSuchElementApplicationException::new);

        delivery.delete(userId);

    }
}
