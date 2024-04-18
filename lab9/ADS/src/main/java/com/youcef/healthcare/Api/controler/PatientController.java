package com.youcef.healthcare.Api.controler;

import com.youcef.healthcare.Api.models.AuthenticationResponse;
import com.youcef.healthcare.Api.models.Patient;
import com.youcef.healthcare.Api.models._User;
import com.youcef.healthcare.Api.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientServiceImpl) {
        this.patientService = patientServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody Patient patient){
        return ResponseEntity.ok(patientService.register(patient));

    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Patient request
    ) {
        return ResponseEntity.ok(patientService.authenticate(request));
    }

    @GetMapping
    public ResponseEntity<List<Patient>>  getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long id) throws Exception {
        return   ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient,@PathVariable long id) throws Exception {
        return ResponseEntity.ok(patientService.updatePatient(patient,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(patientService.deletePatient(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByFirstNameOrLastName(@PathVariable String st){
       return ResponseEntity.ok(patientService.searchByFirstNameOrLastName(st));
    }

}
