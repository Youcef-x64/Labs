package com.esihati.repository;

import com.esihati.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Doctor
     */

    @Query("SELECT p from Patient p " +
            "JOIN p.doctorAppointments da " +
            "JOIN da.doctor d " +
            "WHERE d.id = :doctorId " +
            "ORDER BY p.lastName")
    Page<Patient> findAllByDoctorIdOrderByLastName(Long doctorId, Pageable pageable);

}
