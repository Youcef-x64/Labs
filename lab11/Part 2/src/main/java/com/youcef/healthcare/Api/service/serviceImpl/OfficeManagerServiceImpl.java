package com.youcef.healthcare.Api.service.serviceImpl;

import com.youcef.healthcare.Api.models.OfficeManager;
import com.youcef.healthcare.Api.repository.UserRepository;
import com.youcef.healthcare.Api.service.OfficeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeManagerServiceImpl implements OfficeManagerService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public OfficeManager saveOfficeManager(OfficeManager officeManager) {
        return userRepository.save(officeManager);
    }
}
