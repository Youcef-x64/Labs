package com.youcef.healthcare.Api.service;

import com.youcef.healthcare.Api.models.AuthenticationResponse;
import com.youcef.healthcare.Api.models.Patient;

import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient);

    List<Patient> getAllPatients();
    AuthenticationResponse authenticate(Patient request);
    Patient getPatientById(long id) throws Exception;

    Patient updatePatient(Patient patient,long id) throws Exception;

    String deletePatient(long id) throws Exception;

    List<Patient> searchByFirstNameOrLastName(String st);
    public AuthenticationResponse register(Patient request);
}
