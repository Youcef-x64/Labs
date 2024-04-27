package com.esihati.model.dto.patient.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAppointmentResponseDto {

    private Long id;
    private LocalDateTime dateTime;

    public PatientAppointmentResponseDto(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
