package com.youcef.healthcare.Api.service.serviceImpl;

import com.youcef.healthcare.Api.models.Speciality;
import com.youcef.healthcare.Api.repository.SpecialityRepository;
import com.youcef.healthcare.Api.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    public Speciality saveSpeciality(Speciality speciality){
        return specialityRepository.save(speciality);
    }

    @Override
    public Speciality getSpecialityByName(String name) {
        return specialityRepository.findByName(name);
    }

}
