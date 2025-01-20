package com.example.janghakmoney.scholarship;

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

    @PostMapping("/possible") // 해당하는 장학금을 전부 보여줌
    public ResponseEntity<List<Scholarship>> searchPossibleScholarships(
            @RequestBody ScholarshipSearchCondition condition
    ) {
        return ResponseEntity.ok(scholarshipService.findPossibleScholarships(
                condition.getIncomeLevel(),
                condition.getTargetRegion(),
                condition.getUniversity(),
                condition.getGrade(),
                condition.getHasScholarship()
        ));
    }

    // 검색 조건을 위한 클래스
    @Getter
    @Setter
    public static class ScholarshipSearchCondition {
        private Integer incomeLevel;
        private String targetRegion;
        private String university;
        private Float grade;
        private Boolean hasScholarship;
    }

    @GetMapping("/{id}") // user가 선택한 장학금 디테일을 보여줌
    public ResponseEntity<Scholarship> getScholarshipDetail(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(scholarshipService.findScholarshipDetail(id));
    }
}