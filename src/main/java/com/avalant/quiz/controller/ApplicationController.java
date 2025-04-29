package com.avalant.quiz.controller;

import com.avalant.quiz.dto.ApplicationRequest;
import com.avalant.quiz.dto.ApplicationRequestDto;
import com.avalant.quiz.dto.ApplicationResponseDto;
import com.avalant.quiz.entity.Application;
import com.avalant.quiz.mapper.ApplicationMapper;
import com.avalant.quiz.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    @PostMapping
    public ResponseEntity<ApplicationResponseDto> createApplication(@Valid @RequestBody ApplicationRequestDto dto) {
        Application app = applicationService.createApplication(dto);
        ApplicationResponseDto response = applicationMapper.toDto(app);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<Application>> getApplications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String applicationNo,
            @RequestParam(required = false) String idNo,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String status) {

        Page<Application> result = applicationService.getApplications(page, size, applicationNo, idNo, firstname, lastname, status);
        return ResponseEntity.ok(result);
    }
}
