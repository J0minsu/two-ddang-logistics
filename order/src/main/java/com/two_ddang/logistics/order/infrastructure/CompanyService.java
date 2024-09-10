package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyDetailResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CompanyService {

    ResponseDTO<CompanyDetailResponse> getCompany(UUID companyId);
}
