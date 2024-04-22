package com.example.Exam.controller;

import com.example.Exam.model.RetirementPlan;
import com.example.Exam.service.RetirementPlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RetirementPlanController {

    private RetirementPlanService retirementPlanService;

    public RetirementPlanController(RetirementPlanService retirementPlanService) {
        this.retirementPlanService = retirementPlanService;
    }

    @GetMapping("/retirement_plans")
    public List<RetirementPlan> findAll() {
        return retirementPlanService.findAll();
    }

}
