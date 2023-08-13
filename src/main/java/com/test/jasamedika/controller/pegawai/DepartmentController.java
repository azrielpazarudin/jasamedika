package com.test.jasamedika.controller.pegawai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.test.jasamedika.payload.request.entity.DepartementRequest;
import com.test.jasamedika.services.EntityService.DepartementService;
import com.test.jasamedika.services.EntityService.PegawaiService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/pegawai/combo/departemen")
public class DepartmentController {
    @Autowired
    private DepartementService departementService;
    @Autowired
    private PegawaiService pegawaiService;
    @GetMapping
    public Object getAll(){
        return departementService.findAll();
    }
    @PostMapping
    public Object save(HttpServletRequest hsrequest,@RequestBody DepartementRequest request){
        if(hsrequest.isUserInRole("ROLE_ADMIN")){
            return departementService.save(request);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hanya Bisa Diakses Admin");
    }
    @GetMapping("-hrd")
    public Object getAllHRD(){
        return pegawaiService.getAllHrd();
    }

   

}
