package com.two_ddang.logistics.hub.presentation.controller;

import com.two_ddang.logistics.core.util.CommonApiResponses;
import com.two_ddang.logistics.hub.application.dto.UserRes;
import com.two_ddang.logistics.hub.holder.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SecurityRequirement(name = "Bearer Authentication")
@SecurityScheme( name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "사용자 관리 API")
@CommonApiResponses
public class UserController {

    @GetMapping("/{userId}")
    @Operation(summary = "사용자 단건 조회", description = "사용자 아이디로 조회 API")
    public ResponseEntity<Result<UserRes>> findById(@PathVariable int userId) {

        UserRes result = UserRes.example();

        return ResponseEntity.ok(Result.success(result));

    }

    @GetMapping("/hubs/{hubId}")
    @Operation(summary = "허브 소속 사용자 조회", description = "허브 사용자 조회 API")
    public ResponseEntity<Result<Page<UserRes>>> findByHub(@PathVariable int hubId) {

        List<UserRes> list = IntStream.range(1, 11).mapToObj(i -> UserRes.example()).toList();

        PageImpl result = new PageImpl(list, PageRequest.of(1, 10), 100);

        return ResponseEntity.ok(Result.success(result));

    }


    @PatchMapping("/{userId}")
    @Operation(summary = "사용자 수정", description = "사용자 수정 API")
    public ResponseEntity<Result<UserRes>> startDelivery(@PathVariable int userId) {

        UserRes result = UserRes.example();

        return ResponseEntity.ok(Result.success(result));

    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "사용자 논리적 삭제", description = "사용자 논리적 삭제 API")
    public ResponseEntity<Result<Void>> softDelete(@PathVariable int userId) {

//        DeliveryRes result = DeliveryRes.example(false);

        return ResponseEntity.ok(Result.success(null));

    }

}
