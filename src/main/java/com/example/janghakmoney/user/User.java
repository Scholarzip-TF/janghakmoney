package com.example.janghakmoney.user;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 대학교, 지역, 소득 분위, 학점, 기존 장학 수혜 여부, 전화번호, 이메일 받기
    private String university;
    private String region;
    private Integer incomeLevel;
    private Float grade;
    private Boolean hasScholarship;

    private String phone;
    private String email;

    @CreatedDate
    private LocalDateTime createdAt;
}
