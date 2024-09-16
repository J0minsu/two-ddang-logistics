package com.two_ddang.logistics.delivery.application.service.feign.user;

import com.two_ddang.logistics.delivery.application.service.feign.user.dto.res.UserRes;
import com.two_ddang.logistics.delivery.application.service.feign.user.fallback.UserFallbackFactory;
import com.two_ddang.logistics.delivery.infrastructrure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "user",
configuration = FeignClientConfig.class,
fallbackFactory = UserFallbackFactory.class)
public interface UserFeignClient extends UserService {

    @Override
    @GetMapping("/api/v1/users/id/{userId}")
    UserRes findUserById(int userId);
}
