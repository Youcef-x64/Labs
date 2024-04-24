package com.youcef.healthcare.Api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Patient")
public class Patient extends _User{
    private LocalDate dateOfBirth;
    @OneToOne
    private Address address;
}
