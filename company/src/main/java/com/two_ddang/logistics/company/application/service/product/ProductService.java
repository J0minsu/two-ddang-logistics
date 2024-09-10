package com.two_ddang.logistics.company.application.service.product;

import com.two_ddang.logistics.company.domain.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
}
