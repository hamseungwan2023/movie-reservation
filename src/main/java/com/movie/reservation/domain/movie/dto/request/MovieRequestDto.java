package com.movie.reservation.domain.movie.dto.request;

import com.movie.reservation.domain.movie.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MovieRequestDto {

    @NotBlank(message = "제목은 필수 입력 값 입니다.")
    @Size(max = 20, message = "제목은 20자까지 가능합니다.")
    private String title;

    @NotBlank(message = "영화 설명은 필수 입력 값 입니다.")
    private String description;

    @NotBlank(message = "장르는 필수 입력 값 입니다.")
    private String genre;

    @NotNull(message = "러닝 타임은 필수 입력 값 입니다.")
    private Integer duration;
}
