package com.esihati.model.dto.patient.request;

import com.esihati.model.dto.patient.response.SpecialityResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DoctorRequestDto extends PersonRequestDto {

    private SpecialityRequestDto speciality;
    private List<DoctorShiftRequestDto> shifts;

}