package com.esihati.service;

import com.esihati.exception.NotFoundException;
import com.esihati.model.Role;

public interface RoleService {

    Role findById(Long id) throws NotFoundException;

    Role findByName(String name) throws NotFoundException;

}
