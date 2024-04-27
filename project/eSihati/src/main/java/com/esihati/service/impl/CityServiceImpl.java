package com.esihati.service.impl;

import com.esihati.exception.NotFoundException;
import com.esihati.model.City;
import com.esihati.repository.CityRepository;
import com.esihati.service.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findById(Long id) throws NotFoundException {
        return cityRepository.findById(id).orElseThrow(() -> new NotFoundException(1L, "City Not Found"));
    }

}
