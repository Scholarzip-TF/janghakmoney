package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Integer> {

    @Query("SELECT DISTINCT s FROM Scholarship s " +
            "LEFT JOIN s.scholarshipUniversities su " +
            "WHERE (su.university IS NULL OR su.university = :university)")
    List<Scholarship> findByUniversity(@Param("university") University university);

    @Query("SELECT DISTINCT s FROM Scholarship s " +
        "LEFT JOIN s.scholarshipRegions sr " +
        "LEFT JOIN sr.region r " +
        "LEFT JOIN r.parent p " +
        "WHERE sr.region IS NULL " + // (1) 조건: 지역 제한 없음
        "OR (r.level = 1 AND p = :region) " + // (2) 조건: 대분류만 일치
        "OR (r.level = 2 AND r = :region)") // (3) 조건: 대분류와 소분류 모두 일치
    List<Scholarship> findByRegion(@Param("region") Region region);

    @Query("SELECT s FROM Scholarship s " +
            "WHERE s.incomeLevel >= :userIncomeLevel")
    List<Scholarship> findByIncomeLevel(@Param("userIncomeLevel") Integer userIncomeLevel);

    @Query("SELECT s FROM Scholarship s " +
            "WHERE (s.type = 'TUITION' AND :hasFullTuition = false) " +
            "OR (s.type = 'LIVING_DUPLICATE')" +
            "OR (s.type = 'LIVING_NO_DUPLICATE' AND :hasScholarship = false)")
    List<Scholarship> findByScholarshipType(
            @Param("hasFullTuition") boolean hasFullTuition,
            @Param("hasScholarship") boolean hasScholarship);

    @Query("SELECT DISTINCT s FROM Scholarship s " +
            "LEFT JOIN s.scholarshipUniversities su " +
            "LEFT JOIN s.scholarshipRegions sr LEFT JOIN sr.region r LEFT JOIN r.parent p " +
            "WHERE " +
            // 대학 조건
            "(:userUniversityId IS NULL OR su.university.id = :userUniversityId OR su.university.id IS NULL) AND " +
            // 지역 조건
            "(:userRegionId IS NULL OR " +
            "(sr.region IS NULL " +
            "OR (r.level = 1 AND p.id = :userRegionId) " +
            "OR (r.level = 2 AND r.id = :userRegionId))) AND " +
            // 소득분위 조건
            "(:userIncomeLevel IS NULL OR s.incomeLevel >= :userIncomeLevel) AND " +
            // 지원유형 조건
            "((s.type = 'TUITION' AND :hasFullTuition = false) " +
            "OR (s.type = 'LIVING_DUPLICATE') " +
            "OR (s.type = 'LIVING_NO_DUPLICATE' AND :hasScholarship = false)) AND" +
            // 마감일 조건
            "(s.applicationEndDate BETWEEN :minDate AND CURRENT_DATE)" +
            "ORDER BY s.applicationEndDate DESC")
    List<Scholarship> findPossibleScholarships(
            @Param("userRegionId") Integer userRegionId,
            @Param("userUniversityId") Integer userUniversityId,
            @Param("userIncomeLevel") Integer userIncomeLevel,
            @Param("hasFullTuition") boolean hasFullTuition,
            @Param("hasScholarship") boolean hasScholarship,
            @Param("minDate") LocalDate minDate);


    Optional<Scholarship> findById(Long scholarshipId);
}
