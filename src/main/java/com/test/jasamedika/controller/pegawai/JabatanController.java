package com.test.jasamedika.controller.pegawai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.jasamedika.payload.request.entity.JabatanRequest;
import com.test.jasamedika.services.EntityService.JabatanService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/pegawai/combo/jabatan")
public class JabatanController {
    @Autowired
    private JabatanService service;
    
    @GetMapping
    public Object getAll(){
        return service.findAll();
    }

    @PostMapping
    public Object save(HttpServletRequest request,@RequestBody JabatanRequest jabatanRequest){
        if(!request.isUserInRole("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hanya Bisa Diakses Oleh Admin") ;
        }
        return service.save(jabatanRequest);
    }
}
