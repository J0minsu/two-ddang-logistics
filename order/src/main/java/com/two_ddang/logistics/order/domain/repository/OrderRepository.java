package com.two_ddang.logistics.order.domain.repository;

import com.two_ddang.logistics.order.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>, CustomOrderRepository {

    Order save(Order order);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByReqHubIdOrResHubId(Pageable pageable, UUID reqHubId, UUID resHubId);

    Page<Order> findAllByCreatedBy(Pageable pageable, Integer userId);
}
