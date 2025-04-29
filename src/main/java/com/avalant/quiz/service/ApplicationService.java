package com.avalant.quiz.service;

import com.avalant.quiz.dto.ApplicationRequest;
import com.avalant.quiz.dto.ApplicationRequestDto;
import com.avalant.quiz.entity.Application;
import com.avalant.quiz.mapper.ApplicationMapper;
import com.avalant.quiz.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;


    public Application createApplication(ApplicationRequestDto dto) {
        Application entity = applicationMapper.toAppEntity(dto);
        entity.setApplicationId("8837");
        entity.setCreateDate(LocalDateTime.now());
        // Set other default fields if needed
        return applicationRepository.save(entity);
    }
    public Page<Application> getApplications(int page, int size, String applicationNo, String idNo,
                                             String firstname, String lastname, String status) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createDate").descending());

        if (applicationNo != null || idNo != null || firstname != null || lastname != null || status != null) {
            return applicationRepository.findByFilters(applicationNo, idNo, firstname, lastname, status, pageable);
        } else {
            return applicationRepository.findAll(pageable);
        }
    }

    public Application getApplicationById(String applicationId) {
        Optional<Application> applicationOptional =  applicationRepository.findByApplicationId(applicationId);
        if (applicationOptional.isEmpty()) {
          throw new ServerErrorException("Application not found ", new Throwable());
        }
        return applicationOptional.get();
    }
}