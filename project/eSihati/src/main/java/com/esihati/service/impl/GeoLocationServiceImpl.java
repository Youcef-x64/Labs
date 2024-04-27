package com.esihati.service.impl;

import com.esihati.model.GeoLocation;
import com.esihati.repository.GeoLocationRepository;
import com.esihati.service.GeoLocationService;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    private GeoLocationRepository geoLocationRepository;

    public GeoLocationServiceImpl(GeoLocationRepository geoLocationRepository) {
        this.geoLocationRepository = geoLocationRepository;
    }

    @Override
    public GeoLocation save(GeoLocation geoLocation) {
        return geoLocationRepository.save(geoLocation);
    }
}
