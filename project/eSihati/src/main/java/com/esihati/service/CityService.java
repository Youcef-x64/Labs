package com.esihati.service;

import com.esihati.exception.NotFoundException;
import com.esihati.model.City;

public interface CityService {

    City findById(Long id) throws NotFoundException;

}
