package com.youcef.healthcare.Api.service.serviceImpl;

import com.youcef.healthcare.Api.exception.NotFoundException;
import com.youcef.healthcare.Api.models.*;
import com.youcef.healthcare.Api.repository.AddressRepository;
import com.youcef.healthcare.Api.repository.TokenRepository;
import com.youcef.healthcare.Api.repository.UserRepository;
import com.youcef.healthcare.Api.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {


    private UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private AddressRepository addressRepository;

    private final JwtService jwtService;

    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, AddressRepository addressRepository, JwtService jwtService, TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.addressRepository = addressRepository;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public AuthenticationResponse register(Patient request) {

        // check if user already exist. if exist than authenticate the user
        if(userRepository.findByUserName(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exist");
        }

        Address address = request.getAddress();
        addressRepository.save(address);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);
        String jwt = jwtService.generateToken(request);
        saveUserToken(jwt, request);
        return new AuthenticationResponse(jwt, "User registration was successful");

    }
    public AuthenticationResponse authenticate(Patient request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Patient user = (Patient)userRepository.findByUserName(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);
        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        return new AuthenticationResponse(jwt, "User login was successful");

    }
    private void revokeAllTokenByUser(_User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(Math.toIntExact(user.getId()));
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String jwt, _User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }
    @Override
    @Transactional
    public Patient savePatient(Patient patient) {
        Address address = patient.getAddress();
        addressRepository.save(address);
        return userRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        List<_User> users = userRepository.findAllPatients();
        return users.stream().map(user -> (Patient) user).collect(Collectors.toList());
    }

    @Override
    public Patient getPatientById(long id) throws Exception {
        return (Patient) userRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient does not exist"));
    }

    @Override
    public Patient updatePatient(Patient patient, long id) throws Exception {
        Patient pt = (Patient) userRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient does not exist"));
        pt.setAddress(patient.getAddress());
        pt.setEmail(patient.getEmail());
        pt.setFname(patient.getFname());
        pt.setLname(patient.getLname());
        pt.setDateOfBirth(patient.getDateOfBirth());
        savePatient(pt);
        return pt;
    }

    @Override
    public String deletePatient(long id) throws Exception {
        Patient patient = getPatientById(id);
        userRepository.deleteById(id);
        return "Patient with id " + id + "deleted successfully";
    }

    @Override
    public List<Patient> searchByFirstNameOrLastName(String st) {
   return    userRepository
           .findAllByFnameContainsIgnoreCase(st)
           .stream()
           .map(user -> (Patient) user)
           .collect(Collectors.toList());
    }
}
