package com.example.Exam.repository;

import com.example.Exam.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByOrderByLastNameAsc();

    @Query("SELECT e FROM Employee e ORDER BY e.lastName ASC")
    List<Employee> findAllOrderedByLastNameAsc();

    @Query("SELECT e FROM Employee e " +
            "LEFT JOIN RetirementPlan rp " +
            "ON e.retirementPlan = rp " +
            "WHERE (rp.retirementDate BETWEEN :firstDate AND :lastDate) OR (rp.retirementDate < CURRENT_DATE) " +
            "ORDER BY rp.retirementDate ASC")
    List<Employee> findMonthlyUpcomingRetirees(
            LocalDate firstDate,
            LocalDate lastDate
    );

}
