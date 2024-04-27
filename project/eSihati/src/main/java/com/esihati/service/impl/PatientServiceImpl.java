package com.esihati.service.impl;

import com.esihati.exception.NotFoundException;
import com.esihati.model.Patient;
import com.esihati.model.dto.patient.response.PatientResponseDto;
import com.esihati.repository.PatientRepository;
import com.esihati.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private ModelMapper mapper;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper mapper) {
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    @Override
    public PatientResponseDto register(Patient patient) {
        return mapper.map(patientRepository.save(patient), PatientResponseDto.class);
    }

    @Override
    public Patient findById(Long id) throws NotFoundException {
        return patientRepository.findById(id).orElseThrow(() -> new NotFoundException(5L, "Patient Not Found"));
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Page<PatientResponseDto> findAllByDoctorId(Long doctorId, Pageable pageable) {
        return patientRepository.findAllByDoctorIdOrderByLastName(doctorId, pageable)
                .map(patient -> mapper.map(patient, PatientResponseDto.class));
    }

}
