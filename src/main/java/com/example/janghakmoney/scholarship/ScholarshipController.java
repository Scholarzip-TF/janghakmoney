package com.example.janghakmoney.scholarship;

import com.example.janghakmoney.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ScholarshipController {
    private final ScholarshipService scholarshipService;
    private final UserService userService;

    @PostMapping("/users") // 유저 정보를 받아서 넘김
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userService.createUser(userRequestDto);
        return ResponseEntity.ok(UserResponseDto.from(user));
    }

    @GetMapping("/scholarships/possible") // 요건에 맞는 장학금 목록을 보여줌
    public ResponseEntity<List<ScholarshipResponseDto>> getPossibleScholarships(
            @RequestParam Long userId
    ) {
        List<Scholarship> possibleScholarships = scholarshipService.findPossibleScholarships(userId);
        return ResponseEntity.ok(possibleScholarships.stream()
                .map(ScholarshipResponseDto::from)
                .collect(Collectors.toList()));
    }

    @GetMapping("/scholarships/{id}") // user가 선택한 장학금 디테일 창을 보여줌
    public ResponseEntity<ScholarshipDetailDto> getScholarshipDetail(@PathVariable Long id) {
        Scholarship scholarship = scholarshipService.findById(id);
        return ResponseEntity.ok(ScholarshipDetailDto.from(scholarship));
    }
}
