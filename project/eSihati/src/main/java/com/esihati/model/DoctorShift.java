package com.esihati.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DoctorShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @ManyToOne
    private Day day;
    @ManyToOne
    private Doctor doctor;

}
