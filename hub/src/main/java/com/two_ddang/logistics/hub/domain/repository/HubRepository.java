package com.two_ddang.logistics.hub.domain.repository;

import com.two_ddang.logistics.hub.domain.model.Hub;
import com.two_ddang.logistics.hub.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HubRepository extends JpaRepository<Hub, UUID> {

    Optional<Hub> findByIdAndIsDeletedIsFalse(UUID hubId);

    Page<Hub> findByNameStartingWith(@Param("name") String keyword, Pageable pageable);
}
