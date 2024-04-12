package com.example.demo.service;

import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    public Address findById(long id);

    public void save(Address address);

    public void delete(Address address);

}
