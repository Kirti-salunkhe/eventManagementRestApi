package com.event.eventManagement.controller;

import com.event.eventManagement.entity.AuthModel;
import com.event.eventManagement.entity.JwtResponse;
import com.event.eventManagement.entity.User;
import com.event.eventManagement.security.JwtTokenService;
import com.event.eventManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel){
       Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authModel.getEmail(),authModel.getPassword()));
          var token = jwtTokenService.generateToken(authentication);
       return new ResponseEntity<>(new JwtResponse(token),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User u){
        return new ResponseEntity<>(userService.createUser(u),HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }

}
