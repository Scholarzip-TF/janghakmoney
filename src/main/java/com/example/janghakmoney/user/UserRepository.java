package com.example.janghakmoney.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 기존 db에 유저 정보가 있는 지 확인
    Optional<User> findByEmailOrPhone(String email, String phone);

}
