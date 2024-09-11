package com.two_ddang.logistics.order.infrastructure;


import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "company")
public interface ProductFeignClient extends ProductService{

    @Override
    @GetMapping("/api/v1/products/companies/{companyId}")
    ResponseDTO<List<CompanyProductResponse>> getCompanyProducts(@PathVariable UUID companyId);
}
