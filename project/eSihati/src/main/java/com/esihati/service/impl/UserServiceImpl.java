package com.esihati.service.impl;

import com.esihati.model._User;
import com.esihati.repository.UserRepository;
import com.esihati.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public _User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
