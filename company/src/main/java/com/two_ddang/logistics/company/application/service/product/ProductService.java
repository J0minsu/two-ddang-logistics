package com.two_ddang.logistics.company.application.service.product;

import com.two_ddang.logistics.company.application.dtos.product.*;
import com.two_ddang.logistics.company.application.exception.BusinessException;
import com.two_ddang.logistics.company.domain.model.company.Company;
import com.two_ddang.logistics.company.domain.model.product.Product;
import com.two_ddang.logistics.company.domain.repository.company.CompanyRepository;
import com.two_ddang.logistics.company.domain.repository.product.ProductRepository;
import com.two_ddang.logistics.company.infrastrucuture.HubService;
import com.two_ddang.logistics.company.presentation.dtos.product.CreateProductRequest;
import com.two_ddang.logistics.company.presentation.dtos.product.RollbackStockRequest;
import com.two_ddang.logistics.company.presentation.dtos.product.UpdateProductInfoRequest;
import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final HubService hubService;

    @Transactional
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        Product product = Product.create(createProductRequest);
        productRepository.save(product);
        return CreateProductResponse.of(product);
    }

    public Page<ProductResponse> getProducts(Pageable pageable, String keyword) {
        return productRepository.findAll(pageable).map(ProductResponse::of);
    }


    public ProductDetailResponse getProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        Company company = companyRepository.findById(product.getCompanyId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        String hubName = hubService.getHubInfo(product.getHubId()).getData().getName();

        return ProductDetailResponse.of(product, hubName, company.getCompanyName());
    }

    @Transactional
    public UpdateProductInfoResponse updateProductInfo(UUID productId, UpdateProductInfoRequest updateProductInfoRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        product.updateInfo(updateProductInfoRequest);

        return UpdateProductInfoResponse.of(product);
    }


    @Transactional
    public void deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
//        product.delete();
    }

    @Transactional
    public void rollbackStock(UUID productId, RollbackStockRequest rollbackStockRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        product.rollbackStock(rollbackStockRequest);
    }

    public List<CompanyProductResponse> getCompanyProducts(UUID companyId) {
        return productRepository.findAllByCompanyId(companyId)
                .stream()
                .map(CompanyProductResponse::of)
                .toList();
    }
}
