package com.esihati.controller;

import com.esihati.exception.NotFoundException;
import com.esihati.model.*;
import com.esihati.model.dto.patient.request.DoctorRequestDto;
import com.esihati.model.dto.patient.request.FilterRequestDto;
import com.esihati.model.dto.patient.request.SortRequestDto;
import com.esihati.model.dto.patient.response.DoctorResponseDto;
import com.esihati.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private DoctorService doctorService;
    private CityService cityService;
    private StateService stateService;
    private SpecialityService specialityService;
    private RoleService roleService;
    private GenderService genderService;


    private ModelMapper mapper;

    public DoctorController(DoctorService doctorService, CityService cityService, StateService stateService,
                            SpecialityService specialityService, RoleService roleService, GenderService genderService,
                            ModelMapper mapper) {
        this.doctorService = doctorService;
        this.cityService = cityService;
        this.stateService = stateService;
        this.specialityService = specialityService;
        this.roleService = roleService;
        this.genderService = genderService;
        this.mapper = mapper;
    }

    @GetMapping("/p")
    public Page<DoctorResponseDto> findAllPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            // Sort & Filter
            @RequestParam(value = "sort", required = false) SortRequestDto sortRequestDto,
            @RequestParam(value = "filter", required = false) List<FilterRequestDto> filterRequestDto,
            // Patient Geo Location
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude,
            // Doctor Speciality
            @RequestParam(value = "speciality_id", required = false) Long specialityId,
            // Current Date & Time
            @RequestParam(value = "current_date", required = false) LocalDate currentDate,
            @RequestParam(value = "current_time", required = false) LocalTime currentTime) {
        if (size > 25)
            size = 25;

        Pageable pageable = PageRequest.of(page, size);

        return getDoctorResponseDtos(sortRequestDto, filterRequestDto, latitude, longitude, specialityId, pageable);
    }

    @PostMapping("/d/register")
    public ResponseEntity<DoctorResponseDto> register(@RequestBody DoctorRequestDto doctorRequestDto)
            throws NotFoundException {
        Doctor doctor = convertToDoctor(doctorRequestDto);

        return ResponseEntity.ok(doctorService.register(doctor));
    }

    private Doctor convertToDoctor(DoctorRequestDto doctorRequestDto)
            throws NotFoundException {
        Doctor doctor = new Doctor();

        Role role = roleService.findById(2L);
        State state = stateService.findById(doctorRequestDto.getLocation().getState().getId());
        City city = cityService.findById(doctorRequestDto.getLocation().getCity().getId());
        GeoLocation geoLocation = mapper.map(doctorRequestDto.getLocation().getGeoLocation(), GeoLocation.class);
        Location location = new Location(null, state, city, geoLocation);
        Gender gender = genderService.findById(doctorRequestDto.getGender().getId());
        Speciality speciality = specialityService.findById(doctorRequestDto.getSpeciality().getId());

        doctor.setEmail(doctorRequestDto.getEmail());
        doctor.setPhone(doctorRequestDto.getPhone());
        doctor.setEncryptedPassword(doctorRequestDto.getPassword());
        doctor.setFirstName(doctorRequestDto.getFirstName());
        doctor.setLastName(doctorRequestDto.getLastName());
        doctor.setDob(doctorRequestDto.getDob());
        doctor.setGender(gender);
        doctor.setRole(role);
        doctor.setLocation(location);
        doctor.setSpeciality(speciality);

        return doctor;
    }

    private Page<DoctorResponseDto> getDoctorResponseDtos(
            SortRequestDto sortRequestDto, List<FilterRequestDto> filterRequestDto,
            Double latitude, Double longitude,
            Long specialityId,
            Pageable pageable) {
        if (sortRequestDto != null) {
            if (sortRequestDto.equals(SortRequestDto.NEAREST)) {
                if (filterRequestDto != null) {
                    if (filterRequestDto.contains(FilterRequestDto.SPECIALITY)) {
                        if (filterRequestDto.contains(FilterRequestDto.OPEN)) {
                            // Sort(NEAREST) Filter[SPECIALITY, OPEN]
                            System.out.println("Sort(NEAREST) Filter[SPECIALITY, OPEN]");
                        } else {
                            // Sort(NEAREST) Filter[SPECIALITY]
                            System.out.println("Sort(NEAREST) Filter[SPECIALITY]");
                            return doctorService
                                    .findAllOrderByNearestFilterSpeciality(latitude, longitude, specialityId, pageable);
                        }
                    } else if (filterRequestDto.contains(FilterRequestDto.OPEN)){
                        // Sort(NEAREST) Filter[OPEN]
                        System.out.println("Sort(NEAREST) Filter[OPEN]");
                    }
                } else {
                    // Sort(NEAREST)
                    return doctorService.findAllOrderByNearest(latitude, longitude, pageable);
                }
            } else if (sortRequestDto.equals(SortRequestDto.RATE)) {
                if (filterRequestDto != null) {
                    if (filterRequestDto.contains(FilterRequestDto.SPECIALITY)) {
                        if (filterRequestDto.contains(FilterRequestDto.OPEN)) {
                            // Sort(RATE) Filter[SPECIALITY, OPEN]
                            System.out.println("Sort(RATE) Filter[SPECIALITY, OPEN]");
                        } else {
                            // Sort(RATE) Filter[SPECIALITY]
                            System.out.println("Sort(RATE) Filter[SPECIALITY]");
                            return doctorService.findAllOrderByRateFilterBySpeciality(specialityId, pageable);
                        }
                    } else if (filterRequestDto.contains(FilterRequestDto.OPEN)){
                        // Sort(RATE) Filter[OPEN]
                        System.out.println("Sort(RATE) Filter[OPEN]");
                    }
                } else {
                    // Sort(Rate) Filter[DEFAULT - NEAREST]
                    System.out.println("Sort(Rate) Filter[DEFAULT - NEAREST]");
                    return doctorService.findAllOrderByNearest(latitude, longitude, pageable);
                }
            }
        } else {
            if (filterRequestDto != null) {
                if (filterRequestDto.contains(FilterRequestDto.SPECIALITY)) {
                    if (filterRequestDto.contains(FilterRequestDto.OPEN)) {
                        // Sort(DEFAULT - NEAREST) Filter[SPECIALITY, OPEN]
                        System.out.println("Sort(DEFAULT - NEAREST) Filter[SPECIALITY, OPEN]");
                    } else {
                        // Sort(DEFAULT - NEAREST) Filter[SPECIALITY]
                        System.out.println("Sort(DEFAULT - NEAREST) Filter[SPECIALITY]");
                        return doctorService
                                .findAllOrderByNearestFilterSpeciality(latitude, longitude, specialityId, pageable);
                    }
                } else if (filterRequestDto.contains(FilterRequestDto.OPEN)){
                    // Sort(DEFAULT - NEAREST) Filter[OPEN]
                    System.out.println("Sort(DEFAULT - NEAREST) Filter[OPEN]");
                }
            } else {
                // Sort(DEFAULT - NEAREST)
                System.out.println("Sort(DEFAULT - NEAREST)");
                return doctorService.findAllOrderByNearest(latitude, longitude, pageable);
            }
        }

        return doctorService.findAllOrderByNearest(latitude, longitude, pageable);
    }

}
