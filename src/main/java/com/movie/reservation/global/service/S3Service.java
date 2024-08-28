package com.movie.reservation.global.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.movie.reservation.global.exception.BadRequestException;
import com.movie.reservation.global.util.S3Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3Service {
    @Value("${cloud.aws.s3.bucket.name}")
    private String bucket;
    private final AmazonS3 s3;
    private final S3Util s3Util;

    public S3Service(AmazonS3 s3, S3Util s3Util) {
        this.s3 = s3;
        this.s3Util = s3Util;
    }

    public String s3Upload(MultipartFile file) throws IOException {
        if (file != null) {
            try {
                String fileName = file.getOriginalFilename();
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase(); // 확장자를 소문자로 변환
                String contentType = s3Util.getContentType(ext);

                s3Util.validateFileSize(file.getSize(), ext);
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(contentType);
                s3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), metadata));
                return s3.getUrl(bucket, fileName).toString();
            } catch (AmazonS3Exception e) {
                throw new BadRequestException("S3 업로드 중 오류가 발생했습니다.");
            } catch (SdkClientException e) {
                e.printStackTrace();
            }
        }
        return "null";
    }
}
