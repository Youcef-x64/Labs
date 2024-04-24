package com.example.Exam.repository;

import com.example.Exam.model.Lease;
import com.example.Exam.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {

    List<Lease> findAllByOrderByRefDesc();

    List<Lease> findAllByPropertyState(String state);

}
