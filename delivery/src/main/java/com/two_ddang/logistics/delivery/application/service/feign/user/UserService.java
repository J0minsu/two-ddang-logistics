package com.two_ddang.logistics.delivery.application.service.feign.user;

import com.two_ddang.logistics.delivery.application.service.feign.user.dto.res.UserRes;

public interface UserService {

    UserRes findUserById(int userId);

}
