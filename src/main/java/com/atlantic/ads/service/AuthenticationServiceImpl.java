package com.atlantic.ads.service;

import com.atlantic.ads.collections.Customer;
import com.atlantic.ads.dao.request.SignInRequest;
import com.atlantic.ads.dao.request.SignUpRequest;
import com.atlantic.ads.dao.response.JwtAuthenticationResponse;
import com.atlantic.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        System.out.println("in signup " + request.toString());
        var user = Customer.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                        .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).phoneNumber(request.getPhoneNumber()).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
