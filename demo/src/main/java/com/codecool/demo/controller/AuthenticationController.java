package com.codecool.demo.controller;

import com.codecool.demo.controller.status.NotFoundHttpException;
import com.codecool.demo.model.authentication.AuthRequest;
import com.codecool.demo.model.authentication.AuthResponse;
import com.codecool.demo.services.JwtUtil;
import com.codecool.demo.services.OurUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {

    // global bean set up in WebSecurityConfig
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtTokenUtil;

    private OurUserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            JwtUtil jwtTokenUtil,
            OurUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }




    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthRequest authenticationRequest
    ) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails;
        try {
            userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
        } catch (UsernameNotFoundException err) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final String jwt = jwtTokenUtil.generateResponseJwt(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
