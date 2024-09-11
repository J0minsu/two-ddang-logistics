package com.two_ddang.logistics.hub.application.service;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.hub.domain.model.User;
import com.two_ddang.logistics.hub.domain.repository.UserRepository;
import com.two_ddang.logistics.hub.domain.vo.UserVO;
import com.two_ddang.logistics.hub.infrastructrure.exception.NoSuchElementApplicationException;
import com.two_ddang.logistics.hub.presentation.request.HubSortStandard;
import com.two_ddang.logistics.hub.presentation.request.UserModifyRequest;
import com.two_ddang.logistics.hub.presentation.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserVO register(UserRegisterRequest request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        User user = User.of(
                request.getUsername(), request.getPassword(), request.getName(),
                request.getEmail(), request.getContact(), request.getRole());

        User savedUser = userRepository.save(user);

        return savedUser.toVO();

    }

    public UserVO findById(int userId) {

        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(NoSuchElementApplicationException::new);

        return user.toVO();

    }

    public Page<UserVO> findByUserType(int pageNumber, int size, UserType userType, HubSortStandard standard) {

        Page<User> users = userRepository.findByRole(userType, PageRequest.of(pageNumber + 1, size, standard.getSort()));

        Page<UserVO> result = users.map(User::toVO);

        return result;
    }

    @Transactional
    public UserVO modify(int userId, UserModifyRequest request) {

        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(NoSuchElementApplicationException::new);

        user.modifyUserInfo(request.getEmail(), request.getContact());

        return user.toVO();

    }

    @Transactional
    public void delete(int userId) {

        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(NoSuchElementApplicationException::new);

        user.delete(1);

    }
}
