package com.test.jasamedika.controller.pegawai;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.jasamedika.payload.request.entity.PresensiAbsenRequest;
import com.test.jasamedika.payload.request.entity.StatusAbsenRequest;
import com.test.jasamedika.services.EntityService.PresensiService;
import com.test.jasamedika.services.EntityService.StatusAbsensiService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/presensi")
public class PresensiController {
    @Autowired
    private StatusAbsensiService service;
    @Autowired
    private PresensiService presensiService;

    @GetMapping("/combo/status-absen")
    private Object findAll() {
        return service.findAll();
    }

    @GetMapping("/daftar/admin")
    public Object findAllByAdmin(HttpServletRequest request,@RequestParam(name = "tglAwal") LocalDate tglAwal,@RequestParam(name = "tglAkhir")LocalDate tglAkhir){
        if(request.isUserInRole("ROLE_ADMIN")){
            return presensiService.presensiAdmin(tglAwal, tglAkhir);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hanya Bisa Diakses Oleh Admin");

    }

    @PostMapping
    public Object save(HttpServletRequest request, @RequestBody StatusAbsenRequest statusAbsenRequest) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return service.save(statusAbsenRequest);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hanya Bisa Diakses Oleh Admin");
    }

    @PostMapping("/in")
    public Object in(Authentication authentication) {
        return presensiService.presensiIn(authentication);
    }

    @PostMapping("/out")
    public Object out(Authentication authentication) {
        return presensiService.presensiOut(authentication);
    }

    @PostMapping("/absensi")
    public Object absen(Authentication authentication, @RequestBody PresensiAbsenRequest request) {
        return presensiService.presensiAbsen(authentication, request);
    }

}
