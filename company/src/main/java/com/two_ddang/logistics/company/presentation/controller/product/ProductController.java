package com.two_ddang.logistics.company.presentation.controller.product;

import com.two_ddang.logistics.company.application.dtos.product.*;
import com.two_ddang.logistics.company.application.service.product.ProductService;
import com.two_ddang.logistics.company.presentation.dtos.product.CreateProductRequest;
import com.two_ddang.logistics.company.presentation.dtos.product.RollbackStockRequest;
import com.two_ddang.logistics.company.presentation.dtos.product.UpdateProductInfoRequest;
import com.two_ddang.logistics.core.util.CurrentPassport;
import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.core.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "상품 API")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "상품 생성", description = "상품 생성 API")
    public ResponseEntity<ResponseDTO<CreateProductResponse>> createProduct(
            @RequestBody CreateProductRequest createProductRequest,
            @CurrentPassport Passport passport)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(productService.createProduct(createProductRequest, passport)));
    }


    @GetMapping
    @Operation(summary = "상품 리스트 조회", description = "상품 생성 API")
    public ResponseEntity<ResponseDTO<Page<ProductResponse>>> getProducts(
            @PageableDefault(page = 0,
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam("keyword") String keyword)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(productService.getProducts(pageable, keyword)));
    }


    @GetMapping("/{productId}")
    @Operation(summary = "상품 단건 조회", description = "상품 단건 조회 API")
    public ResponseEntity<ResponseDTO<ProductDetailResponse>> getProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(productService.getProduct(productId)));
    }



    @PatchMapping("/{productId}")
    @Operation(summary = "상품 정보 수정", description = "상품 정보 수정 API")
    public ResponseEntity<ResponseDTO<UpdateProductInfoResponse>> updateProductInfo(
            @PathVariable UUID productId,
            @RequestBody UpdateProductInfoRequest updateProductInfoRequest,
            @CurrentPassport Passport passport)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(
                productService.updateProductInfo(productId, updateProductInfoRequest, passport)));
    }


    @PostMapping("/{productId}/rollback")
    @Operation(summary = "상품 재고 롤백", description = "상품 재고 롤백 API")
    public ResponseEntity<ResponseDTO<Void>> rollbackStock(@PathVariable UUID productId,
                                           @RequestBody RollbackStockRequest rollbackStockRequest) {
        productService.rollbackStock(productId, rollbackStockRequest);
        return ResponseEntity.ok(ResponseDTO.ok());
    }


    @DeleteMapping("/{productId}")
    @Operation(summary = "상품 삭제", description = "상품 삭제 API")
    public ResponseEntity<ResponseDTO<Void>> deleteProduct(@PathVariable UUID productId, @CurrentPassport Passport passport) {
        productService.deleteProduct(productId, passport);
        return ResponseEntity.ok(ResponseDTO.ok());
    }


    @GetMapping("/companies/{companyId}")
    @Operation(summary = "업체 상품 리스트 조회", description = "업체 상품 리스트 조회 API")
    public ResponseEntity<ResponseDTO<List<CompanyProductResponse>>> getCompanyProducts(@PathVariable UUID companyId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(productService.getCompanyProducts(companyId)));
    }
}
