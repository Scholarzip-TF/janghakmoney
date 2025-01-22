package com.example.janghakmoney.scholarship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ScholarshipRepository extends JpaRepository<Scholarship, UUID> {

    @Query("SELECT DISTINCT s FROM Scholarship s " +
            "LEFT JOIN s.scholarshipUniversities su " +
            "WHERE (su.university IS NULL OR su.university.id = :universityId)")
    List<Scholarship> findByUniversity(@Param("universityId") String universityId);

    @Query("SELECT DISTINCT s FROM Scholarship s " +
            "LEFT JOIN s.scholarshipRegions sr " +
            "LEFT JOIN sr.region r " +
            "LEFT JOIN Region ur ON ur.id = :userRegionId " +
            "WHERE (sr.region IS NULL OR " +
            "(r.level = 1 AND r.id = ur.parent.id) OR " +
            "(r.level = 2 AND r.id = ur.id))")
    List<Scholarship> findByRegion(@Param("userRegionId") String userRegionId);

    @Query("SELECT s " +
            "FROM Scholarship s " +
            "WHERE s.incomeLevel >= :userIncomeLevel")
    List<Scholarship> findByIncomeLevel(@Param("userIncomeLevel") Integer userIncomeLevel);

    @Query("SELECT s FROM Scholarship s " +
            "WHERE (s.type = 'TUITION' AND :hasFullTuition = false) OR " +
            "(s.type = 'LIVING_DUPLICATE') OR " +
            "(s.type = 'LIVING_NO_DUPLICATE' AND :hasScholarship = false)")
    List<Scholarship> findByScholarshipType(
            @Param("hasFullTuition") boolean hasFullTuition,
            @Param("hasScholarship") boolean hasScholarship);

    @Query("SELECT DISTINCT s " +
            "FROM Scholarship s " +
            "LEFT JOIN s.scholarshipUniversities su " +
            "LEFT JOIN s.scholarshipRegions sr " +
            "LEFT JOIN sr.region r " +
            "WHERE (:universityId IS NULL OR su.university.id = :universityId OR su.university IS NULL) " +
            "AND (:regionId IS NULL OR sr.region.id = :regionId OR r.level = 1 AND r.id = :parentRegionId) " +
            "AND s.incomeLevel >= :incomeLevel " +
            "AND ((s.type = 'TUITION' AND :hasFullTuition = false) OR " +
            "     (s.type = 'LIVING_DUPLICATE') OR " +
            "     (s.type = 'LIVING_NO_DUPLICATE' AND :hasScholarship = false))")
    List<Scholarship> findPossibleScholarships( // 통합 메서드
            @Param("universityId") String universityId,
            @Param("regionId") String regionId,
            @Param("parentRegionId") String parentRegionId,
            @Param("incomeLevel") Integer incomeLevel,
            @Param("hasFullTuition") boolean hasFullTuition,
            @Param("hasScholarship") boolean hasScholarship
    );
}
