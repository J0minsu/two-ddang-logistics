package com.two_ddang.logistics.company.presentation.controller.product;

import com.two_ddang.logistics.company.common.utils.ResponseDTO;
import com.two_ddang.logistics.company.presentation.request.product.CreateProductRequest;
import com.two_ddang.logistics.company.presentation.request.product.RollbackStockRequest;
import com.two_ddang.logistics.company.presentation.request.product.UpdateProductInfoRequest;
import com.two_ddang.logistics.company.presentation.response.product.*;
import org.springframework.data.domain.PageImpl;
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
public class ProductController {

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(ResponseDTO.okWithData(CreateProductResponse.builder().build()));
    }


    @GetMapping
    public ResponseEntity<?> getProducts(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                         @RequestParam("keyword") String keyword) {
        List<ProductResponse> productResponses = new ArrayList<>();
        return ResponseEntity.ok(ResponseDTO.okWithData(new PageImpl<ProductResponse>(productResponses)));
    }


    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(ProductDetailResponse.builder().build()));
    }


    @GetMapping("/companies/{companyId}")
    public ResponseEntity<?> getCompanyProducts(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                @PathVariable UUID companyId,
                                                @RequestParam("keyword") String keyword) {
        List<CompanyProductResponse> companyProductResponses = new ArrayList<>();
        return ResponseEntity.ok(ResponseDTO.okWithData(new PageImpl<CompanyProductResponse>(companyProductResponses)));
    }


    @PatchMapping("/{productId}")
    public ResponseEntity<?> updateProductInfo(@PathVariable UUID productId,
                                               @RequestBody UpdateProductInfoRequest updateProductInfoRequest) {
        return ResponseEntity.ok(ResponseDTO.okWithData(UpdateProductInfoResponse.builder().build()));
    }


    @PostMapping("/{productId}/rollback")
    public ResponseEntity<?> rollbackStock(@PathVariable UUID productId,
                                           @RequestBody RollbackStockRequest rollbackStockRequest) {
        return ResponseEntity.ok(ResponseDTO.ok());
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(ResponseDTO.ok());
    }
}
