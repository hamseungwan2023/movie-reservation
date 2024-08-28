package com.movie.reservation.global.util;

import com.movie.reservation.global.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class S3Util {

    private static final long MAX_IMAGE_SIZE = 10 * 1024 * 1024; //10mb

    public void validateFileSize(long size, String ext) {
        if (isImageFile(ext) && size > MAX_IMAGE_SIZE) {
            throw new BadRequestException("이미지 파일 크기는 10MB를 초과할 수 없습니다.");
        }
    }

    private boolean isImageFile(String ext) {
        return ext.equals("jpeg") || ext.equals("jpg") || ext.equals("png");
    }

    public String getContentType(String ext) {
        switch (ext) {
            case "jpeg":
                return "image/jpeg";
            case "jpg":
                return "image/jpg";
            case "png":
                return "image/png";

            default:
                throw new BadRequestException("적절한 확장자가 아닙니다.");
        }
    }
}
