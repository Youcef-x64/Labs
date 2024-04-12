package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dentist extends User {

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;
    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointment;

}
