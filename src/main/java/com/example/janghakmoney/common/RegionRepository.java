package com.example.janghakmoney.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {
    // 주요 이름으로 Region 조회
    Optional<Region> findByMajorName(String majorName);

    // 부모 지역 ID로 하위 지역들 조회
    List<Region> findByParent(Region parentRegion);

    // 지역 레벨로 Region 조회
    List<Region> findByLevel(int level);

    // 주요 이름과 부모 ID로 특정 지역 조회
    Optional<Region> findByMajorNameAndParentId(String majorName, UUID parentId);

    // 특정 레벨의 고유한 지역명들 조회
    @Query("SELECT DISTINCT r.majorName FROM Region r WHERE r.level = :level")
    List<String> findDistinctMajorNamesByLevel(@Param("level") int level);

    // 추가로 편의 메서드 제공
    @Query("SELECT r FROM Region r WHERE r.parent.id = :parentId")
    List<Region> findChildRegions(@Param("parentId") UUID parentId);
}