package com.esihati.model.dto.patient.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorShiftRequestDto {

    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;

    private DayRequestDto day;

}
