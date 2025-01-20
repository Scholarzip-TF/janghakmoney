package com.example.janghakmoney.user;

import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<User> processUserInfo(@RequestBody User user) {
        return ResponseEntity.ok(userService.processUserInfo(user));
    }
}
