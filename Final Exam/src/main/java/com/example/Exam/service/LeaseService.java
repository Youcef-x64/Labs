package com.example.Exam.service;

import com.example.Exam.model.Lease;
import com.example.Exam.model.StateRevenue;

import java.util.List;

public interface LeaseService {

    List<Lease> findAllByOrderByRefDesc();

    StateRevenue getStateRevenue(String state);

}
