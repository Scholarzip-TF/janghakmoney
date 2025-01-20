package com.example.janghakmoney.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // UUID로 고유 ID 생성
    private String id;

    @Column(nullable = false)
    private String majorName; // 대분류 (예: 서울특별시)

    @Column(nullable = true)
    private String minorName; // 소분류 (예: 강남구)

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true) // 대분류와의 참조 관계
    private Region parent; // 상위 주소 (대분류)

    @Column(nullable = false)
    private Integer level; // 1(대분류), 2(소분류)
}