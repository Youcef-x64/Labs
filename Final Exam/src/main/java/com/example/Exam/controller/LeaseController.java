package com.example.Exam.controller;

import com.example.Exam.model.Lease;
import com.example.Exam.model.StateRevenue;
import com.example.Exam.service.LeaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leases")
public class LeaseController {

    private LeaseService leaseService;

    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping
    public ResponseEntity<List<Lease>> findAllByOrderByRefDesc() {
        return ResponseEntity.ok(leaseService.findAllByOrderByRefDesc());
    }

    @GetMapping("/state_revenue")
    public ResponseEntity<StateRevenue> getStateRevenue(@RequestParam("state") String state) {
        return ResponseEntity.ok(leaseService.getStateRevenue(state));
    }

}
