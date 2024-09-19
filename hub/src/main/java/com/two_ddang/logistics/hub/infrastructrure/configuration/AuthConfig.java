package com.two_ddang.logistics.hub.infrastructrure.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
public class AuthConfig {


    private static final String[] RESOURCE_WHITELIST = {
            "/v3/**", // v3 : SpringBoot 3(없으면 swagger 예시 api 목록 제공)
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",
    };

    // SecurityFilterChain 빈을 정의합니다. 이 메서드는 Spring Security의 보안 필터 체인을 구성합니다.
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            // CSRF 보호를 비활성화합니다. CSRF 보호는 주로 브라우저 클라이언트를 대상으로 하는 공격을 방지하기 위해 사용됩니다.
//            .csrf(csrf -> csrf.disable())
//            // 요청에 대한 접근 권한을 설정합니다.
//            .authorizeRequests(authorize -> authorize
//                // /auth/signIn 경로에 대한 접근을 허용합니다. 이 경로는 인증 없이 접근할 수 있습니다.
//                .requestMatchers(RESOURCE_WHITELIST).permitAll()
//                .requestMatchers("/auth/signin").permitAll()
//                .requestMatchers("/auth/signup").permitAll()
//                // 그 외의 모든 요청은 인증이 필요합니다.
//                .anyRequest().authenticated()
//            )
//            // 세션 관리 정책을 정의합니다. 여기서는 세션을 사용하지 않도록 STATELESS로 설정합니다.
//            .sessionManagement(session -> session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            );
////            .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, jwtUserDetailsService), UsernamePasswordAuthenticationFilter.class);;
//
//        // 설정된 보안 필터 체인을 반환합니다.
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
