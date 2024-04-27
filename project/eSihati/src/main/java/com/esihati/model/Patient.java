package com.esihati.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Patient extends Person {

    @OneToMany(mappedBy = "patient")
    private List<DoctorAppointment> doctorAppointments;
    @OneToMany(mappedBy = "patient")
    private List<MedicalRecord> medicalRecords;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
    private List<DoctorReview> doctorReviews;

}
