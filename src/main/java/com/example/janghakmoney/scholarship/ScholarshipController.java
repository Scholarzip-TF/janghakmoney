package com.example.janghakmoney.scholarship;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholarships")
@RequiredArgsConstructor
@Tag(name = "Scholarship", description = "장학금 API")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @GetMapping("/possible") // 가능한 장학금들을 전부 보여줌
    public ResponseEntity<List<Scholarship>> searchPossibleScholarships(
            @RequestParam(required = false) Integer incomeLevel,
            @RequestParam(required = false) String targetRegion,
            @RequestParam(required = false) String university,
            @RequestParam(required = false) Float grade,
            @RequestParam(required = false) Boolean hasScholarship
    ) {
        return ResponseEntity.ok(scholarshipService.findPossibleScholarships(
                incomeLevel,
                targetRegion,
                university,
                grade,
                hasScholarship
        ));
    }

    @GetMapping("/{id}") // user가 선택한 장학금 디테일을 보여줌
    public ResponseEntity<Scholarship> getScholarshipDetail(@PathVariable Long id) {
        return ResponseEntity.ok(scholarshipService.findScholarshipDetail(id));
    }
}