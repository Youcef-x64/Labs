package com.esihati.service;

import com.esihati.exception.NotFoundException;
import com.esihati.model.Patient;
import com.esihati.model.dto.patient.request.PatientRequestDto;
import com.esihati.model.dto.patient.response.PatientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

    PatientResponseDto register(Patient patient);

    Patient findById(Long id) throws NotFoundException;

    Patient save(Patient patient);

    /**
     * Doctor
     */

    Page<PatientResponseDto> findAllByDoctorId(Long doctorId, Pageable pageable);

}
