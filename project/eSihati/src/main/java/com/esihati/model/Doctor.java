package com.esihati.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @OneToMany(mappedBy = "doctor")
    private List<DoctorReview> reviews;
    @OneToOne(mappedBy = "doctor")
    private DoctorStatic _static;
    @OneToMany(mappedBy = "doctor")
    private List<DoctorShift> shifts;

}
