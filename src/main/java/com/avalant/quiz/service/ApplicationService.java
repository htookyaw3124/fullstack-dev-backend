package com.avalant.quiz.service;

import com.avalant.quiz.dto.ApplicationRequestDto;
import com.avalant.quiz.dto.ApplicationStatusRequestDto;
import com.avalant.quiz.entity.Address;
import com.avalant.quiz.entity.Application;
import com.avalant.quiz.mapper.AddressMapper;
import com.avalant.quiz.mapper.ApplicationMapper;
import com.avalant.quiz.repository.AddressRepository;
import com.avalant.quiz.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerErrorException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public Application createApplication(ApplicationRequestDto dto) {
        // Map DTO to Entity
        Application entity = applicationMapper.toAppEntity(dto);

        // Generate or reuse application ID
        String appId = ObjectUtils.isEmpty(dto.getApplicationId())
                ? UUID.randomUUID().toString()
                : dto.getApplicationId();
        entity.setApplicationId(appId);
        entity.setCreateDate(LocalDateTime.now());

        // Save application
        Application savedApp = applicationRepository.save(entity);

        // Determine address ID (reuse if found, or generate new one)
        String addressId = null;
        if (!ObjectUtils.isEmpty(dto.getApplicationId())) {
            Address existingAddress = addressRepository.findByApplication(savedApp);
            addressId = (existingAddress != null)
                    ? existingAddress.getAddressId()
                    : UUID.randomUUID().toString();
        } else {
            addressId = UUID.randomUUID().toString();
        }

        // Map and save address
        Address address = addressMapper.toAddressEntity(dto);
        address.setAddressId(addressId);
        address.setApplication(savedApp); // make sure application reference is set if needed
        addressRepository.save(address);

        // Return result map
        return savedApp;
    }

    public Page<Application> getApplications(
            int page, int size,
            String applicationNo, String idNo, String firstname, String lastname, String status,
            List<String> sortFields, List<String> directions
    ) {
        // Build dynamic Sort
        Sort sort = Sort.unsorted();
        for (int i = 0; i < sortFields.size(); i++) {
            String field = sortFields.get(i);
            String dir = (i < directions.size()) ? directions.get(i) : "asc";
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(dir), field);
            sort = sort.and(Sort.by(order));
        }

        Pageable pageable = PageRequest.of(page, size, sort);

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

    public Application updateApplicationStatus(ApplicationStatusRequestDto dto) {
        Application application = getApplicationById(dto.getApplicationId());
        application.setStatus(dto.getStatus());
        return applicationRepository.save(application);
    }
}