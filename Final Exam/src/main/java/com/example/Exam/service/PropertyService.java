package com.example.Exam.service;

import com.example.Exam.model.Property;

import java.util.List;

public interface PropertyService {

    Property findById(Long id);

    List<Property> findAllByStateOrderByMonthlyRentalRateAsc(String state);

    void save(Property property);
}
