package com.esihati.service.impl;

import com.esihati.exception.NotFoundException;
import com.esihati.model.State;
import com.esihati.repository.StateRepository;
import com.esihati.service.StateService;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State findById(Long id) throws NotFoundException {
        return stateRepository.findById(id).orElseThrow(() -> new NotFoundException(2L, "State Not Found"));
    }

}
