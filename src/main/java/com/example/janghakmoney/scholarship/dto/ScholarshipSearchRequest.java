package com.example.janghakmoney.scholarship.dto;

import com.example.janghakmoney.scholarship.ScholarshipType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ScholarshipSearchRequest {

    @NotNull
    private Integer regionId;

    @NotNull
    private Integer universityId;

    @NotNull
    private Integer incomeLevel;

    @NotNull
    private Boolean hasFullTuition; // 등록금 지원 여부

    @NotNull
    private Boolean hasScholarship;

    @NotNull
    private String phoneNumber;
}
