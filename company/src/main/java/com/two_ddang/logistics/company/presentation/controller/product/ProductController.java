package com.two_ddang.logistics.company.presentation.controller.product;

import com.two_ddang.logistics.company.application.dtos.product.*;
import com.two_ddang.logistics.company.application.service.product.ProductService;
import com.two_ddang.logistics.company.presentation.dtos.product.CreateProductRequest;
import com.two_ddang.logistics.company.presentation.dtos.product.RollbackStockRequest;
import com.two_ddang.logistics.company.presentation.dtos.product.UpdateProductInfoRequest;
import com.two_ddang.logistics.core.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseDTO<CreateProductResponse>> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(ResponseDTO.okWithData(productService.createProduct(createProductRequest)));
    }


    @GetMapping
    public ResponseEntity<ResponseDTO<Page<ProductResponse>>> getProducts(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                                          @RequestParam("keyword") String keyword) {

        return ResponseEntity.ok(ResponseDTO.okWithData(productService.getProducts(pageable, keyword)));
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDTO<ProductDetailResponse>> getProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(productService.getProduct(productId)));
    }



    @PatchMapping("/{productId}")
    public ResponseEntity<ResponseDTO<UpdateProductInfoResponse>> updateProductInfo(@PathVariable UUID productId,
                                               @RequestBody UpdateProductInfoRequest updateProductInfoRequest) {


        return ResponseEntity.ok(ResponseDTO.okWithData(
                productService.updateProductInfo(productId, updateProductInfoRequest
                )));
    }


    @PostMapping("/{productId}/rollback")
    public ResponseEntity<?> rollbackStock(@PathVariable UUID productId,
                                           @RequestBody RollbackStockRequest rollbackStockRequest) {

        return ResponseEntity.ok(ResponseDTO.ok());
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDTO<Void>> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(ResponseDTO.ok());
    }


    @GetMapping("/companies/{companyId}")
    public ResponseEntity<?> getCompanyProducts(@PathVariable UUID companyId) {

        List<CompanyProductResponse> companyProductResponses = productService.getCompanyProducts(companyId);
        return ResponseEntity.ok(ResponseDTO.okWithData(companyProductResponses));
    }
}
