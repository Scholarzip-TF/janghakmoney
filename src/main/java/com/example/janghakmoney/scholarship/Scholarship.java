package com.example.janghakmoney.scholarship;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Scholarship")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 정보 6개
    @Column(nullable = false)
    private String name; // 장학금명

    @Column(nullable = false)
    private String organization; // 장학 재단명

    @Column(nullable = false)
    private String description; // 지원 내용

    @Column(nullable = true)
    private LocalDate applicationStartDate; // 모집 시작일

    @Column(nullable = false)
    private LocalDate applicationEndDate; // 모집 종료일

    @Column(nullable = true)
    private String note; // 비고

    // 비교 조건 4개
    // 조건: 특정 대학교 (장학금-대학조건 연결)
    @OneToMany(mappedBy = "scholarship", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<ScholarshipUniversity> scholarshipUniversities = new ArrayList<>();

    // 조건: 특정 지역 (장학금-지역 연결)
    @Column(nullable = true)
    @OneToMany(mappedBy = "scholarship", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ScholarshipRegion> scholarshipRegions = new ArrayList<>();

    @Column(nullable = false)
    private Integer incomeLevel; // 조건: 소득 분위

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScholarshipType type;

    // 조건: 지원 유형 (ENUM)
    // TUITION,                    // 등록금
    //    LIVING_DUPLICATE,           // 생활비 - 타 장학 중복 가능
    //    LIVING_NO_DUPLICATE,        // 생활비 - 타 장학 중복 불가
    //    INTEREST                   // 이자 지원

}