package com.esihati.service;

import com.esihati.exception.NotFoundException;
import com.esihati.exception.NotValidException;
import com.esihati.model.dto.patient.request.AppointmentRequestDto;
import com.esihati.model.dto.patient.response.DoctorAppointmentResponseDto;
import com.esihati.model.dto.patient.response.PatientAppointmentResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface DoctorAppointmentService {

    /**
     * Patient
     */
    List<PatientAppointmentResponseDto> findAllAvailableByDoctorIdAndDate(Long doctorId, LocalDate selectedDate)
            throws NotFoundException;

    PatientAppointmentResponseDto save(Long patientId, Long doctorId, AppointmentRequestDto dateTime)
            throws NotFoundException, NotValidException;

    PatientAppointmentResponseDto delete(Long id, Long patientId)
            throws NotFoundException;

    /**
     * Doctor
     */

    List<DoctorAppointmentResponseDto> findAllByDoctorIdAndDateOrderByDateTime(Long doctorId, LocalDate selectedDate)
            throws NotFoundException;

}
