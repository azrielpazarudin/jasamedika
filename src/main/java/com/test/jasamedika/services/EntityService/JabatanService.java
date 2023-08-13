package com.test.jasamedika.services.EntityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.jasamedika.models.Jabatan;
import com.test.jasamedika.payload.request.entity.JabatanRequest;
import com.test.jasamedika.payload.response.entity.JabatanResponse;
import com.test.jasamedika.repositories.JabatanRepository;
import com.test.jasamedika.repositories.UnitKerjaRepository;

@Service
public class JabatanService {
    @Autowired
    private JabatanRepository repository;
    @Autowired
    private UnitKerjaRepository unitKerjaRepository;

    public List<JabatanResponse> findAll() {
        List<Jabatan> jabatans = repository.findAll();
        return jabatans.stream().map(this::mapToResponse).toList();
    }

    private JabatanResponse mapToResponse(Jabatan jabatan) {
        return JabatanResponse.builder()
                .kdJabatan(jabatan.getKode())
                .namaJabatan(jabatan.getNama())
                .build();
    }

    public Object save(JabatanRequest request){
        if(unitKerjaRepository.findById(request.getKodeUnitKerja()).isEmpty()){
            return ResponseEntity.badRequest().body("Unit Kerja Tidak Ditemukan");
        }
        Jabatan jabatan = Jabatan.builder().nama(request.getNama()).unitKerja(unitKerjaRepository.findById(request.getKodeUnitKerja()).get()).build();
        repository.save(jabatan);
        return JabatanResponse.builder()
        .kdJabatan(jabatan.getKode())
        .namaJabatan(jabatan.getNama())
        .build();
    }
}
