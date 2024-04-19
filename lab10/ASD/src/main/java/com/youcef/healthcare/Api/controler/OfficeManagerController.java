package com.youcef.healthcare.Api.controler;

import com.youcef.healthcare.Api.models.OfficeManager;
import com.youcef.healthcare.Api.service.OfficeManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/officemanager")
public class OfficeManagerController {

    private final OfficeManagerService officeManagerService;

    public OfficeManagerController(OfficeManagerService officeManagerService) {
        this.officeManagerService = officeManagerService;
    }


    @PostMapping
    public ResponseEntity<?> saveOfficeManager(@RequestBody OfficeManager officeManager){

        return ResponseEntity.ok(officeManagerService.saveOfficeManager(officeManager));
    }

}
