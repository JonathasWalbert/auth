package com.authentication.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.auth.domain.user.DataAuthentication;
import com.authentication.auth.domain.user.User;
import com.authentication.auth.infra.security.DataTokenJwt;
import com.authentication.auth.infra.security.TokenService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid DataAuthentication data) {
        try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());

                return ResponseEntity.ok(new DataTokenJwt(tokenJwt));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
