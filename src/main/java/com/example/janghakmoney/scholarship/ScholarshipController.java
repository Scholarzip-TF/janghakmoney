package com.example.janghakmoney.scholarship;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/scholarships")
@RequiredArgsConstructor
@Tag(name = "Scholarship", description = "장학금 API")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @PostMapping("/possible")
    public ResponseEntity<List<Scholarship>> searchPossibleScholarships(
            @RequestBody ScholarshipSearchCondition condition
    ) {
        return ResponseEntity.ok(scholarshipService.findPossibleScholarships(
                condition.getIncomeLevel(),
//                condition.getTargetRegion(),
                condition.getRegionId(),
//                condition.getUniversity(),
                condition.getUniversityId(),
                condition.getType(),
                condition.getHasFullTuition(),
                condition.getHasScholarship()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scholarship> getScholarshipDetail(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(scholarshipService.findScholarshipDetail(id));
    }

    @Data
    public static class ScholarshipSearchCondition {
        private Integer incomeLevel;           // 소득분위
//        private Region targetRegion;      // 지역 // RegionId로 수정 희망
        private UUID regionId;      // 지역 ID
//        private University university;   // 대학교 // univId로 수정 희망
        private UUID universityId;   // 대학교 ID
        private ScholarshipType type;   // 장학금 유형
        private Boolean hasFullTuition; // 등록금 지원 여부
        private Boolean hasScholarship; // 기존 장학금 수혜 여부

    }
}