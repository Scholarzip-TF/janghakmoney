package com.example.janghakmoney.user;

import com.example.janghakmoney.user.dto.UserCreateRequest;
import com.example.janghakmoney.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User", description = "유저 API")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    @ApiResponse(responseCode = "200", description = "사용자 생성 성공")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        User createdUser = userService.createUser(request);
        return ResponseEntity.ok(new UserResponse(createdUser));
    }
}
