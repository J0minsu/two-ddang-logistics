package com.two_ddang.logistics.delivery.domain.repository;

import com.two_ddang.logistics.delivery.domain.model.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, UUID> {

    Optional<DeliveryAgent> findByUserIdAndIsDeletedIsFalse(Integer userId);

}
