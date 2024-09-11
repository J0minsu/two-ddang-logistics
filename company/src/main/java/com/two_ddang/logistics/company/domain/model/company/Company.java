package com.two_ddang.logistics.company.domain.model.company;

import com.two_ddang.logistics.company.presentation.dtos.company.CreatedCompanyRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.UpdateCompanyInfoRequest;
import com.two_ddang.logistics.core.entity.BaseEntity;
import com.two_ddang.logistics.core.entity.CompanyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Entity
@Table(name = "p_companies")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@SQLRestriction(value = "is_deleted = false")
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String companyName;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CompanyType companyType;

    @Column(nullable = false)
    private UUID hubId;

    private String address;

    @Column(nullable = false)
    private Integer companyManager;

    public static Company create(CreatedCompanyRequest createdCompanyRequestDto) {
        return Company.builder()
                .companyName(createdCompanyRequestDto.getCompanyName())
                .companyType(createdCompanyRequestDto.getCompanyType())
                .hubId(createdCompanyRequestDto.getHubId())
                .address(createdCompanyRequestDto.getAddress())
                .companyManager(createdCompanyRequestDto.getCompanyManager())
                .build();
    }

    public void updateInfo(UpdateCompanyInfoRequest updateCompanyRequestDto) {
        this.companyName = updateCompanyRequestDto.getCompanyName();
        this.companyType = updateCompanyRequestDto.getCompanyType();
        this.hubId = updateCompanyRequestDto.getHubId();
        this.address = updateCompanyRequestDto.getAddress();
        this.companyManager = updateCompanyRequestDto.getCompanyManager();
    }
}
