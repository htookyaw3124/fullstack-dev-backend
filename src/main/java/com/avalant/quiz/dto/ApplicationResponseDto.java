package com.avalant.quiz.dto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationResponseDto {

    private String applicationId;
    private String applicationNo;
    private LocalDate applicationDate;
    private String applicationType;
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
    private LocalDateTime createDate;
    private String updateBy;
    private LocalDateTime updateDate;
}
