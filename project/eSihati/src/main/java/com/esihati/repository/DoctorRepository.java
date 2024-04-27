package com.esihati.repository;

import com.esihati.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * By Nearest
     * @param latitude
     * @param longitude
     * @param pageable
     * @return doctors
     */
    @Query(value =
            "SELECT d.*, p.*, u.*, " +
                    "(6371 * acos(cos(radians(:latitude)) * cos(radians(gl.latitude)) * cos(radians(gl.longitude) " +
                    "- radians(:longitude)) + " +
                    "sin(radians(:latitude)) * sin(radians(gl.latitude)))) AS distance " +
            "FROM doctor d " +
            "JOIN person p ON d.id = p.id " +
            "JOIN _user u ON u.id = d.id " +
            "JOIN location l ON l.id = u.location_id " +
            "JOIN geo_location gl ON gl.id = l.geo_location_id " +
            "ORDER BY distance",
            nativeQuery = true)
    Page<Doctor> findAllOrderByNearest(Double latitude, Double longitude, Pageable pageable);

    @Query(value =
            "SELECT d.*, p.*, u.*, " +
                    "(6371 * acos(cos(radians(:latitude)) * cos(radians(gl.latitude)) * cos(radians(gl.longitude) " +
                    "- radians(:longitude)) + " +
                    "sin(radians(:latitude)) * sin(radians(gl.latitude)))) AS distance " +
            "FROM doctor d " +
            "JOIN speciality s ON s.id = d.speciality_id " +
            "JOIN person p ON p.id = d.id " +
            "JOIN _user u ON u.id = d.id " +
            "JOIN location l ON l.id = u.location_id " +
            "JOIN geo_location gl ON gl.id = l.geo_location_id " +
            "WHERE d.speciality_id = :specialityId " +
            "ORDER BY distance",
            nativeQuery = true)
    Page<Doctor> findAllOrderByNearestFilterSpeciality(Double latitude, Double longitude, Long specialityId, Pageable pageable);

    @Query("SELECT d from Doctor d " +
            "JOIN d._static ds " +
            "ORDER BY ds.rate DESC")
    Page<Doctor> findAllOrderByRate(Pageable pageable);

    @Query(value = "SELECT d.*, p.*, u.* from Doctor d " +
            "JOIN speciality s ON s.id = d.speciality_id " +
            "JOIN person p ON p.id = d.id " +
            "JOIN _user u ON u.id = d.id " +
            "JOIN doctor_static ds ON ds.doctor_id = d.id " +
            "WHERE d.speciality_id = :specialityId " +
            "ORDER BY ds.rate DESC",
            nativeQuery = true)
    Page<Doctor> findAllOrderByRateFilterBySpeciality(Long specialityId, Pageable pageable);

    @Query("SELECT DISTINCT d " +
            "FROM Doctor d " +
            "JOIN d.shifts s " +
            "JOIN s.day sd " +
            "WHERE sd.id = :dayId " +
            "AND :time BETWEEN s.startTime AND s.endTime")
    Page<Doctor> findAllFilterByOpen(int dayId, LocalTime time, Pageable pageable);

}
