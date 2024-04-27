package com.esihati.controller;

import com.esihati.exception.NotFoundException;
import com.esihati.exception.NotValidException;
import com.esihati.model.dto.patient.request.AppointmentRequestDto;
import com.esihati.model.dto.patient.response.DoctorAppointmentResponseDto;
import com.esihati.model.dto.patient.response.PatientAppointmentResponseDto;
import com.esihati.service.DoctorAppointmentService;
import com.esihati.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private DoctorService doctorService;
    private DoctorAppointmentService doctorAppointmentService;

    public AppointmentController(DoctorService doctorService, DoctorAppointmentService doctorAppointmentService) {
        this.doctorService = doctorService;
        this.doctorAppointmentService = doctorAppointmentService;
    }

    /**
     * Patient End Points
     */
    @GetMapping("/p/doctors/{doctor_id}")
    public ResponseEntity<List<PatientAppointmentResponseDto>> findAllAvailableAppointmentByDoctorIdAndDate(
            @PathVariable("doctor_id") Long doctorId, @RequestParam("date") LocalDate selectedDate)
            throws NotFoundException {
        return ResponseEntity.ok(doctorAppointmentService.findAllAvailableByDoctorIdAndDate(doctorId, selectedDate));
    }

    @PostMapping("/p/doctors/{doctor_id}")
    public ResponseEntity<PatientAppointmentResponseDto> book(
            @PathVariable("doctor_id") Long doctorId, @RequestBody AppointmentRequestDto appointmentRequestDto)
            throws NotFoundException, NotValidException {
        // TODO change patient id with token user
        return ResponseEntity.ok(doctorAppointmentService.save(5L, doctorId, appointmentRequestDto));
    }

    @DeleteMapping("/p")
    public ResponseEntity<PatientAppointmentResponseDto> cancel(
            @RequestBody AppointmentRequestDto appointmentRequestDto)
            throws NotFoundException {
        // TODO change patient id with token user
        return ResponseEntity.ok(doctorAppointmentService.delete(appointmentRequestDto.getId(), 5L));
    }

    /**
     * Doctor End Points
     */
    @GetMapping("/d")
    public ResponseEntity<List<DoctorAppointmentResponseDto>> findAllByDate(@RequestParam("date") LocalDate selectedDate)
            throws NotFoundException {
        return ResponseEntity.ok(doctorAppointmentService
                .findAllByDoctorIdAndDateOrderByDateTime(2L, selectedDate));
    }

}
