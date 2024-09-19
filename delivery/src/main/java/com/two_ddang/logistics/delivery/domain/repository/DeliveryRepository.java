package com.two_ddang.logistics.delivery.domain.repository;

import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.delivery.domain.model.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

    Optional<Delivery> findByIdAndIsDeletedIsFalse(UUID deliveryId);

    Page<Delivery> findByDeliveryStatus(DeliveryStatus status, Pageable pageable);

    List<Delivery> findByDepartmentHubIdAndDeliveryStatus(UUID departmentHubId, DeliveryStatus status);


}
