package com.esihati.model.dto.patient.request;

import com.esihati.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonRequestDto extends UserRequestDto {

    private String firstName;
    private String lastName;
    private LocalDate dob;

    private Gender gender;

}
