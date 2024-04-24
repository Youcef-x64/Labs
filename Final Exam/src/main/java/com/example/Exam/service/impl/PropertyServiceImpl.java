package com.example.Exam.service.impl;

import com.example.Exam.model.Property;
import com.example.Exam.repository.PropertyRepository;
import com.example.Exam.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property findById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Property> findAllByStateOrderByMonthlyRentalRateAsc(String state) {
        return propertyRepository.findAllByStateIgnoreCaseOrderByMonthlyRentalRateAsc(state);
    }

    @Override
    public void save(Property property) {
        propertyRepository.save(property);
    }

}
