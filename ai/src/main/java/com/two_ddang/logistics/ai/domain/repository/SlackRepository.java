package com.two_ddang.logistics.ai.domain.repository;

import com.two_ddang.logistics.ai.domain.model.SlackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlackRepository extends JpaRepository<SlackEntity, UUID> {

}
