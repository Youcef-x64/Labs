package com.example.Exam.service;

import com.example.Exam.model.RetirementPlan;

import java.util.List;

public interface RetirementPlanService {

    List<RetirementPlan> findAll();

    RetirementPlan save(RetirementPlan retirementPlan);

}
