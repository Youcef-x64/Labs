package com.esihati.service.impl;

import com.esihati.exception.NotFoundException;
import com.esihati.model.Role;
import com.esihati.repository.RoleRepository;
import com.esihati.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findById(Long id) throws NotFoundException {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException(3L, "Role Not Found"));
    }

    @Override
    public Role findByName(String name) throws NotFoundException {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundException(3L, "Role Not Found"));
    }
}
