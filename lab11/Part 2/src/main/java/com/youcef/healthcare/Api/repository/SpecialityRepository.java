package com.youcef.healthcare.Api.repository;

import com.youcef.healthcare.Api.models.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
    Speciality findByName(String name);
}
