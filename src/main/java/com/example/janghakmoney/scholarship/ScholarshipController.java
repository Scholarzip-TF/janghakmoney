package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
                condition.getTargetRegion(),
                condition.getUniversity(),
                condition.getType()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scholarship> getScholarshipDetail(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(scholarshipService.findScholarshipDetail(id));
    }

    @Data
    public static class ScholarshipSearchCondition {
        private Integer incomeLevel;           // 소득분위
        private Region targetRegion;           // 지역
        private University university;             // 대학교
        private ScholarshipType type;          // 장학금 유형

    }
}