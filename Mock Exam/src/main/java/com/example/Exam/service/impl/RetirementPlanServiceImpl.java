package com.example.Exam.service.impl;

import com.example.Exam.model.RetirementPlan;
import com.example.Exam.repository.RetirementPlanRepository;
import com.example.Exam.service.RetirementPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetirementPlanServiceImpl implements RetirementPlanService {

    private RetirementPlanRepository retirementPlanRepository;

    public RetirementPlanServiceImpl(RetirementPlanRepository retirementPlanRepository) {
        this.retirementPlanRepository = retirementPlanRepository;
    }

    @Override
    public List<RetirementPlan> findAll() {
        return retirementPlanRepository.findAll();
    }

    @Override
    public RetirementPlan save(RetirementPlan retirementPlan) {
        return retirementPlanRepository.save(retirementPlan);
    }

}
