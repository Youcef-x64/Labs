package com.youcef.healthcare.Api.service.serviceImpl;

import com.youcef.healthcare.Api.models.Address;
import com.youcef.healthcare.Api.models.AuthenticationResponse;
import com.youcef.healthcare.Api.models.Token;
import com.youcef.healthcare.Api.models._User;
import com.youcef.healthcare.Api.repository.TokenRepository;
import com.youcef.healthcare.Api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(_User request) {

        // check if user already exist. if exist than authenticate the user
        if(repository.findByUserName(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exist");
        }

        _User user = new _User();
        user.setFname(request.getFname());
        user.setLname(request.getLname());
        user.setUserName(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user = repository.save(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);
        System.out.println("hellllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllo");
        return new AuthenticationResponse(jwt, "User registration was successful");

    }

    public AuthenticationResponse authenticate(_User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        _User user = repository.findByUserName(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

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
}
