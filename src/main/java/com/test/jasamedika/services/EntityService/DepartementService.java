package com.test.jasamedika.services.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.jasamedika.repositories.DepartementRepository;
import com.test.jasamedika.models.Departement;
import com.test.jasamedika.payload.request.entity.DepartementRequest;
import com.test.jasamedika.payload.response.entity.DepartementResponse;
import java.util.List;

@Service
public class DepartementService {
    @Autowired
    private DepartementRepository departementRepository;

    public List<DepartementResponse> findAll() {
        List<Departement> res = departementRepository.findAll();
        return res.stream().map(this::maptToResponse).toList();
    }

    private DepartementResponse maptToResponse(Departement departement) {
        return DepartementResponse.builder()
                .kdDepartement(departement.getKode())
                .namaDepartment(departement.getNama())
                .build();
    }

    public DepartementResponse save(DepartementRequest request) {
        Departement departement = Departement.builder().nama(request.getNama()).build();
        departementRepository.save(departement);
        return DepartementResponse.builder()
                .kdDepartement(departement.getKode())
                .namaDepartment(departement.getNama()).build();

    }
}
