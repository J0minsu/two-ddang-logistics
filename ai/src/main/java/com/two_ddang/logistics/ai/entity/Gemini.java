package com.two_ddang.logistics.ai.entity;

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

@Entity(name = "p_ai")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gemini extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID id;

    @Column
    @Getter
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column
    @Getter
    private AiType aiType;

    @Column
    @Getter
    private UUID referenceId;

    @Column
    @Getter
    private String prompt;

    @Column
    @Getter
    private String content;

}
