package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import com.example.janghakmoney.scholarship.dto.ScholarshipDetailResponse;
import com.example.janghakmoney.scholarship.dto.ScholarshipListResponse;
import com.example.janghakmoney.scholarship.dto.ScholarshipSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/scholarships")
@RequiredArgsConstructor
@Tag(name = "Scholarship", description = "장학금 API")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @PostMapping("/possible")
    @Operation(summary = "가능한 장학금 검색", description = "지원 가능한 장학금 목록을 조회합니다.")
    public ResponseEntity<List<ScholarshipListResponse>> searchPossibleScholarships(
            @Valid @RequestBody ScholarshipSearchRequest condition
    ) {
        List<Scholarship> scholarships = scholarshipService.findPossibleScholarships(
                condition.getIncomeLevel(),
                condition.getRegionId(),
                condition.getUniversityId(),
                condition.getHasFullTuition(),
                condition.getHasScholarship()
        );
        return ResponseEntity.ok(scholarships.stream()
                .map(ScholarshipListResponse::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "장학금 상세 조회", description = "특정 장학금의 상세 정보를 조회합니다.")
    public ResponseEntity<ScholarshipDetailResponse> getScholarshipDetail(@PathVariable("id") Long id) {
        Scholarship scholarship = scholarshipService.findScholarshipDetail(id);
        return ResponseEntity.ok(new ScholarshipDetailResponse(scholarship));
    }
}