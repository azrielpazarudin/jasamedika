package com.test.jasamedika.controller.pegawai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jasamedika.payload.request.entity.JenisKelaminRequest;
import com.test.jasamedika.services.EntityService.JenisKelaminService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/api/pegawai/combo/jenis-kelamin")
public class JenisKelaminController {
    @Autowired
    private JenisKelaminService service;

    @GetMapping
    public Object findAll(){
        return service.findAll();
    }

    @PostMapping
    public Object save(HttpServletRequest request,@RequestBody JenisKelaminRequest jenisKelaminRequest){
        if(!request.isUserInRole("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hanya Bisa Diakses Admin"); 
        }
        return service.save(jenisKelaminRequest);
    }

}
