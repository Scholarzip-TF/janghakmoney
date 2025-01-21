package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.RegionRepository;
import com.example.janghakmoney.common.University;
import com.example.janghakmoney.common.UniversityRepository;
import com.example.janghakmoney.scholarship.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class ScholarshipSetupTest {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @Test
    @Order(1)
    @Commit
    void setupInitialData() {
        // 지역 데이터 생성
        List<Region> regions = createRegions();
        regionRepository.saveAll(regions);

        // 대학교 데이터 생성
        List<University> universities = createUniversities();
        universityRepository.saveAll(universities);

        printAllUniversities();
        printAllRegions();
    }

    private List<Region> createRegions() {
        List<Region> regions = new ArrayList<>();

        // 광역시/도 레벨 1 지역
        Region seoul = createRegion("서울", null, null, 1);
        Region gyeonggi = createRegion("경기", null, null, 1);
        Region busan = createRegion("부산", null, null, 1);
        Region daejeon = createRegion("대전", null, null, 1);
        Region daegu = createRegion("대구", null, null,1);

        // 서울 구 지역, level2
        Region gangnamgu = createRegion("서울", "강남구",  seoul, 2);
        Region seochogu = createRegion("서울", "서초구", seoul, 2);

        // 경기도 시/군 지역, level2
        Region suwon = createRegion("경기도", "수원시", gyeonggi, 2);
        Region incheon = createRegion("경기도", "과천시", gyeonggi, 2);

        regions.addAll(Arrays.asList(
                seoul, gyeonggi, busan, daejeon, daegu,
                gangnamgu, seochogu, suwon, incheon
        ));

        return regions;
    }

    private Region createRegion(String majorName, String minorName, Region parentRegion, int level) {
        Region region = new Region();

        region.setMajorName(majorName);
        region.setMinorName(minorName);
        region.setParent(parentRegion);
        region.setLevel(level);
        return region;
    }

    private List<University> createUniversities() {
        List<University> universities = new ArrayList<>();

        universities.add(createUniversity("서울대학교"));
        universities.add(createUniversity("연세대학교"));
        universities.add(createUniversity("고려대학교"));
        universities.add(createUniversity("카이스트"));
        universities.add(createUniversity("포스텍"));
        universities.add(createUniversity("성균관대학교"));
        universities.add(createUniversity("중앙대학교"));
        universities.add(createUniversity("한양대학교"));
        universities.add(createUniversity("건국대학교"));
        universities.add(createUniversity("경희대학교"));

        return universities;
    }

    private University createUniversity(String name) {
        University university = new University();

        university.setName(name);
        return university;
    }

    private void printAllUniversities() {
        List<University> allUniversities = universityRepository.findAll();
        System.out.println("All Universities:");
        allUniversities.forEach(u -> System.out.println(u.getName()));
    }

    private void printAllRegions() {
        List<Region> allRegions = regionRepository.findAll();
        allRegions.forEach(r -> System.out.println(r.getMajorName() + " " + r.getMinorName()));


    }

    @Test
    @Order(2)
    void createDummyScholarships() {
        // 기존 setupInitialData 메서드로 데이터 생성 후 실행

        // 서울대 장학금 1
        Scholarship scholarship1 = new Scholarship();
        scholarship1.setName("서울대 성적우수 장학금");
        scholarship1.setOrganization("서울대학교 장학재단");
        scholarship1.setDescription("성적 우수 학생 지원 장학금");
        scholarship1.setApplicationStartDate(LocalDate.of(2024, 1, 1));
        scholarship1.setApplicationEndDate(LocalDate.of(2024, 1, 31));
        scholarship1.setIncomeLevel(3);
        scholarship1.setType(ScholarshipType.TUITION);

        // 서울대 대학 조건 연결
        University seoulUniversity = universityRepository.findByName("서울대학교")
                .orElseThrow(() -> new EntityNotFoundException("서울대학교를 찾을 수 없습니다"));

        ScholarshipUniversity scholarshipUniversity1 = new ScholarshipUniversity();
        scholarshipUniversity1.setUniversity(seoulUniversity);
        scholarshipUniversity1.setScholarship(scholarship1);
        scholarship1.getScholarshipUniversities().add(scholarshipUniversity1);

        // 서울 지역 조건 연결
        Region seoulRegion = regionRepository.findByMajorName("서울")
                .orElseThrow(() -> new EntityNotFoundException("서울 지역을 찾을 수 없습니다"));

        ScholarshipRegion scholarshipRegion1 = new ScholarshipRegion();
        scholarshipRegion1.setRegion(seoulRegion);
        scholarshipRegion1.setScholarship(scholarship1);
        scholarship1.getScholarshipRegions().add(scholarshipRegion1);

        // 카이스트 장학금 2
        Scholarship scholarship2 = new Scholarship();
        scholarship2.setName("카이스트 연구 장학금");
        scholarship2.setOrganization("카이스트 연구재단");
        scholarship2.setDescription("우수 연구 학생 지원 장학금");
        scholarship2.setApplicationStartDate(LocalDate.of(2024, 2, 1));
        scholarship2.setApplicationEndDate(LocalDate.of(2024, 2, 28));
        scholarship2.setIncomeLevel(1);
        scholarship2.setType(ScholarshipType.LIVING_DUPLICATE);

        // 카이스트 대학 조건 연결
        University kaistUniversity = universityRepository.findByName("카이스트")
                .orElseThrow(() -> new EntityNotFoundException("카이스트를 찾을 수 없습니다"));

        ScholarshipUniversity scholarshipUniversity2 = new ScholarshipUniversity();
        scholarshipUniversity2.setUniversity(kaistUniversity);
        scholarshipUniversity2.setScholarship(scholarship2);
        scholarship2.getScholarshipUniversities().add(scholarshipUniversity2);

        // 대전 지역 조건 연결
        Region daejeonRegion = regionRepository.findByMajorName("대전")
                .orElseThrow(() -> new EntityNotFoundException("대전 지역을 찾을 수 없습니다"));

        ScholarshipRegion scholarshipRegion2 = new ScholarshipRegion();
        scholarshipRegion2.setRegion(daejeonRegion);
        scholarshipRegion2.setScholarship(scholarship2);
        scholarship2.getScholarshipRegions().add(scholarshipRegion2);

        // 장학금 저장
        scholarshipRepository.saveAll(List.of(scholarship1, scholarship2));
    }


}