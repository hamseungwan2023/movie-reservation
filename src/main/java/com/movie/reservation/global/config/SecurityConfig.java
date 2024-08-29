package com.movie.reservation.global.config;

import com.movie.reservation.domain.user.service.CustomUserDetailsService;
import com.movie.reservation.global.exception.CustomAccessDeniedHandler;
import com.movie.reservation.global.exception.CustomAuthenticationEntryPoint;
import com.movie.reservation.global.jwt.JwtAuthenticationFilter;
import com.movie.reservation.global.jwt.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtProvider jwtProvider,
                          CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                          CustomAccessDeniedHandler customAccessDeniedHandler,
                          CustomUserDetailsService customUserDetailsService) {
        this.jwtProvider = jwtProvider;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/users/signup").permitAll()
                                .requestMatchers("/api/users/login").permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN") // 어드민 권한 요구
                                .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtProvider, customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}