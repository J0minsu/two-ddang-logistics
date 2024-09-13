package com.two_ddang.logistics.delivery.domain.repository;

import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.delivery.domain.model.Delivery;
import com.two_ddang.logistics.delivery.domain.model.DeliveryAgent;
import com.two_ddang.logistics.delivery.domain.model.Transit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransitRepository extends JpaRepository<Transit, UUID> {

    Optional<Transit> findByIdAndIsDeletedIsFalse(UUID transitId);

    Page<Transit> findByTransitStatus(TransitStatus status, Pageable pageable);

}
