package com.esihati.repository;

import com.esihati.model.DoctorAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Long> {

    @Query("SELECT da from DoctorAppointment da " +
            "JOIN da.doctor d " +
            "WHERE d.id = :doctorId AND DATE(da.dateTime) = :selectedDate " +
            "ORDER BY da.dateTime")
    List<DoctorAppointment> findAllByDoctorIdAndDateOrderByDateTime(Long doctorId, LocalDate selectedDate);

    Optional<DoctorAppointment> findByDateTime(LocalDateTime dateTime);

    Optional<DoctorAppointment> findByIdAndPatientId(Long id, Long patientId);

}
