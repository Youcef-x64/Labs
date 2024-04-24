package com.example.Exam.controller;

import com.example.Exam.model.Lease;
import com.example.Exam.model.Property;
import com.example.Exam.service.LeaseService;
import com.example.Exam.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public ResponseEntity<List<Property>> findAllByStateOrderByMonthlyRentalRateAsc(
            @RequestParam("state") String state) {
        return ResponseEntity.ok(propertyService.findAllByStateOrderByMonthlyRentalRateAsc(state));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Property> addLease(@PathVariable("id") Long id, @RequestBody Lease lease) {
        Property property = propertyService.findById(id);

        if (property == null) {
            return null; // Exception throwing NotFoundException
        }

        lease.setProperty(property);
        property.getLeases().add(lease);

        propertyService.save(property);

        return ResponseEntity.ok(property);
    }

}
