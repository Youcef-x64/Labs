package com.youcef.healthcare.Api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Patient")
public class Patient extends _User{
    private String dateOfBirth;
    @OneToOne
    private Address address;
}
