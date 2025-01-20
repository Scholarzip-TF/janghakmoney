package com.example.janghakmoney.scholarship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {

    @Query("SELECT s FROM Scholarship s " +
            "WHERE (:incomeLevel IS NULL OR s.incomeLevel >= :incomeLevel) " +
            "AND (:targetRegion IS NULL OR s.targetRegion = :targetRegion) " +
            "AND (:university IS NULL OR s.university = :university) " +
            "AND (:grade IS NULL OR s.grade <= :grade) " +
            "AND (:hasScholarship IS NULL OR s.hasScholarship = :hasScholarship)")

    List<Scholarship> findPossibleScholarships(
            @Param("incomeLevel") Integer incomeLevel,
            @Param("targetRegion") String targetRegion,
            @Param("university") String university,
            @Param("grade") Float grade,
            @Param("hasScholarship") Boolean hasScholarship
    );

}
