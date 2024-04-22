package com.example.Exam.service.impl;

import com.example.Exam.model.Employee;
import com.example.Exam.repository.EmployeeRepository;
import com.example.Exam.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findAllByOrderByLastNameAsc() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findMonthlyUpcomingRetirees() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Get the first date of the next month
        LocalDate firstDateOfNextMonth = currentDate.plusMonths(1).withDayOfMonth(1);

        // Get the last date of the next month
        LocalDate lastDateOfNextMonth = firstDateOfNextMonth.plusMonths(1).minusDays(1);

        return employeeRepository.findMonthlyUpcomingRetirees(firstDateOfNextMonth, lastDateOfNextMonth);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

}
