package com.two_ddang.logistics.company.domain.repository.company;

import com.two_ddang.logistics.company.domain.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByCompanyManager(Integer companyManager);
}
