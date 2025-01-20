package com.example.janghakmoney.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // UUID로 고유 ID 생성
    private String id;

    @Column(nullable = false, unique = true)
    private String name; // 대학교 이름

    // 장학금-대학조건 연결
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScholarshipUniversity> scholarshipUniversities = new ArrayList<>();

}