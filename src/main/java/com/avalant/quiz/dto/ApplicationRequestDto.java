package com.avalant.quiz.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ApplicationRequestDto {

    @NotBlank
    private String applicationNo;

    @NotNull
    private LocalDate applicationDate;

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

    @NotBlank
    private String status;

    private String createBy;
    private String updateBy;
}

