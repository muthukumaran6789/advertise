package com.atlantic.ads.service;

import com.atlantic.ads.dao.request.SignInRequest;
import com.atlantic.ads.dao.request.SignUpRequest;
import com.atlantic.ads.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signin(SignInRequest request);
}
