package com.example.janghakmoney.scholarship;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 운영기관, 장학명, 지원유형, 타 장학 중복 여부, 지원내용, 해당 지역, 소득 분위, 모집시작일, 모집종료일, 학기, 학교, 학점
    private String name;
    private String organization;
    private String type;
    private Boolean hasScholarship;
    private String description;
    private String targetRegion;
    private Integer incomeLevel;
    private LocalDate applicationStartDate;
    private LocalDate applicationEndDate;
    private String university;
    private Float grade;


}
