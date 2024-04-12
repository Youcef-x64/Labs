package com.example.demo.service.impl;

import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findById(long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }

}
