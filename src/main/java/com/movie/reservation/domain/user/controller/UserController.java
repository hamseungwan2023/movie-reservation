package com.movie.reservation.domain.user.controller;

import com.movie.reservation.domain.user.dto.request.LoginRequestDto;
import com.movie.reservation.domain.user.dto.request.SignupRequestDto;
import com.movie.reservation.domain.user.dto.request.UpdateUserRequestDto;
import com.movie.reservation.domain.user.dto.response.LoginResponseDto;
import com.movie.reservation.domain.user.dto.response.UserResponseDto;
import com.movie.reservation.domain.user.service.UserService;
import com.movie.reservation.global.dto.DataResponse;
import com.movie.reservation.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/signup")
    public ResponseEntity<MessageResponse> signup(@Valid @RequestBody SignupRequestDto requestDto) {

        userService.signup(requestDto);

        return ResponseEntity.ok(new MessageResponse(200, "회원가입 성공"));
    }

    @PostMapping("/users/login")
    public ResponseEntity<DataResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok
                (new DataResponse<>(200, "로그인 성공", userService.login(requestDto)));
    }

    @PostMapping("/user/logout")
    public ResponseEntity<MessageResponse> logout(@AuthenticationPrincipal UserDetails userDetails) {

        userService.logout(userDetails.getUsername());

        return ResponseEntity.ok(new MessageResponse(200, "로그아웃 성공"));
    }

    @DeleteMapping("/users/withdraw")
    public ResponseEntity<MessageResponse> withdraw(@AuthenticationPrincipal UserDetails userDetails) {

        userService.withdraw(userDetails.getUsername());

        return ResponseEntity.ok(new MessageResponse(200, "탈퇴 성공"));
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<MessageResponse> updateUser(@PathVariable("id") Long userId,
                                                      @Valid @RequestBody UpdateUserRequestDto requestDto) {

        userService.updateUser(userId, requestDto);

        return ResponseEntity.ok(new MessageResponse(200, "프로필 수정 성공"));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<DataResponse<UserResponseDto>> getUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok
                (new DataResponse<>(200, "프로필 조회 성공", userService.getUser(userId)));
    }
}

