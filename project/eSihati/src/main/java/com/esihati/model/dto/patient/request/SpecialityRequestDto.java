package com.esihati.model.dto.patient.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityRequestDto {

    private Long id;
    private String name;

}