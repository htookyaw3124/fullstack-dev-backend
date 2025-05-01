package com.avalant.quiz.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @Column(name = "address_id", nullable = false)
    private String addressId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    @JsonBackReference
    private Application application;

    @Column(name = "address1", length = 120)
    private String address1;

    @Column(name = "address2", length = 120)
    private String address2;

    @Column(name = "district", length = 400)
    private String district;

    @Column(name = "province", length = 400)
    private String province;

    @Column(name = "zipcode", length = 10)
    private String zipcode;

    @Column(name = "tel_number")
    private String telNumber;

    @Column(name = "ext_number")
    private String extNumber;

    @Column(name = "fax_number")
    private String faxNumber;
}


