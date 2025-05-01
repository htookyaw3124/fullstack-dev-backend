package com.avalant.quiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @Column(name = "application_id", nullable = false)
    private String applicationId;

    @Column(name = "application_no", length = 32)
    private String applicationNo;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "application_type", length = 10, nullable = true)
    private String applicationType;

    @Column(name = "id_no", length = 13)
    private String idNo;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "id_type", length = 10)
    private String idType;

    @Column(name = "firstname_en", length = 120)
    private String firstnameEn;

    @Column(name = "lastname_en", length = 120)
    private String lastnameEn;

    @Column(name = "firstname_th", length = 120)
    private String firstnameTh;

    @Column(name = "lastname_th", length = 120)
    private String lastnameTh;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "location_type", length = 10)
    private String locationType;

    @Column(name = "mobile_number", length = 10, nullable = true)
    private String mobileNumber;

    @Column(name = "optional", length = 10)
    private String optional;

    @Column(name = "position_type", length = 10, nullable = true)
    private String positionType;

    @Column(name = "job_type", length = 10)
    private String jobType;

    @Column(name = "work_month", precision = 10, scale = 2, nullable = true)
    private BigDecimal workMonth;

    @Column(name = "work_year", precision = 10, scale = 2, nullable = true)
    private BigDecimal workYear;

    @Column(name = "income_month", precision = 10, scale = 2, nullable = true)
    private BigDecimal incomeMonth;

    @Column(name = "income_year", precision = 10, scale = 2)
    private BigDecimal incomeYear;

    @Column(name = "residence", length = 10, nullable = true)
    private String residence;

    @Column(name = "childen_number", precision = 10, scale = 2, nullable = true)
    private Integer childenNumber;

    @Column(name = "marital_type", length = 10)
    private String maritalType;

    @Column(name = "status", length = 25)
    private String status = "Draft";

    @Column(name = "create_by", length = 50, nullable = true)
    private String createBy;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "update_by", length = 50, nullable = true)
    private String updateBy;

    @Column(name = "update_date")
    private LocalDateTime updateDate = LocalDateTime.now();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();

    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
}
