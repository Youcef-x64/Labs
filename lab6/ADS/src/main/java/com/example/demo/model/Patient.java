package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends User {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointment;

}
