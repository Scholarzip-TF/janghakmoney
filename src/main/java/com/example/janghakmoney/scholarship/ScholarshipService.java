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
            UUID regionId,
            UUID universityId,
            Boolean hasFullTuition,
            Boolean hasScholarship
    ) {
    return scholarshipRepository.findPossibleScholarships(
                    regionId,
                    universityId,
                    incomeLevel,
                    hasFullTuition,
                    hasScholarship
        );
    }

    public Scholarship findScholarshipDetail(Long scholarshipId) {
        return scholarshipRepository.findById(scholarshipId)
                .orElseThrow(() -> new EntityNotFoundException("해당 장학금을 찾을 수 없습니다. ID: " + scholarshipId));
    }
}

