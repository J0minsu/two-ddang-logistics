package com.two_ddang.logistics.hub.domain.repository;

import com.two_ddang.logistics.hub.domain.model.Hub;
import com.two_ddang.logistics.hub.domain.model.HubAgent;
import com.two_ddang.logistics.hub.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HubAgentRepository extends JpaRepository<HubAgent, UUID> {

    Optional<HubAgent> findByUserIdAndIsDeletedIsFalse(Integer id);

}
