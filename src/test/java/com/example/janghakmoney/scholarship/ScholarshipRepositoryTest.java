package com.example.janghakmoney.scholarship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ScholarshipRepositoryTest {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @Test
    void testSaveScholarship() {
        // Given
        Scholarship scholarship = new Scholarship();
        scholarship.setName("테스트 장학금");
        scholarship.setIncomeLevel(3);
        scholarship.setType(Scholarship.Type.TUITION);
        scholarshipRepository.save(scholarship);

        // When
        var scholarships = scholarshipRepository.findAll();

        // Then
        assertThat(scholarships).isNotEmpty();
        assertThat(scholarships.get(0).getName()).isEqualTo("테스트 장학금");
    }
}