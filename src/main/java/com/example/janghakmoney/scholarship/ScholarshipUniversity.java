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
public class ScholarshipUniversity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "scholarship_id", nullable = false)
    private Scholarship scholarship;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 연결 생성일
}
