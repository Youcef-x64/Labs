package com.esihati.model.dto;

import com.esihati.model.Gender;
import jakarta.persistence.ManyToOne;

public class PersonDto {

    private String firstName;
    private String lastName;

    private Gender gender;

}
