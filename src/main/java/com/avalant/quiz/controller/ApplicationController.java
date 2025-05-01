package com.avalant.quiz.controller;

import com.avalant.quiz.dto.ApplicationRequestDto;
import com.avalant.quiz.dto.ApplicationStatusRequestDto;
import com.avalant.quiz.entity.Application;
import com.avalant.quiz.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<Application> createApplication(@Valid @RequestBody ApplicationRequestDto dto) {
        Application app = applicationService.createApplication(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(app);
    }

    @GetMapping
    public ResponseEntity<Page<Application>> getApplications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String applicationNo,
            @RequestParam(required = false) String idNo,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "applicationDate") List<String> sort,
            @RequestParam(defaultValue = "desc") List<String> direction    ) {

        Page<Application> result = applicationService.getApplications(page, size, applicationNo, idNo, firstname, lastname, status, sort, direction);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/status")
    public ResponseEntity<Application> updateApplicationStatus(@RequestBody ApplicationStatusRequestDto dto) {
     Application application = applicationService.updateApplicationStatus(dto);
     return ResponseEntity.ok(application);
    }
}
