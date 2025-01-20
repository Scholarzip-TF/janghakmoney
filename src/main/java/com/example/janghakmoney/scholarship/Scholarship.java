package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.common.Region;
import com.example.janghakmoney.common.University;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "scholarships")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // 정보 6개
    @Column(nullable = false)
    private String name; // 장학금명

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = true)
    private University university; // 조건: 특정 대학교

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = true)
    private Region region; // 조건: 특정 지역

    @Column(nullable = false)
    private String organization; // 장학 재단명

    @Column(nullable = false)
    private String description; // 지원 내용

}
