package com.esihati.service;

import com.esihati.model.Doctor;
import com.esihati.model.dto.patient.response.DoctorResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {

    Page<DoctorResponseDto> findAllOrderByNearest(Double latitude, Double longitude, Pageable pageable);

    Page<DoctorResponseDto> findAllOrderByRate(Pageable pageable);

    Page<DoctorResponseDto> findAllOrderByNearestFilterSpeciality(Double latitude, Double longitude, Long specialityId, Pageable pageable);

    Page<DoctorResponseDto> findAllOrderByRateFilterBySpeciality(Long specialityId, Pageable pageable);

    DoctorResponseDto register(Doctor doctor);

}
