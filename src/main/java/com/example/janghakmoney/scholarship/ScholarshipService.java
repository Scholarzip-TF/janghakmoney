package com.example.janghakmoney.scholarship;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;

    public List<Scholarship> findPossibleScholarships(
            Integer incomeLevel,
            String targetRegion,
            String university,
            Float grade,
            Boolean hasScholarship
    ) {
        return scholarshipRepository.findPossibleScholarships(
                incomeLevel,
                targetRegion,
                university,
                grade,
                hasScholarship
        );
    }

    public Scholarship findScholarshipDetail(Long scholarshipId) {
        return scholarshipRepository.findById(scholarshipId)
                .orElseThrow(() -> new IllegalStateException("해당 장학금을 찾을 수 없습니다. ID: " + scholarshipId));
    }

}
