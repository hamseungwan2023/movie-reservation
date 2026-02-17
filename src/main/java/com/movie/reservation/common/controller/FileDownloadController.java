package com.movie.reservation.common.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/download")
public class FileDownloadController {

    @GetMapping("/schema")
    public ResponseEntity<Resource> downloadSchema() {
        Resource resource = new ClassPathResource("schema.sql");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=movie-reservation-schema.sql")
                .body(resource);
    }
}
