package com.two_ddang.logistics.hub.domain.repository;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.hub.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByIdAndIsDeletedIsFalse(int id);


    Page<User> findByRole(@Param("role") UserType userType, PageRequest of);
}
