package com.esihati.service;

import com.esihati.exception.NotFoundException;
import com.esihati.model.State;

public interface StateService {

    State findById(Long id) throws NotFoundException;

}
