package com.youcef.healthcare.Api.service.serviceImpl;

import com.youcef.healthcare.Api.exception.NotFoundException;
import com.youcef.healthcare.Api.models.Address;
import com.youcef.healthcare.Api.models.Patient;
import com.youcef.healthcare.Api.models._User;
import com.youcef.healthcare.Api.repository.AddressRepository;
import com.youcef.healthcare.Api.repository.UserRepository;
import com.youcef.healthcare.Api.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional
    public Patient savePatient(Patient patient) {
        return userRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        List<_User> users = userRepository.findAllPatients();
        return users.stream().map(user -> (Patient) user).collect(Collectors.toList());
    }

    @Override
    public Patient getPatientById(long id) throws Exception {
        return (Patient) userRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient does not exist"));
    }

    @Override
    public Patient updatePatient(Patient patient, long id) throws Exception {
        Patient pt = (Patient) userRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient does not exist"));

        pt.setAddress(patient.getAddress());
        pt.setEmail(patient.getEmail());
        pt.setFName(patient.getFName());
        pt.setLName(patient.getLName());
        pt.setDateOfBirth(patient.getDateOfBirth());

        return userRepository.save(patient);
    }

    @Override
    public String deletePatient(long id) throws Exception {
        Patient patient = getPatientById(id);
        userRepository.deleteById(id);
        return "Patient with id " + id + "deleted successfully";
    }

    @Override
    public List<Patient> searchByFirstNameOrLastName(String st) {
        return    userRepository
           .findAllByFNameContainsIgnoreCaseOrLNameContainsIgnoreCase(st, st)
           .stream()
           .map(user -> (Patient) user)
           .collect(Collectors.toList());
    }
}
