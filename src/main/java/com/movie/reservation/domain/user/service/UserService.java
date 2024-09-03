package com.movie.reservation.domain.user.service;

import com.movie.reservation.domain.user.dto.request.LoginRequestDto;
import com.movie.reservation.domain.user.dto.request.SignupRequestDto;
import com.movie.reservation.domain.user.dto.request.UpdateUserRequestDto;
import com.movie.reservation.domain.user.dto.response.LoginResponseDto;
import com.movie.reservation.domain.user.dto.response.UserResponseDto;
import com.movie.reservation.domain.user.entity.IsWithDraw;
import com.movie.reservation.domain.user.entity.User;
import com.movie.reservation.domain.user.entity.UserRoleEnum;
import com.movie.reservation.domain.user.repository.UserRepository;
import com.movie.reservation.global.exception.BadRequestException;
import com.movie.reservation.global.exception.NotFoundException;
import com.movie.reservation.global.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public void signup(SignupRequestDto requestDto) {

        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new BadRequestException("이미 등록된 사용자 입니다.");
        }

        final String password = passwordEncoder.encode(requestDto.getPassword());
        final User user = User.builder()
                .email(requestDto.getEmail())
                .username(requestDto.getUsername())
                .phone(requestDto.getPhone())
                .password(password)
                .isWithDraw(IsWithDraw.ACTIVE)
                .role(UserRoleEnum.USER)
                .build();

        userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {

        final User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자 입니다."));

        final String accessToken = jwtProvider.createAccessToken(user.getUsername(), user.getRole());
        final String refreshToken = jwtProvider.createRefreshToken(user.getUsername());

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new BadRequestException("패스워드가 일치하지 않습니다.");
        }

        return LoginResponseDto.builder()
                .username(user.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void updateUser(Long id, UpdateUserRequestDto requestDto) {

        final User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자 입니다."));

        if (requestDto.getUsername() != null) {
            existingUser.updateUsername(requestDto.getUsername());
        }

        if (requestDto.getPassword() != null && !requestDto.getPassword().isEmpty()) {
            final String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
            existingUser.updatePassword(encodedPassword);
        }

        if (requestDto.getPhone() != null) {
            existingUser.updatePhone(requestDto.getPhone());
        }

        userRepository.save(existingUser); // 기존 객체를 업데이트하여 저장
    }

    @Transactional
    public void logout(String username) {

        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자 입니다."));

        user.logout();
    }

    @Transactional
    public void withdraw(String username) {

        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("해당 유저는 존재하지 않습니다."));

        user.withdraw();
    }

    public UserResponseDto getUser(Long id) {

        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 유저는 존재하지 않습니다."));

        return UserResponseDto.builder()
                .email(user.getEmail())
                .phone(user.getPhone())
                .username(user.getUsername())
                .build();
    }

    public User findUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("해당 유저는 존재하지 않습니다."));
    }
}