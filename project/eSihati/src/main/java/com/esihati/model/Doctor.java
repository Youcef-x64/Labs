package com.esihati.model;

import jakarta.persistence.*;
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
public class Doctor extends Person {

    private Integer appointmentDuration;

    @ManyToOne
    private Speciality speciality;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
    private List<DoctorShift> shifts;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
    private List<DoctorAppointment> doctorAppointments;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
    private List<MedicalRecord> medicalRecords;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
    private List<DoctorReview> reviews;
    @OneToOne(mappedBy = "doctor", cascade = CascadeType.PERSIST)
    private DoctorStatic _static;


}
