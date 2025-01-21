package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ScholarshipRepository extends JpaRepository<Scholarship, UUID> {

    @Query("SELECT s FROM Scholarship s " +
            "WHERE (:incomeLevel IS NULL OR s.incomeLevel >= :incomeLevel) " +
            "AND (:targetRegion IS NULL OR EXISTS (SELECT sr FROM s.scholarshipRegions sr WHERE sr.region = :targetRegion)) " +
            "AND (:university IS NULL OR EXISTS (SELECT su FROM s.scholarshipUniversities su WHERE su.university = :university)) " +
            "AND (:type IS NULL OR s.type = :type)")

    List<Scholarship> findPossibleScholarships(
            @Param("incomeLevel") Integer incomeLevel,
            @Param("targetRegion") Region targetRegion,
            @Param("university") University university,
            @Param("type") ScholarshipType type
    );

}
