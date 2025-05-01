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

    private String residence;
    private BigDecimal childenNumber;
    private String maritalType;

    private String status ="Draft";

    private String createBy;
    private String updateBy;
    private String applicationId;

    @NotBlank
    private String address1;
    @NotBlank
    private String address2;
    @NotBlank
    private String province;
    @NotBlank
    private String zipcode;
    private String district;
    private String faxNumber;
    private String telNumber;
    private String extNumber;
}

