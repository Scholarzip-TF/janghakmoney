package com.example.janghakmoney.user;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.RegionRepository;
import com.example.janghakmoney.common.University;
import com.example.janghakmoney.common.UniversityRepository;
import com.example.janghakmoney.user.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;
    private final RegionRepository regionRepository;

    public User createUser(UserCreateRequest request) {
        // 대학교 조회 또는 생성
        University university = universityRepository.findByName(request.getUniversityName())
                .orElseGet(() -> {
                    University newUniversity = new University();
                    newUniversity.setName(request.getUniversityName());
                    return universityRepository.save(newUniversity);
                });

        // 지역 조회 또는 생성
        Region region = regionRepository.findByMajorNameAndMinorName(
                        request.getMajorRegionName(),
                        request.getMinorRegionName()
                )
                .orElseGet(() -> {
                    Region newRegion = new Region();
                    newRegion.setMajorName(request.getMajorRegionName());
                    newRegion.setMinorName(request.getMinorRegionName());

                    // 부모 지역 설정 로직
                    if (request.getMinorRegionName() != null) {
                        Region parentRegion = regionRepository.findByMajorName(request.getMajorRegionName())
                                .orElseGet(() -> {
                                    Region newParentRegion = new Region();
                                    newParentRegion.setMajorName(request.getMajorRegionName());
                                    newParentRegion.setLevel(1);
                                    return regionRepository.save(newParentRegion);
                                });
                        newRegion.setParent(parentRegion);
                        newRegion.setLevel(2);
                    } else {
                        newRegion.setLevel(1);
                    }

                    return regionRepository.save(newRegion);
                });

        // 사용자 엔티티 생성
        User user = new User();
        user.setPhone(request.getPhone());
        user.setUniversity(university);
        user.setRegion(region);
        user.setIncomeLevel(request.getIncomeLevel());
        user.setHasScholarship(request.getHasLivingExpenseScholarship());
        user.setHasFullTuition(request.getHasFullTuitionScholarship());

        return userRepository.save(user);
    }
}
