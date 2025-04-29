package com.avalant.quiz.repository;
import com.avalant.quiz.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, String> {
    Page<Application> findByApplicationNoContainingIgnoreCase(String applicationNo, Pageable pageable);

    @Query("SELECT a FROM Application a WHERE " +
            "(LOWER(a.applicationNo) LIKE LOWER(CONCAT('%', :applicationNo, '%')) OR :applicationNo IS NULL) AND " +
            "(LOWER(a.idNo) LIKE LOWER(CONCAT('%', :idNo, '%')) OR :idNo IS NULL) AND " +
            "(LOWER(a.firstnameEn) LIKE LOWER(CONCAT('%', :firstname, '%')) OR :firstname IS NULL) AND " +
            "(LOWER(a.lastnameEn) LIKE LOWER(CONCAT('%', :lastname, '%')) OR :lastname IS NULL) AND " +
            "(LOWER(a.status) LIKE LOWER(CONCAT('%', :status, '%')) OR :status IS NULL)"
    )
    Page<Application> findByFilters(String applicationNo, String idNo, String firstname, String lastname, String status, Pageable pageable);

    Optional<Application> findByApplicationId(String applicationId);
}