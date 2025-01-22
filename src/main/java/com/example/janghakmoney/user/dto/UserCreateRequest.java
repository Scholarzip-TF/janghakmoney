package com.example.janghakmoney.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
   private String phone;
   private String universityName;
   private String majorRegionName;
   private String minorRegionName;
   private Integer incomeLevel;
   private Boolean hasLivingExpenseScholarship;
   private Boolean hasFullTuitionScholarship;
}
