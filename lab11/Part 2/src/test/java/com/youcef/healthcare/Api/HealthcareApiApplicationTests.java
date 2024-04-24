package com.youcef.healthcare.Api;

import com.youcef.healthcare.Api.controler.PatientController;
import com.youcef.healthcare.Api.exception.NotFoundException;
import com.youcef.healthcare.Api.models.Address;
import com.youcef.healthcare.Api.models.Patient;
import com.youcef.healthcare.Api.models._User;
import com.youcef.healthcare.Api.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest

class HealthcareApiApplicationTests {
    @Autowired
    private PatientService patientService;
    @Mock
    private PatientService mockedPatientService;

    @InjectMocks
    private PatientController patientController;

    

    @Test
    public void testFindPatientByIdWhenExists() throws Exception {

        long patientId = 1L;   //patient with id 1L exist

        Patient patient = patientService.getPatientById(patientId);

        assertNotNull(patient);
        assertEquals(patientId, patient.getId());
    }

    @Test
    public void testFindPatientByIdWhenDoesNotExist() throws Exception {

        long nonExistentPatientId = 10L; // patient with id 10L does not exist
        try {
            patientService.getPatientById(nonExistentPatientId);
        } catch (NotFoundException e) {
            assertNotNull(e.getMessage());
        }


    }

    @Test
    public void testGetAllPatient() {

        Patient p1 = new Patient();
        p1.setId(1L);
        Patient p2 = new Patient();
        p2.setId(2L);
        List<Patient> patients = Arrays.asList(p1, p2);

        when(mockedPatientService.getAllPatients()).thenReturn(patients);
        ResponseEntity<List<Patient>> result = patientController.getAllPatients();
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(patients,result.getBody());

    }


}
