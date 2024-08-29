package com.movie.reservation.domain.cinema.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CinemaRequestDto {

    @NotBlank(message = "영화관 이름은 필수 입력 값 입니다.")
    private String name;

    @NotBlank(message = "도로명 주소는 필수 입력 값 입니다.")
    private String address;

    @NotBlank(message = "시,도는 필수 입력 값 입니다.")
    private String sido;

    @NotBlank(message = "군, 구는 필수 입력 값 입니다.")
    private String gungu;
}
