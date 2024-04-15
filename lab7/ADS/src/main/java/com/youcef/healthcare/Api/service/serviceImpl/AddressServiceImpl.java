package com.youcef.healthcare.Api.service.serviceImpl;

import com.youcef.healthcare.Api.models.Address;
import com.youcef.healthcare.Api.repository.AddressRepository;
import com.youcef.healthcare.Api.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
