package com.test.jasamedika.services.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.jasamedika.models.UnitKerja;
import com.test.jasamedika.payload.request.entity.UnitKerjaRequest;
import com.test.jasamedika.payload.response.entity.UnitKerjaResponse;
import com.test.jasamedika.repositories.DepartementRepository;
import com.test.jasamedika.repositories.UnitKerjaRepository;
import java.util.List;

@Service
public class UnitKerjaService {
    @Autowired
    private UnitKerjaRepository repository;
    @Autowired
    private DepartementRepository departementRepository;

    public List<UnitKerjaResponse> findAll() {
        List<UnitKerja> units = repository.findAll();
        return units.stream().map(this::mapToResponse).toList();
    }

    private UnitKerjaResponse mapToResponse(UnitKerja units) {
        return UnitKerjaResponse.builder()
                .kdUnitKerja(units.getKode())
                .namaUnitKerja(units.getNama())
                .build();
    }

    public Object save(UnitKerjaRequest request) {
        System.out.println("HERAAAN AINGMAH");
        if (departementRepository.findById(request.getKdDepartement()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Departement tidak ditemukan");
        }
        UnitKerja unitKerja = UnitKerja.builder().nama(request.getNama())
                .departement(departementRepository.findById(request.getKdDepartement()).get()).build();
        repository.save(unitKerja);
        return UnitKerjaResponse.builder().kdUnitKerja(unitKerja.getKode()).namaUnitKerja(unitKerja.getNama()).build();
    }

}
