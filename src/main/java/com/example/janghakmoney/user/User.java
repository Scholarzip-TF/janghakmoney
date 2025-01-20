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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university; // 대학교 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region; // 거주 지역 정보

    @Column(nullable = false)
    private Integer incomeLevel; // 소득 분위 (0~10)

    @Column(nullable = false)
    private Boolean hasLivingExpenseScholarship; // 생활비 장학금 수혜 여부

    @Column(nullable = false)
    private Boolean hasFullTuitionScholarship; // 등록금 전액 장학금 수혜 여부

    @Column(nullable = false, length = 15)
    private String phone; // 사용자 전화번호

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 생성일
}
