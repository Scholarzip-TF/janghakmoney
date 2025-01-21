package com.example.janghakmoney.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // 기존 db에 유저 정보가 있는 지 확인
    Optional<User> findByPhone(String phone);

}
