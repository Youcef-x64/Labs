package com.example.Exam.repository;

import com.example.Exam.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findAllByStateIgnoreCaseOrderByMonthlyRentalRateAsc(String state);

}
