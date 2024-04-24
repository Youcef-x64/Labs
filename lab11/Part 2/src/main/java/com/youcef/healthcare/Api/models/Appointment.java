package com.youcef.healthcare.Api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Surgery surgery;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Dentist dentist;
    private LocalDateTime dateTime;
    private boolean isApproved;
}
