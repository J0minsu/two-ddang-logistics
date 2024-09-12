package com.two_ddang.logistics.ai.repository;

import com.two_ddang.logistics.ai.entity.Gemini;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeminiRepository extends JpaRepository<Gemini, UUID> {

}
