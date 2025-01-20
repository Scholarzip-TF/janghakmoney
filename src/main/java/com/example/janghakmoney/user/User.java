package com.example.janghakmoney.user;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "form_responses")
@EntityListeners(AuditingEntityListener.class) // Auditing 활성화
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university; // 대학교 정보

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region; // 거주 지역 정보

    @Column(nullable = false)
    private Integer incomeLevel; // 소득 분위 (0~10)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type; // 지원유형 (ENUM)

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 생성일

    // 지원유형 ENUM 정의
    public enum Type {
        TUITION,                // 등록금
        LIVING_DUPLICATE,       // 생활비 - 타 장학 중복 O
        LIVING_NO_DUPLICATE,    // 생활비 - 타 장학 중복 X
        INTEREST_SUPPORT        // 이자지원
    }
}