package com.movie.reservation.domain.user.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    @Size(min = 2, max = 10, message = "유저 이름은 2자 이상 10자 이하로 입력해야 합니다.")
    private String username;

    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).*$",
            message = "비밀번호는 특수문자를 포함해야 합니다.")
    private String password;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "휴대폰 번호는 010-0000-0000 형식이어야 합니다.")
    private String phone;
}
