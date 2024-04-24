package com.youcef.healthcare.Api.controler;

import com.youcef.healthcare.Api.models.Dentist;
import com.youcef.healthcare.Api.service.DentistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dentist")
public class DentistController {

    private final DentistService dentistService;

    public DentistController(DentistService userServiceImpl) {
        this.dentistService = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> saveDentist (@RequestBody Dentist dentist){
    return ResponseEntity.ok(dentistService.saveDentist(dentist));
    }
}
