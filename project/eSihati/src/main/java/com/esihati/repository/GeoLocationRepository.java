package com.esihati.repository;

import com.esihati.model.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoLocationRepository extends JpaRepository<GeoLocation, Long> {
}
