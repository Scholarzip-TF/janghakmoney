package com.example.janghakmoney.scholarship.dto;

import com.example.janghakmoney.scholarship.Scholarship;
import com.example.janghakmoney.scholarship.ScholarshipType;
import lombok.Getter;

import java.time.LocalDate;

// 장학금 목록 조회용 응답 DTO
@Getter
public class ScholarshipListResponse {
    private Long id;
    private String name;                   // 장학금명
    private String organization;           // 주관기관
    private String description;            // 간단한 설명
    private LocalDate applicationStartDate; // 신청 시작일
    private LocalDate applicationEndDate;   // 신청 종료일
    private ScholarshipType type;          // 장학금 유형

    public ScholarshipListResponse(Scholarship scholarship) {
        this.id = scholarship.getId();
        this.name = scholarship.getName();
        this.organization = scholarship.getOrganization();
        this.description = scholarship.getDescription();
        this.applicationStartDate = scholarship.getApplicationStartDate();
        this.applicationEndDate = scholarship.getApplicationEndDate();
        this.type = scholarship.getType();

    }
}
