package com.two_ddang.logistics.hub.presentation.controller;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.core.util.CommonApiResponses;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.hub.application.dto.UserRes;
import com.two_ddang.logistics.hub.application.service.HubService;
import com.two_ddang.logistics.hub.application.service.UserService;
import com.two_ddang.logistics.hub.domain.repository.UserRepository;
import com.two_ddang.logistics.hub.domain.vo.UserVO;
import com.two_ddang.logistics.hub.presentation.request.HubSortStandard;
import com.two_ddang.logistics.hub.presentation.request.UserModifyRequest;
import com.two_ddang.logistics.hub.presentation.request.UserRegisterRequest;
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

//@SecurityRequirement(name = "Bearer Authentication")
//@SecurityScheme( name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "사용자 관리 API")
@CommonApiResponses
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "사용자 생성", description = "사용자 생성 API")
    public ResponseEntity<ResponseDTO<UserRes>> register(@RequestBody UserRegisterRequest request) {

        UserVO user = userService.register(request);

        UserRes result = UserRes.fromVO(user);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @GetMapping("/{username}")
    @Operation(summary = "사용자 로그인 아이디로 조회", description = "사용자 로그인 아이디로 조회 API")
    public ResponseEntity<ResponseDTO<UserRes>> findByUsername(@PathVariable String username) {

        UserVO user = userService.findByUsername(username);

        UserRes result = UserRes.fromVO(user);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @GetMapping("/id/{userId}")
    @Operation(summary = "사용자 단건 조회", description = "사용자 아이디로 조회 API")
    public ResponseEntity<ResponseDTO<UserRes>> findById(@PathVariable int userId) {

        UserVO user = userService.findById(userId);

        UserRes result = UserRes.fromVO(user);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @GetMapping("")
    @Operation(summary = "사용자 검색", description = "사용자 조회 API")
    public ResponseEntity<ResponseDTO<Page<UserRes>>> search(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "HUB") UserType userType,
            @RequestParam(defaultValue = "CREATED_DESC") HubSortStandard standard) {

        size = switch (size) {
            case 30 -> 30;
            case 50 -> 50;
            default -> 10;
        };

        Page<UserVO> users = userService.findByUserType(pageNumber, size, userType, standard);

        Page<UserRes> result = users.map(UserRes::fromVO);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }


    @PatchMapping("/{userId}")
    @Operation(summary = "사용자 수정", description = "사용자 수정 API")
    public ResponseEntity<ResponseDTO<UserRes>> modify(
            @PathVariable int userId,
            @RequestBody UserModifyRequest request) {

        UserVO user = userService.modify(userId, request);

        UserRes result = UserRes.fromVO(user);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "사용자 논리적 삭제", description = "사용자 논리적 삭제 API")
    public ResponseEntity<ResponseDTO<Void>> softDelete(@PathVariable int userId) {

        /**
         * @TODO Passport check
         */

        userService.delete(userId);

        return ResponseEntity.ok(ResponseDTO.ok());

    }

}
