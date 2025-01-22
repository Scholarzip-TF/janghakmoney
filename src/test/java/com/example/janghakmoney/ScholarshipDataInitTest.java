package com.example.janghakmoney;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.RegionRepository;
import com.example.janghakmoney.common.University;
import com.example.janghakmoney.common.UniversityRepository;
import com.example.janghakmoney.scholarship.*;
import com.example.janghakmoney.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ScholarshipDataInitTest {
    @Autowired
    private ScholarshipRepository scholarshipRepository;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    void initData() {

        // 기존 데이터 삭제
        scholarshipRepository.deleteAll();
        scholarshipRepository.flush();

        userRepository.deleteAll();
        userRepository.flush();

        universityRepository.deleteAll();
        universityRepository.flush();

        regionRepository.deleteAll();
        regionRepository.flush();

        // 1. Region 데이터 생성
        Region seoul = new Region();
        seoul.setMajorName("서울특별시");
        seoul.setLevel(1);
        regionRepository.save(seoul);

        Region busan = new Region();
        busan.setMajorName("부산광역시");
        busan.setLevel(1);
        regionRepository.save(busan);

        Region gangnam = new Region();
        gangnam.setMajorName("서울특별시");
        gangnam.setMinorName("강남구");
        gangnam.setParent(seoul);
        gangnam.setLevel(2);
        regionRepository.save(gangnam);

        Region gwanak = new Region();
        gwanak.setMajorName("서울특별시");
        gwanak.setMinorName("관악구");
        gwanak.setParent(seoul);
        gwanak.setLevel(2);
        regionRepository.save(gwanak);

        Region busanjin = new Region();
        busanjin.setMajorName("부산광역시");
        busanjin.setMinorName("부산진구");
        busanjin.setParent(busan);
        busanjin.setLevel(2);
        regionRepository.save(busanjin);

        // 2. University 데이터 생성
        University snu = new University();
        snu.setName("서울대학교");
        universityRepository.save(snu);

        University yonsei = new University();
        yonsei.setName("연세대학교");
        universityRepository.save(yonsei);

        University pusan = new University();
        pusan.setName("부산대학교");
        universityRepository.save(pusan);

        // 3. Scholarship 데이터 생성
        List<Scholarship> scholarships = new ArrayList<>();

        // 3.1 등록금 장학금 (전국 대상)
        Scholarship s1 = createScholarship(
                "국가장학금 1유형",
                "한국장학재단",
                "소득분위별 장학금 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(1),
                "서류 제출 필수",
                3,
                ScholarshipType.TUITION
        );
        scholarships.add(s1);

        // 3.2 생활비 장학금 (서울 지역 한정, 중복 가능)
        Scholarship s2 = createScholarship(
                "서울특별시 장학금",
                "서울장학재단",
                "서울 거주 대학생 생활비 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(2),
                null,
                2,
                ScholarshipType.LIVING_DUPLICATE
        );
        s2.getScholarshipRegions().add(createScholarshipRegion(s2, seoul));
        scholarships.add(s2);

        // 3.3 이자지원 장학금 (저소득층 대상)
        Scholarship s3 = createScholarship(
                "학자금 대출 이자지원",
                "금융진흥원",
                "저소득층 학자금 대출 이자 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(3),
                "소득분위 3분위 이하",
                3,
                ScholarshipType.INTEREST
        );
        scholarships.add(s3);

        // 3.4 생활비 장학금 (서울대 전용, 중복 불가)
        Scholarship s4 = createScholarship(
                "서울대 생활지원 장학금",
                "서울대학교",
                "서울대학교 재학생 생활비 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(4),
                "재학생만 가능",
                0,
                ScholarshipType.LIVING_NO_DUPLICATE
        );
        s4.getScholarshipUniversities().add(createScholarshipUniversity(s4, snu));
        scholarships.add(s4);

        // 3.5 등록금 장학금 (부산 지역 한정)
        Scholarship s5 = createScholarship(
                "부산 지역 등록금 지원",
                "부산시청",
                "부산 거주 대학생 등록금 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(2),
                null,
                4,
                ScholarshipType.TUITION
        );
        s5.getScholarshipRegions().add(createScholarshipRegion(s5, busan));
        scholarships.add(s5);

        // 3.6 생활비 장학금 (연세대 전용, 중복 가능)
        Scholarship s6 = createScholarship(
                "연세대 생활장학금",
                "연세대학교",
                "연세대학교 재학생 생활비 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(3),
                "성적 3.5 이상",
                0,
                ScholarshipType.LIVING_DUPLICATE
        );
        s6.getScholarshipUniversities().add(createScholarshipUniversity(s6, yonsei));
        scholarships.add(s6);

        // 3.7 이자지원 장학금 (강남구 거주자 대상)
        Scholarship s7 = createScholarship(
                "강남구 학자금 이자지원",
                "강남구청",
                "강남구 거주 대학생 대출 이자 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                "주민등록등본 제출",
                0,
                ScholarshipType.INTEREST
        );
        s7.getScholarshipRegions().add(createScholarshipRegion(s7, gangnam));
        scholarships.add(s7);

        // 3.8 등록금 장학금 (부산대 전용)
        Scholarship s8 = createScholarship(
                "부산대 성적우수 장학금",
                "부산대학교",
                "부산대학교 성적우수자 등록금 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(1),
                "직전학기 평점 4.0 이상",
                0,
                ScholarshipType.TUITION
        );
        s8.getScholarshipUniversities().add(createScholarshipUniversity(s8, pusan));
        scholarships.add(s8);

        // 3.9 생활비 장학금 (관악구 거주자, 중복 불가)
        Scholarship s9 = createScholarship(
                "관악구 생활지원금",
                "관악구청",
                "관악구 거주 대학생 생활비 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(2),
                "다른 생활비 장학금 수혜 불가",
                5,
                ScholarshipType.LIVING_NO_DUPLICATE
        );
        s9.getScholarshipRegions().add(createScholarshipRegion(s9, gwanak));
        scholarships.add(s9);

        // 3.10 이자지원 장학금 (전국 대상, 높은 소득분위)
        Scholarship s10 = createScholarship(
                "일반 학자금 이자지원",
                "교육부",
                "일반 대학생 학자금 대출 이자 지원",
                LocalDate.now(),
                LocalDate.now().plusMonths(3),
                "성적 2.5 이상",
                8,
                ScholarshipType.INTEREST
        );
        scholarships.add(s10);

        scholarshipRepository.saveAll(scholarships);
    }

    private Scholarship createScholarship(
            String name,
            String organization,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            String note,
            Integer incomeLevel,
            ScholarshipType type) {
        Scholarship s = new Scholarship();
        s.setName(name);
        s.setOrganization(organization);
        s.setDescription(description);
        s.setApplicationStartDate(startDate);
        s.setApplicationEndDate(endDate);
        s.setNote(note);
        s.setIncomeLevel(incomeLevel);
        s.setType(type);
        s.setScholarshipUniversities(new ArrayList<>());
        s.setScholarshipRegions(new ArrayList<>());
        return s;
    }

    private ScholarshipUniversity createScholarshipUniversity(Scholarship scholarship, University university) {
        ScholarshipUniversity su = new ScholarshipUniversity();

        su.setScholarship(scholarship);
        su.setUniversity(university);
        su.setCreatedAt(LocalDateTime.now());
        return su;
    }

    private ScholarshipRegion createScholarshipRegion(Scholarship scholarship, Region region) {
        ScholarshipRegion sr = new ScholarshipRegion();

        sr.setScholarship(scholarship);
        sr.setRegion(region);
        sr.setCreatedAt(LocalDateTime.now());
        return sr;
    }
}