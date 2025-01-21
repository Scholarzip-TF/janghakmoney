package com.example.janghakmoney.common;

import com.example.janghakmoney.scholarship.ScholarshipUniversity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "University")
public class University {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id; // UUID로 고유 ID 생성

    @Column(nullable = false, unique = true)
    private String name; // 대학교 이름

    // 장학금-대학조건 연결
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScholarshipUniversity> scholarshipUniversities = new ArrayList<>();

}