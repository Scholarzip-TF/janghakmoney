package com.example.janghakmoney.scholarship.dto;

import com.example.janghakmoney.scholarship.Scholarship;
import com.example.janghakmoney.scholarship.ScholarshipType;
import lombok.Getter;

import java.time.LocalDate;

// 장학금 상세 조회용 응답 DTO
@Getter
public class ScholarshipDetailResponse {

    private Long id;
    private String name;
    private String organization;
    private String description;
    private LocalDate applicationStartDate;
    private LocalDate applicationEndDate;
    private String note;
    private ScholarshipType type;

        public ScholarshipDetailResponse(Scholarship scholarship) {
            this.id = scholarship.getId();
            this.name = scholarship.getName();
            this.organization = scholarship.getOrganization();
            this.description = scholarship.getDescription();
            this.applicationStartDate = scholarship.getApplicationStartDate();
            this.applicationEndDate = scholarship.getApplicationEndDate();
            this.note = scholarship.getNote();
            this.type = scholarship.getType();

    }
}
