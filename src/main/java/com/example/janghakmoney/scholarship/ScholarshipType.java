package com.example.janghakmoney.scholarship;

import lombok.Getter;

@Getter
public enum ScholarshipType {
    TUITION,                    // 등록금
    LIVING_DUPLICATE,           // 생활비 - 타 장학 중복 가능
    LIVING_NO_DUPLICATE,        // 생활비 - 타 장학 중복 불가
    INTEREST                   // 이자 지원
}