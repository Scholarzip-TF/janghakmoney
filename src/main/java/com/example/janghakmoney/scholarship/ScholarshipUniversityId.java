package com.example.janghakmoney.scholarship;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ScholarshipUniversityId implements Serializable {
    private Long scholarship;
    private UUID university;
}
