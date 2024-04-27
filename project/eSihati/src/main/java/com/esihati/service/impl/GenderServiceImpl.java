package com.esihati.service.impl;

import com.esihati.exception.NotFoundException;
import com.esihati.model.Gender;
import com.esihati.repository.GenderRepository;
import com.esihati.service.GenderService;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {

    private GenderRepository genderRepository;

    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public Gender findById(Long id) throws NotFoundException {
        return genderRepository.findById(id).orElseThrow(() -> new NotFoundException(4L, "Gender Not Found"));
    }

    @Override
    public Gender findByName(String name) throws NotFoundException {
        return genderRepository.findByName(name).orElseThrow(() -> new NotFoundException(4L, "Gender Not Found"));
    }
}
