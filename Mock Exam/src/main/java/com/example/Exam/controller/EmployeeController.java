package com.example.Exam.controller;

import com.example.Exam.exception.NotFoundException;
import com.example.Exam.model.Employee;
import com.example.Exam.model.EmployeeRetirementDto;
import com.example.Exam.model.RetirementPlan;
import com.example.Exam.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private ModelMapper mapper;

    public EmployeeController(EmployeeService employeeService, ModelMapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeService.findAllByOrderByLastNameAsc());
    }

    @GetMapping("/{id}/retirement_plan")
    public ResponseEntity<RetirementPlan> findRetirementPlanById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.findById(id).getRetirementPlan());
    }

    @GetMapping("/monthly_upcoming_retirees")
    public ResponseEntity<List<Employee>> findMonthlyUpcomingRetirees() {
        return ResponseEntity.ok(employeeService.findMonthlyUpcomingRetirees());
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody EmployeeRetirementDto employeeRetirementDto) {
        RetirementPlan retirementPlan = new RetirementPlan();
        retirementPlan.setEnrollmentDate(employeeRetirementDto.getEnrollmentDate());
        retirementPlan.setRetirementDate(employeeRetirementDto.getRetirementDate());
        retirementPlan.setReferenceNumber(employeeRetirementDto.getReferenceNumber());
        retirementPlan.setMonthlyContribution(employeeRetirementDto.getMonthlyContribution());

        Employee employee = new Employee();
        employee.setFirstName(employeeRetirementDto.getFirstName());
        employee.setLastName(employeeRetirementDto.getLastName());
        employee.setYearlySalary(employeeRetirementDto.getYearlySalary());
        employee.setRetirementPlan(retirementPlan);

        System.out.println(employee);

        return ResponseEntity.ok(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employeeDto) throws NotFoundException {
        Employee employee = employeeService.findById(id);

        if (employee == null)
            throw new NotFoundException("Employee Not Found.");

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setYearlySalary(employeeDto.getYearlySalary());

        return ResponseEntity.ok(employeeService.save(employee));
    }

}
