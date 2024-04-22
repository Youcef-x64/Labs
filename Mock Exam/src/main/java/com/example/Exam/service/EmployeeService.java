package com.example.Exam.service;

import com.example.Exam.model.Employee;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    List<Employee> findAllByOrderByLastNameAsc();

    Employee findById(Long id);

    List<Employee> findMonthlyUpcomingRetirees();

    Employee save(Employee employee);

}
