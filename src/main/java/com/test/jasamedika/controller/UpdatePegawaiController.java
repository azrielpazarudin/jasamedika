package com.test.jasamedika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jasamedika.payload.request.entity.UbahPegawaiRequest;
import com.test.jasamedika.repositories.UserRepository;
import com.test.jasamedika.services.EntityService.PegawaiService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pegawai/admin-ubah-pegawai")
public class UpdatePegawaiController {
    @Autowired
    private PegawaiService service;
    @Autowired
    private UserRepository repository;

    @PostMapping
    public Object save(HttpServletRequest hsrequest, Authentication auth, @Valid @RequestBody UbahPegawaiRequest request,
            Errors errors) {
        if (hsrequest.isUserInRole("ROLE_ADMIN") || repository.findByEmail(auth.getName()).get().getJabatan()
                .getUnitKerja().getDepartement().getKode() == 1) {

            return service.update(request, errors);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hanya Bisa Diakses Oleh ADMIN atau pegawai HRD");

    }
    
}
