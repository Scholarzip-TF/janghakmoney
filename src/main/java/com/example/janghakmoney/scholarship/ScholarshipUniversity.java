package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.University;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "scholarship_university")
@IdClass(ScholarshipUniversityId.class) // 복합키 클래스 연결
public class ScholarshipUniversity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY) // N+1 문제 방지
    @JoinColumn(name = "scholarship_id", nullable = false)
    private Scholarship scholarship;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 연결 생성일
}
