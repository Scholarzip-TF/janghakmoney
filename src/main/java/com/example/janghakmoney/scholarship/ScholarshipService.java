package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScholarshipService {
    private final ScholarshipRepository scholarshipRepository;
    private final UserRepository userRepository;

    public List<Scholarship> findPossibleScholarships(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return scholarshipRepository.findAll().stream()
                .filter(scholarship -> isPossible(user, scholarship))
                .collect(Collectors.toList());
    }

    private boolean isPossible(User user, Scholarship scholarship) {
        return user.getIncomeLevel() <= scholarship.getMaxIncomeLevel()
                && (scholarship.getTargetRegion() == null || scholarship.getTargetRegion().equals(user.getRegion()))
                && (scholarship.getTargetUniversity() == null || scholarship.getTargetUniversity().equals(user.getUniversity()))
                && (!scholarship.getRequiresPriorScholarship() || user.getHasScholarship());
    }
}
