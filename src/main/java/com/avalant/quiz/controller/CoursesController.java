package com.avalant.quiz.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/public/courses")
public class CoursesController {
    @GetMapping("/stream/{file}")
    public ResponseEntity<Resource> stream() throws IOException {
        File videoFile = new File("uploads/test.mp4" ); // e.g., output.m3u8
        return ResponseEntity.ok()
                .contentType(MediaTypeFactory.getMediaType("test.mp4").orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(new FileSystemResource(videoFile));
    }
}
