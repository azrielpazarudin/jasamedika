package com.test.jasamedika.services.EntityService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.test.jasamedika.models.UserAbsensi;
import com.test.jasamedika.payload.request.entity.PresensiAbsenRequest;
import com.test.jasamedika.payload.response.entity.AdminPresensiResponse;
import com.test.jasamedika.payload.response.entity.PresensiInResponse;
import com.test.jasamedika.payload.response.entity.PresensiOutResponse;
import com.test.jasamedika.repositories.StatusAbsenRepository;
import com.test.jasamedika.repositories.UserAbsensiRepository;
import com.test.jasamedika.repositories.UserRepository;

@Service
public class PresensiService {
    @Autowired
    private UserAbsensiRepository userAbsensiRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusAbsenRepository statusAbsenRepository;

    public Object presensiIn(Authentication auth) {
        var user = userRepository.findByEmail(auth.getName()).get();
        var statusAbsen = statusAbsenRepository.findById(1).get();
        UserAbsensi userAbsensi = new UserAbsensi();
        userAbsensi.setUser(user);
        userAbsensi.setStatusAbsensi(statusAbsen);
        userAbsensi.setTanggalAbsensi(LocalDate.now());

        userAbsensi.setJamMasuk(setWaktu());
        userAbsensiRepository.save(userAbsensi);
        PresensiInResponse response = new PresensiInResponse(userAbsensi.getJamMasuk());
        return ResponseEntity.ok().body(response);
    }

    public Object presensiOut(Authentication auth) {
        var userAbsensi = userAbsensiRepository.findBytanggalAbsensi(LocalDate.now());
        var user = userRepository.findByEmail(auth.getName());
        UserAbsensi ua = new UserAbsensi();
        for (UserAbsensi x : userAbsensi) {
            if (x.getUser().getEmail().equals(user.get().getEmail()) && x.getJamKeluar() == null) {
                ua = x;
            }
        }
        ua.setJamKeluar(setWaktu());
        userAbsensiRepository.save(ua);
        PresensiOutResponse response = new PresensiOutResponse(ua.getJamKeluar());
        return response;
    }

    public Object presensiAbsen(Authentication authentication, PresensiAbsenRequest request) {
        var user = userRepository.findByEmail(authentication.getName()).get();
        var statusAbsen = statusAbsenRepository.findById(request.getKdStatus()).get();
        UserAbsensi userAbsensi = new UserAbsensi();
        userAbsensi.setUser(user);
        userAbsensi.setStatusAbsensi(statusAbsen);
        userAbsensi.setTanggalAbsensi(LocalDate.now());
        userAbsensiRepository.save(userAbsensi);
        return ResponseEntity.ok().body("OK");
    }

    private String setWaktu() {
        List<String> waktuStr = new ArrayList<>();
        String waktuFull = "";
        List<Integer> waktuInt = new ArrayList<>(Arrays.asList(
                LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
                LocalDateTime.now().getSecond()));
        int cnt = 0;
        for (int x : waktuInt) {
            String buff = String.format("%02d", x);
            waktuStr.add(buff);
            waktuFull += waktuStr.get(cnt) + ":";
            cnt++;
        }
        waktuFull = waktuFull.substring(0, waktuFull.length() - 1);
        return waktuFull;
    }

    public List<AdminPresensiResponse> presensiAdmin(LocalDate tglAwal, LocalDate tglAkhir) {
        List<UserAbsensi> abs = userAbsensiRepository.findBytanggalAbsensiBetween(tglAwal, tglAkhir);

       return  abs.stream().map(this::mapToResponse).toList();
       
    }

    private AdminPresensiResponse mapToResponse(UserAbsensi ub) {
        if (ub.getUser().getProfile().name().equals("ADMIN")) {
            return null;
        } else {
            return AdminPresensiResponse.builder()
                    .idUser(ub.getUser().getId())
                    .namaLengkap(ub.getUser().getNamaLegkap())
                    .tglAbsensi(ub.getTanggalAbsensi().toString())
                    .jamMasuk(ub.getJamMasuk())
                    .jamKeluar(ub.getJamKeluar())
                    .namaStatus(ub.getStatusAbsensi().getNama())
                    .build();
        }

    }

}
