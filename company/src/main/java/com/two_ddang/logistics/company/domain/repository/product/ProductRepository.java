package com.two_ddang.logistics.company.domain.repository.product;

import com.two_ddang.logistics.company.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
