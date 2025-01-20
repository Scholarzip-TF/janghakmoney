package com.example.janghakmoney.scholarship;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("dev")  // 개발 환경에서만 실행
public class ScholarshipDataLoader implements CommandLineRunner {

    private final ScholarshipRepository scholarshipRepository;

    public ScholarshipDataLoader(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    @Override
    public void run(String... args) {
        // 기존 데이터 삭제
        scholarshipRepository.deleteAll();

        // 테스트 데이터 생성 및 저장
        scholarshipRepository.save(createScholarship(
                "국가장학금", "한국장학재단", "소득장학", true,
                "소득 분위에 따른 국가장학금", "전국", 8,
                LocalDate.now(), LocalDate.now().plusMonths(1),
                "서울대학교", 3.0f));

        scholarshipRepository.save(createScholarship(
                "성적우수장학금", "서울대학교", "성적장학", false,
                "학업 성적 우수자를 위한 장학금", "서울", 0,
                LocalDate.now(), LocalDate.now().plusMonths(2),
                "서울대학교", 4.0f));

        scholarshipRepository.save(createScholarship(
                "지역인재장학금", "부산시", "지역장학", false,
                "부산 지역 인재 육성을 위한 장학금", "부산", 5,
                LocalDate.now(), LocalDate.now().plusMonths(3),
                "부산대학교", 3.5f));

        scholarshipRepository.save(createScholarship(
                "미래인재장학금", "삼성장학재단", "특별장학", true,
                "이공계 특성화 장학금", "전국", 6,
                LocalDate.now(), LocalDate.now().plusMonths(1),
                null, 3.8f));

        scholarshipRepository.save(createScholarship(
                "희망장학금", "현대장학재단", "특별장학", false,
                "저소득층 학생 지원 장학금", "전국", 3,
                LocalDate.now(), LocalDate.now().plusMonths(2),
                null, 3.0f));

        scholarshipRepository.save(createScholarship(
                "글로벌인재장학금", "LG연암문화재단", "특별장학", true,
                "해외유학 지원 장학금", "전국", 4,
                LocalDate.now(), LocalDate.now().plusMonths(4),
                null, 4.0f));

        scholarshipRepository.save(createScholarship(
                "지역발전장학금", "대구시", "지역장학", false,
                "대구 지역 발전을 위한 인재 양성", "대구", 5,
                LocalDate.now(), LocalDate.now().plusMonths(2),
                "경북대학교", 3.5f));

        scholarshipRepository.save(createScholarship(
                "과학인재장학금", "포스코청암재단", "특별장학", true,
                "이공계 학생 지원 장학금", "전국", 7,
                LocalDate.now(), LocalDate.now().plusMonths(3),
                null, 3.8f));

        scholarshipRepository.save(createScholarship(
                "예체능장학금", "문화체육관광부", "특기장학", false,
                "예체능 특기자 장학금", "전국", 8,
                LocalDate.now(), LocalDate.now().plusMonths(2),
                null, 2.5f));

        scholarshipRepository.save(createScholarship(
                "농어촌장학금", "농어촌희망재단", "지역장학", true,
                "농어촌 지역 학생 지원", "농어촌", 4,
                LocalDate.now(), LocalDate.now().plusMonths(3),
                null, 3.0f));
    }

    private Scholarship createScholarship(
            String name, String organization, String type, Boolean hasScholarship,
            String description, String targetRegion, Integer incomeLevel,
            LocalDate startDate, LocalDate endDate, String university, Float grade) {

        Scholarship scholarship = new Scholarship();
        scholarship.setName(name);
        scholarship.setOrganization(organization);
        scholarship.setType(type);
        scholarship.setHasScholarship(hasScholarship);
        scholarship.setDescription(description);
        scholarship.setTargetRegion(targetRegion);
        scholarship.setIncomeLevel(incomeLevel);
        scholarship.setApplicationStartDate(startDate);
        scholarship.setApplicationEndDate(endDate);
        scholarship.setUniversity(university);
        scholarship.setGrade(grade);

        return scholarship;
    }
}