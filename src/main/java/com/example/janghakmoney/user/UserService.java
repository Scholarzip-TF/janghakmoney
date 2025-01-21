package com.example.janghakmoney.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User processUserInfo(User user) {
        // 이메일 or 전화번호로만 기존 사용자 확인
        return userRepository.findByPhone(user.getPhone())
                // 기존 사용자면 DB 저장 없이 입력받은 검색 정보 그대로 반환
                .orElseGet(() ->
                        // 새로운 사용자만 DB에 저장
                        userRepository.save(user)
                );
    }
}
