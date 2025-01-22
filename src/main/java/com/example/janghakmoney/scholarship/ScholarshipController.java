package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholarships")
@RequiredArgsConstructor
@Tag(name = "Scholarship", description = "장학금 API")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @PostMapping("/possible") // 지원 가능한 장학금 정보를 전부 보여줌
    public ResponseEntity<List<Scholarship>> searchPossibleScholarships(
            @RequestBody ScholarshipSearchCondition condition
    ) {
        return ResponseEntity.ok(scholarshipService.findPossibleScholarships(
                condition.getIncomeLevel(),
                condition.getRegion(),
                condition.getUniversity(),
                condition.getHasFullTuition(),
                condition.getHasScholarship()
        ));
    }

    @GetMapping("/{id}") // 유저가 선택한 장학금 디테일을 보여줌
    public ResponseEntity<Scholarship> getScholarshipDetail(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(scholarshipService.findScholarshipDetail(id));
    }

    @Getter
    @Setter
    public static class ScholarshipSearchCondition {
        private Integer incomeLevel;           // 소득분위
        private Region region;      // 지역 // RegionId로 수정 희망
        private University university;   // 대학교 // univId로 수정 희망
        private ScholarshipType type;   // 장학금 유형
        private Boolean hasFullTuition; // 등록금 지원 여부
        private Boolean hasScholarship; // 기존 장학금 수혜 여부

    }
}