package com.test.jasamedika.controller.pegawai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.test.jasamedika.payload.request.entity.UnitKerjaRequest;
import com.test.jasamedika.services.EntityService.UnitKerjaService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/pegawai/combo/unit-kerja")
public class UnitKerjaController {
    @Autowired
    private UnitKerjaService service;

    @GetMapping
    public Object findAll(){
        return service.findAll();
    }

    @PostMapping
    public Object save(HttpServletRequest hsrequest,@RequestBody UnitKerjaRequest request){
        System.out.println("AAA");
        if(hsrequest.isUserInRole("ROLE_ADMIN")){
        return service.save(request);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hanya Dapat Diakses Oleh Admin");
    }
}
