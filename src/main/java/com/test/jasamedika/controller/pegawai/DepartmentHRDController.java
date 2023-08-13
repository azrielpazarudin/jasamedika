package com.test.jasamedika.controller.pegawai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.test.jasamedika.services.EntityService.PegawaiService;

@RestController
@RequestMapping("/api/pegawai/combo/departemen-hrd")
public class DepartmentHRDController {
    @Autowired
    private PegawaiService pegawaiService;
    
    @GetMapping
    public Object getAllHRD(){
        
        return pegawaiService.getAllHrd();
    }

   

}
