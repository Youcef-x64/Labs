package com.esihati.service.impl;

import com.esihati.exception.NotFoundException;
import com.esihati.model.Speciality;
import com.esihati.repository.SpecialityRepository;
import com.esihati.service.SpecialityService;
import org.springframework.stereotype.Service;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    private SpecialityRepository specialityRepository;

    public SpecialityServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Speciality findById(Long id) throws NotFoundException {
        return specialityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(11L, "Speciality Not Found"));
    }

}
