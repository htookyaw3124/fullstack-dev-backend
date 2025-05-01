package com.avalant.quiz.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatusRequestDto {

    @NotBlank
    private String applicationId;
    @NotBlank
    private String status;

}
