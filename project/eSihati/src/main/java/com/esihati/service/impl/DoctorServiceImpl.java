package com.esihati.service.impl;

import com.esihati.model.Doctor;
import com.esihati.model.dto.patient.response.DoctorResponseDto;
import com.esihati.repository.DoctorRepository;
import com.esihati.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    private ModelMapper mapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper mapper) {
        this.doctorRepository = doctorRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<DoctorResponseDto> findAllOrderByNearest(Double latitude, Double longitude, Pageable pageable) {
        return doctorRepository.findAllOrderByNearest(latitude, longitude, pageable)
                .map(doctor -> mapper.map(doctor, DoctorResponseDto.class));
    }

    @Override
    public Page<DoctorResponseDto> findAllOrderByRate(Pageable pageable) {
        return doctorRepository.findAllOrderByRate(pageable)
                .map(doctor -> mapper.map(doctor, DoctorResponseDto.class));
    }

    @Override
    public Page<DoctorResponseDto> findAllOrderByNearestFilterSpeciality(Double latitude, Double longitude, Long specialityId, Pageable pageable) {
        return doctorRepository.findAllOrderByNearestFilterSpeciality(latitude, longitude, specialityId, pageable)
                .map(doctor -> mapper.map(doctor, DoctorResponseDto.class));
    }

    @Override
    public Page<DoctorResponseDto> findAllOrderByRateFilterBySpeciality(Long specialityId, Pageable pageable) {
        return doctorRepository.findAllOrderByRateFilterBySpeciality(specialityId, pageable)
                .map(doctor -> mapper.map(doctor, DoctorResponseDto.class));
    }

    @Override
    public DoctorResponseDto register(Doctor doctor) {
        return mapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }

}
