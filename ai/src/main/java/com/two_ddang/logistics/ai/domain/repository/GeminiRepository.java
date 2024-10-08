package com.two_ddang.logistics.ai.domain.repository;

import com.two_ddang.logistics.ai.domain.model.Gemini;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GeminiRepository extends JpaRepository<Gemini, UUID> {

    Optional<Gemini> findByIdAndDeletedAtIsNull(UUID uuid);

    Page<Gemini> findAllByDeletedIsFalse(Pageable pageable);

}
