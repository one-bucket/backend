package com.onebucket.global.config;


import com.onebucket.global.auth.jwtAuth.filter.JwtAuthenticationFilter;
import com.onebucket.global.auth.jwtAuth.utils.JwtTokenProvider;
import com.onebucket.global.auth.jwtAuth.utils.JwtTokenValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic((auth) -> auth.disable())
                .csrf((auth) -> auth.disable())
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers("/sign-in").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/test-test").permitAll()
                                .requestMatchers("/token-refresh").permitAll()
                                .requestMatchers("/redis/**").permitAll()
                                .requestMatchers("members/test").hasRole("GUEST")
                .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenValidator),
                        UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
