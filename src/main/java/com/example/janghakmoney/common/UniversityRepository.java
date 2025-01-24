package com.example.janghakmoney.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Integer> {
    // 대학교명으로 대학교 조회
    Optional<University> findByName(String name);

    // 대학교명 부분 일치로 대학교 검색 (Like 쿼리)
    List<University> findByNameContaining(String name);

    // 대학교명으로 존재 여부 확인
    boolean existsByName(String name);

    // 정렬된 대학교 목록 조회
    List<University> findAllByOrderByNameAsc();

    // 추가로 대소문자 구분 없는 검색 메서드
    @Query("SELECT u FROM University u WHERE LOWER(u.name) = LOWER(:name)")
    Optional<University> findByNameIgnoreCase(@Param("name") String name);
}
