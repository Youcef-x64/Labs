package com.esihati.service.impl;

import com.esihati.exception.NotFoundException;
import com.esihati.exception.NotValidException;
import com.esihati.model.DoctorAppointment;
import com.esihati.model.Doctor;
import com.esihati.model.DoctorShift;
import com.esihati.model.Patient;
import com.esihati.model.dto.patient.request.AppointmentRequestDto;
import com.esihati.model.dto.patient.response.DoctorAppointmentResponseDto;
import com.esihati.model.dto.patient.response.PatientAppointmentResponseDto;
import com.esihati.repository.DoctorAppointmentRepository;
import com.esihati.repository.DoctorRepository;
import com.esihati.repository.DoctorShiftRepository;
import com.esihati.repository.PatientRepository;
import com.esihati.service.DoctorAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {

    private DoctorAppointmentRepository doctorAppointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private DoctorShiftRepository doctorShiftRepository;
    private ModelMapper mapper;

    public DoctorAppointmentServiceImpl(DoctorAppointmentRepository doctorAppointmentRepository, DoctorRepository doctorRepository,
                                        PatientRepository patientRepository, DoctorShiftRepository doctorShiftRepository,
                                        ModelMapper mapper) {
        this.doctorAppointmentRepository = doctorAppointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.doctorShiftRepository = doctorShiftRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PatientAppointmentResponseDto> findAllAvailableByDoctorIdAndDate(Long doctorId, LocalDate selectedDate) throws NotFoundException {
        List<PatientAppointmentResponseDto> patientAppointmentResponseDtos = new ArrayList<>();
        List<DoctorAppointment> doctorAppointments = doctorAppointmentRepository.findAllByDoctorIdAndDateOrderByDateTime(doctorId, selectedDate);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(8L, "Doctor Not Found"));
        List<DoctorShift> doctorShiftsSelectedDate =
                doctorShiftRepository
                        .findAllByDoctorIdAndDayId(doctor.getId(), (long) selectedDate.getDayOfWeek().getValue());

        int appointmentDuration = doctor.getAppointmentDuration();
        LocalTime currentTime;
        int possibleAppointmentNumber;
        for(DoctorShift doctorShift: doctorShiftsSelectedDate) {
            currentTime = doctorShift.getStartTime();

            long totalShiftMinutes = doctorShift.getStartTime().until(doctorShift.getEndTime(), ChronoUnit.MINUTES);
            possibleAppointmentNumber = (int) (totalShiftMinutes / doctorShift.getDoctor().getAppointmentDuration());
            for (int i = 0; i < possibleAppointmentNumber; i++) {
                if (currentTime.plusMinutes(appointmentDuration).isBefore(doctorShift.getEndTime())
                        || currentTime.plusMinutes(appointmentDuration).equals(doctorShift.getEndTime())) {
                    patientAppointmentResponseDtos.add(
                            new PatientAppointmentResponseDto(LocalDateTime.of(selectedDate, currentTime)));
                }

                currentTime = currentTime.plusMinutes(appointmentDuration);
            }
        }

        for(DoctorAppointment doctorAppointment : doctorAppointments) {
            patientAppointmentResponseDtos.removeIf(
                    patientAppointmentResponseDto ->
                            patientAppointmentResponseDto.getDateTime().equals(doctorAppointment.getDateTime()) ||
                            patientAppointmentResponseDto.getDateTime().plusMinutes(appointmentDuration)
                                            .isAfter(doctorAppointment.getDateTime()) &&
                            patientAppointmentResponseDto.getDateTime().plusMinutes(appointmentDuration)
                                    .isBefore(doctorAppointment.getDateTime().plusMinutes(appointmentDuration)));
        }

        return patientAppointmentResponseDtos;
    }

    @Override
    public PatientAppointmentResponseDto save(Long patientId, Long doctorId, AppointmentRequestDto appointmentRequestDto) throws NotFoundException, NotValidException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException(9L, "Patient Not Found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(9L, "Doctor Not Found"));

        if (doctorAppointmentRepository.findByDateTime(appointmentRequestDto.getDateTime()).isPresent())
            throw new NotValidException(9L, "Date Already Booked");

        DoctorAppointment doctorAppointment = new DoctorAppointment(null, appointmentRequestDto.getDateTime(), patient, doctor);

        return mapper.map(doctorAppointmentRepository.save(doctorAppointment), PatientAppointmentResponseDto.class);
    }

    @Override
    public PatientAppointmentResponseDto delete(Long id, Long patientId) throws NotFoundException {
        DoctorAppointment doctorAppointment = doctorAppointmentRepository.findByIdAndPatientId(id, patientId)
                .orElseThrow(() -> new NotFoundException(10L, "Appointment Not Found"));

        doctorAppointmentRepository.deleteById(id);

        return mapper.map(doctorAppointment, PatientAppointmentResponseDto.class);
    }

    @Override
    public List<DoctorAppointmentResponseDto> findAllByDoctorIdAndDateOrderByDateTime(Long doctorId, LocalDate selectedDate) throws NotFoundException {
        // TODO Auth and get current doctor
        return doctorAppointmentRepository.findAllByDoctorIdAndDateOrderByDateTime(doctorId, selectedDate)
                .stream()
                .map(doctorAppointment -> mapper.map(doctorAppointment, DoctorAppointmentResponseDto.class))
                .toList();
    }


}
