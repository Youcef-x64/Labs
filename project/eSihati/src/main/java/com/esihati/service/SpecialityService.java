package com.esihati.service;

import com.esihati.exception.NotFoundException;
import com.esihati.model.Speciality;

public interface SpecialityService {

    Speciality findById(Long id) throws NotFoundException;

}
