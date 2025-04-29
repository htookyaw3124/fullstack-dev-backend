package com.avalant.quiz.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationRequest {

    @NotBlank
    private String applicationId;

    @NotBlank
    private String applicationNo;

    @NotNull
    private LocalDate applicationDate;

    @NotBlank
    private String applicationType;

    @Size(min = 13, max = 13)
    private String idNo;

    private LocalDate expireDate;

    private String idType;
    private String firstnameEn;
    private String lastnameEn;
    private String firstnameTh;
    private String lastnameTh;
    private LocalDate birthDate;
    private String gender;
    private String locationType;
    private String mobileNumber;
    private String optional;
    private String positionType;
    private String jobType;

    private BigDecimal workMonth;
    private BigDecimal workYear;
    private BigDecimal incomeMonth;
    private BigDecimal incomeYear;

    private String addressType;
    private BigDecimal childenNumber;
    private String maritalType;
    private String status;

    private String createBy;
    private LocalDate createDate;
    private String updateBy;
    private LocalDate updateDate;
}
