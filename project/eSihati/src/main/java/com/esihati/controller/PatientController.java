package com.esihati.controller;

import com.esihati.exception.NotFoundException;
import com.esihati.exception.NotValidException;
import com.esihati.exception.WrongCredentialException;
import com.esihati.model.*;
import com.esihati.model.dto.patient.request.ChangePasswordRequestDto;
import com.esihati.model.dto.patient.request.PatientRequestDto;
import com.esihati.model.dto.patient.response.PatientResponseDto;
import com.esihati.model.dto.patient.response.SuccessResponseDto;
import com.esihati.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private PatientService patientService;

    private CityService cityService;
    private StateService stateService;
    private GeoLocationService geoLocationService;
    private LocationService locationService;
    private RoleService roleService;
    private GenderService genderService;


    private ModelMapper mapper;

    public PatientController(PatientService patientService, CityService cityService, StateService stateService,
                             GeoLocationService geoLocationService, LocationService locationService,
                             RoleService roleService, GenderService genderService, ModelMapper mapper) {
        this.patientService = patientService;
        this.cityService = cityService;
        this.stateService = stateService;
        this.geoLocationService = geoLocationService;
        this.locationService = locationService;
        this.roleService = roleService;
        this.genderService = genderService;
        this.mapper = mapper;
    }

    /**
     * Patient
     */

    @PostMapping("/p/register")
    public ResponseEntity<PatientResponseDto> register(@RequestBody PatientRequestDto patientRequestDto)
            throws NotFoundException {
        // TODO register user and return token
        Patient patient = convertPatientDto(patientRequestDto);

        return ResponseEntity.ok(patientService.register(patient));
    }

    @PutMapping("/p/change_password")
    public ResponseEntity<SuccessResponseDto> changePassword(
            @RequestBody ChangePasswordRequestDto changePasswordRequestDto)
            throws NotFoundException, WrongCredentialException, NotValidException {
        // TODO get user id from token
        Patient patient = patientService.findById(1L);

        // Check if the current password is matching with saved one
        if (!patient.getEncryptedPassword().equals(changePasswordRequestDto.getCurrentPassword()))
            throw new WrongCredentialException(6L, "Wrong Current Password");

        // Check if the new password is valid
        if (!isValidPassword(changePasswordRequestDto.getNewPassword()))
            throw new NotValidException(7L, "Password Not Valid");

        patient.setEncryptedPassword(changePasswordRequestDto.getNewPassword());

        patientService.save(patient);

        return ResponseEntity.ok(new SuccessResponseDto(200, "Password Changed Successfully"));
    }

    @GetMapping("/d")
    public Page<PatientResponseDto> findAllByDoctorId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (size > 25)
            size = 25;

        Pageable pageable = PageRequest.of(page, size);

        // TODO Auth
        return patientService.findAllByDoctorId(2L, pageable);
    }

    private Patient convertPatientDto(PatientRequestDto patientRequestDto) throws NotFoundException {
        Patient patient = new Patient();

        Role role = roleService.findById(2L);
        State state = stateService.findById(patientRequestDto.getLocation().getState().getId());
        City city = cityService.findById(patientRequestDto.getLocation().getCity().getId());
        GeoLocation geoLocation = mapper.map(patientRequestDto.getLocation().getGeoLocation(), GeoLocation.class);
        Location location = new Location(null, state, city, geoLocation);
        Gender gender = genderService.findById(patientRequestDto.getGender().getId());

        patient.setEmail(patientRequestDto.getEmail());
        patient.setPhone(patientRequestDto.getPhone());
        patient.setEncryptedPassword(patientRequestDto.getPassword());
        patient.setFirstName(patientRequestDto.getFirstName());
        patient.setLastName(patientRequestDto.getLastName());
        patient.setDob(patientRequestDto.getDob());
        patient.setGender(gender);
        patient.setRole(role);
        patient.setLocation(location);

        return patient;
    }

    public static boolean isValidPassword(String password) {
        // Check if the password length is at least 8 characters
        if (password.length() < 8) {
            return false;
        }

        // Check if the password contains any whitespace
        for (int i = 0; i < password.length(); i++) {
            if (Character.isWhitespace(password.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
