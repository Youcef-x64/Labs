package com.example.Exam.service.impl;

import com.example.Exam.model.Lease;
import com.example.Exam.model.StateRevenue;
import com.example.Exam.repository.LeaseRepository;
import com.example.Exam.service.LeaseService;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LeaseServiceImpl implements LeaseService {

    private LeaseRepository leaseRepository;

    public LeaseServiceImpl(LeaseRepository leaseRepository) {
        this.leaseRepository = leaseRepository;
    }

    @Override
    public List<Lease> findAllByOrderByRefDesc() {
        return leaseRepository.findAllByOrderByRefDesc();
    }

    @Override
    public StateRevenue getStateRevenue(String state) {
        List<Lease> leases = leaseRepository.findAllByPropertyState(state);

        double totalReveue = 0.0;

        for(Lease lease: leases) {
            System.out.println(lease.getStartDate() + " " + lease.getEndDate());
//            Period period = Period.between(lease.getStartDate(), lease.getEndDate());
            long months = ChronoUnit.MONTHS.between(lease.getStartDate(), lease.getEndDate());
            System.out.println("Months: " + months);

            totalReveue += months * lease.getProperty().getMonthlyRentalRate();
        }

        return new StateRevenue(state, totalReveue);
    }

}
