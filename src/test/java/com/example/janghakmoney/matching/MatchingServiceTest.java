package com.example.janghakmoney.matching;

import com.example.janghakmoney.matching.MatchingService;
import com.example.janghakmoney.scholarship.Scholarship;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class MatchingServiceTest {

    @Autowired
    private MatchingService matchingService;

    @Test
    void testFindMatchingScholarships() {
        // Given
        String universityId = "univ-1";
        String regionId = "region-2";
        Integer incomeLevel = 3;
        boolean hasFullTuition = false;
        boolean hasScholarship = true;

        // When
        List<Scholarship> scholarships = matchingService.findMatchingScholarships(
                universityId, regionId, incomeLevel, hasFullTuition, hasScholarship
        );

        // Then
        assertThat(scholarships).isnotempty(); // Expected non-empty result
    }
}