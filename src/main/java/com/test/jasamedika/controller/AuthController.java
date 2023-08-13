package com.test.jasamedika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jasamedika.payload.request.AuthenticationRequest;
import com.test.jasamedika.payload.request.ChangePassword;
import com.test.jasamedika.payload.request.InitRequest;
import com.test.jasamedika.services.AuthenticationService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/init-data")
    public Object init(@RequestBody InitRequest request){
        System.out.println(request.getNamaAdmin());
        return service.init(request);
    }

    @PostMapping("/login")
    public Object login(@RequestBody AuthenticationRequest request){
        return service.authenticate(request);
    }
    @PostMapping("/ubah-password-sendiri")
    public Object changePassword(Authentication authentication,@RequestBody ChangePassword change){
        return service.changePassword(authentication.getName(),change);
    }
}
