package com.test.jasamedika.controller.pegawai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jasamedika.repositories.UserRepository;
import com.test.jasamedika.services.EntityService.PegawaiService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/pegawai/daftar")
public class PegawaiController {
    @Autowired
    private PegawaiService service;
    @Autowired
    private UserRepository repository;

    @GetMapping
    public Object findAll(HttpServletRequest request,Authentication auth){
        if(request.isUserInRole("ROLE_ADMIN")||repository.findByEmail(auth.getName()).get().getJabatan().getUnitKerja().getDepartement().getKode() == 1){
            
            return service.findAll();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hanya Bisa Diakses Oleh ADMIN atau pegawai HRD");
    }
}
