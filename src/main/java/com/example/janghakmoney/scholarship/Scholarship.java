package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Scholarship")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = true)
    private University university; // 조건: 특정 대학교

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = true)
    private Region region; // 조건: 특정 지역

    @Column(nullable = false)
    private Integer incomeLevel; // 조건: 소득 분위

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type; // 조건: 지원 유형 (ENUM)

    @Column(nullable = false)
    private String name; // 장학금명

    @Column(nullable = false)
    private String organization; // 장학 재단명

    @Column(nullable = false)
    private String description; // 지원 내용

    @Column(nullable = false)
    private LocalDate applicationStartDate; // 모집 시작일

    @Column(nullable = false)
    private LocalDate applicationEndDate; // 모집 종료일

    // 지원유형 ENUM 정의
    public enum Type {
        TUITION,                // 등록금
        LIVING_DUPLICATE,       // 생활비 - 타 장학 중복 O
        LIVING_NO_DUPLICATE,    // 생활비 - 타 장학 중복 X
        INTEREST_SUPPORT        // 이자지원
    }
}