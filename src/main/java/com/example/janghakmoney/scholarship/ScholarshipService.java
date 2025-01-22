package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;

    public List<Scholarship> findPossibleScholarships(
            Integer incomeLevel,
            Region targetRegion,
            University university,
            ScholarshipType type,
            Boolean hasScholarship,
            Boolean hasFullTuition
    ) {
        return scholarshipRepository.findPossibleScholarships(
                incomeLevel,
                targetRegion,
                university,
                type,
                hasFullTuition,
                hasScholarship
        );
    }

    public Scholarship findScholarshipDetail(UUID scholarshipId) {
        return scholarshipRepository.findById(scholarshipId)
                .orElseThrow(() -> new EntityNotFoundException("해당 장학금을 찾을 수 없습니다. ID: " + scholarshipId));
    }
}

