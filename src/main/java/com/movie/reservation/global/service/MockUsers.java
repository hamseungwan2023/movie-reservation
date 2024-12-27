//package com.movie.reservation.global.service;
//
//import com.movie.reservation.domain.user.dto.request.SignupRequestDto;
//import com.movie.reservation.domain.user.service.UserService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MockUsers implements CommandLineRunner {
//
//    private final UserService userService;
//
//    public MockUsers(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i = 1; i < 1000; i++) {
//            SignupRequestDto signupRequestDto = SignupRequestDto.builder()
//                    .email("test@email.com" + i)
//                    .password("testPassword" + i)
//                    .username("testUser" + i)
//                    .phone("휴대폰번호" + i)
//                    .build();
//            userService.signup(signupRequestDto);
//
//        }
//    }
//}
