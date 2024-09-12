package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyDetailResponse;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CompanyService {

    ResponseDTO<CompanyDetailResponse> getCompany(UUID companyId);

    ResponseDTO<List<CompanyProductResponse>> getCompanyProducts(UUID companyId);
}
