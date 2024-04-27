package com.esihati.model.dto.patient.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorStaticResponseDto {

    private Long viewNumber;
    private Long visitorNumber;
    private Float rate;

}
