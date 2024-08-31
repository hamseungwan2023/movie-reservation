package com.movie.reservation.domain.cinema.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScreenRequestDto {

    @NotBlank(message = "이름은 필수 입력 값 입니다.")
    private String name;

    @NotNull(message = "총 좌석은 필수 입력 값 입니다.")
    private Integer totalSeat;
}
