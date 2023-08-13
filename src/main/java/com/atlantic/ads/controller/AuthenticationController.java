package com.atlantic.ads.controller;

import com.atlantic.ads.dao.request.SignInRequest;
import com.atlantic.ads.dao.request.SignUpRequest;
import com.atlantic.ads.dao.response.JwtAuthenticationResponse;
import com.atlantic.ads.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        System.out.println("in signup " + request.toString());
        JwtAuthenticationResponse response = authenticationService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        System.out.println("in signin " + request.toString());
        JwtAuthenticationResponse response = null;
        try {
            response = authenticationService.signin(request);
        } catch (Exception e) {
            System.out.println("exception in signin " + e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
