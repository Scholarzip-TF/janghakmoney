package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
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

    @Query("SELECT s FROM Scholarship s " +
            "WHERE s.incomeLevel >= :userIncomeLevel")
    List<Scholarship> findByIncomeLevel(@Param("userIncomeLevel") Integer userIncomeLevel);

    @Query("SELECT s FROM Scholarship s " +
            "WHERE (s.type = 'TUITION' AND :hasFullTuition = false) OR " +
            "(s.type = 'LIVING_DUPLICATE') OR " +
            "(s.type = 'LIVING_NO_DUPLICATE' AND :hasScholarship = false)")
    List<Scholarship> findByScholarshipType(
            @Param("hasFullTuition") boolean hasFullTuition,
            @Param("hasScholarship") boolean hasScholarship);

    @Query("SELECT DISTINCT s FROM Scholarship s " +
            "LEFT JOIN s.scholarshipUniversities su " +
            "LEFT JOIN s.scholarshipRegions sr " +
            "LEFT JOIN sr.region r " +
            "LEFT JOIN Region ur ON ur.id = :userRegionId " +
            "WHERE " +
            // 대학 조건
            "(su.university IS NULL OR su.university.id = :universityId) AND " +
            // 지역 조건
            "(sr.region IS NULL OR " +
            "(r.level = 1 AND r.id = ur.parent.id) OR " +
            "(r.level = 2 AND r.id = ur.id)) AND " +
            // 소득분위 조건
            "s.incomeLevel >= :userIncomeLevel AND " +
            // 지원유형 조건
            "((s.type = 'TUITION' AND :hasFullTuition = false) OR " +
            "(s.type = 'LIVING_DUPLICATE') OR " +
            "(s.type = 'LIVING_NO_DUPLICATE' AND :hasScholarship = false))")
    List<Scholarship> findPossibleScholarships(
            @Param("universityId") String universityId,
            @Param("userRegionId") String userRegionId,
            @Param("userIncomeLevel") Integer userIncomeLevel,
            @Param("hasFullTuition") boolean hasFullTuition,
            @Param("hasScholarship") boolean hasScholarship);

}
