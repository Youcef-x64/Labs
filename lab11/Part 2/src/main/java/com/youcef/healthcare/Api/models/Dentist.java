package com.youcef.healthcare.Api.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@DiscriminatorValue("Dentist")
@NoArgsConstructor
public class Dentist extends _User{

    @ManyToOne
    private Speciality speciality;
}
