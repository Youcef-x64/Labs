package com.youcef.healthcare.Api.service;

import com.youcef.healthcare.Api.models.Speciality;

public interface SpecialityService {
    Speciality saveSpeciality(Speciality speciality);
    Speciality getSpecialityByName (String name);
}
