package com.example.Exam.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRetirementDto {

    private String firstName;
    private String lastName;
    private Double yearlySalary;
    private String referenceNumber;
    private LocalDate enrollmentDate;
    private LocalDate retirementDate;
    private Double monthlyContribution;

}
