package com.esihati.model.dto.patient.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDto {

    private Long id;
    private LocalDateTime dateTime;

    public AppointmentRequestDto(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
