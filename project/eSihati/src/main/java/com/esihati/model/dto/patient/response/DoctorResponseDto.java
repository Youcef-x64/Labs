package com.esihati.model.dto.patient.response;

import com.esihati.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DoctorResponseDto extends PersonResponseDto {

    private SpecialityResponseDto speciality;
    private DoctorStaticResponseDto _static;

}
