package com.esihati.service;

import com.esihati.exception.NotFoundException;
import com.esihati.model.Gender;

public interface GenderService {

    Gender findById(Long id) throws NotFoundException;

    Gender findByName(String name) throws NotFoundException;

}
