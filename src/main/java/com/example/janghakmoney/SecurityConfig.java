package com.example.janghakmoney;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // X-Frame-Options 설정: iframe 허용
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)  // 동일 도메인 허용
//                        .frameOptions(frameOptions -> frameOptions.allowFrom("https://example.com"))  // 특정 도메인 허용
                );
        return http.build();
    }
}