package com.esihati.model.dto.patient.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAppointmentResponseDto {

    private Long id;
    private LocalDateTime dateTime;

    private PatientResponseDto patient;

}
