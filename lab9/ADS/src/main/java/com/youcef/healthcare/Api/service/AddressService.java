package com.youcef.healthcare.Api.service;

import com.youcef.healthcare.Api.models.Address;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AddressService {
    List<Address> getAll();
}
