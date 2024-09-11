package com.two_ddang.logistics.hub.domain.repository;

import com.two_ddang.logistics.hub.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
