package com.two_ddang.logistics.ai.domain.model;

import com.two_ddang.logistics.ai.domain.AiType;
import com.two_ddang.logistics.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity(name = "p_ai")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gemini extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column
    private AiType aiType;

    @Column
    private UUID referenceId;

    @Column
    private String prompt;

    @Column
    private String content;

}
