package com.youcef.healthcare.Api.service.serviceImpl;

import com.youcef.healthcare.Api.models.Dentist;
import com.youcef.healthcare.Api.models.Speciality;
import com.youcef.healthcare.Api.repository.SpecialityRepository;
import com.youcef.healthcare.Api.repository.UserRepository;
import com.youcef.healthcare.Api.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DentistServiceImpl implements DentistService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public Dentist saveDentist(Dentist dentist) {
            Speciality persistedSpeciality  = specialityRepository.findByName(dentist.getSpeciality().getName());
            if(persistedSpeciality == null){
                Speciality speciality = new Speciality();
                speciality.setName(dentist.getSpeciality().getName());
                Speciality savedSpeciality = specialityRepository.save(speciality);
                dentist.setSpeciality(savedSpeciality);
            }else{
                dentist.setSpeciality(persistedSpeciality);
            }
        return userRepository.save(dentist);
    }
}
