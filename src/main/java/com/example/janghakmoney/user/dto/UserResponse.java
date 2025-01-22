package com.example.janghakmoney.user.dto;

import com.example.janghakmoney.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UserResponse {
    private UUID id;
    private UUID universityId;      // 장학금 검색에 필요
    private UUID regionId;          // 장학금 검색에 필요
    private Integer incomeLevel;     // 장학금 검색에 필요
    private Boolean hasFullTuitionScholarship;  // 장학금 검색에 필요
    private Boolean hasLivingExpenseScholarship;
    private String phone;           // 장학금 검색에 필요
    private LocalDateTime createdAt;


    // 추가 표시용 정보
    private String universityName;
    private String regionName;

    public UserResponse(User user) {
        this.id = user.getId();
        this.universityId = user.getUniversity().getId();
        this.regionId = user.getRegion().getId();
        this.incomeLevel = user.getIncomeLevel();
        this.hasFullTuitionScholarship = user.getHasFullTuitionScholarship();
        this.hasLivingExpenseScholarship = user.getHasLivingExpenseScholarship();
        this.phone = user.getPhone();
        this.createdAt = user.getCreatedAt();

        this.universityName = user.getUniversity().getName();
        this.regionName = String.format("%s %s",
                user.getRegion().getMajorName(),
                user.getRegion().getMinorName());
    }

}
