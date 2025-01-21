package com.example.janghakmoney.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {
    // 기본적으로 JpaRepository 메서드 사용 가능 (예: findById)
}