package com.esihati.repository;

import com.esihati.model.DoctorShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorShiftRepository extends JpaRepository<DoctorShift, Long> {

    List<DoctorShift> findAllByDoctorIdAndDayId(Long doctorId, Long dayId);

}
