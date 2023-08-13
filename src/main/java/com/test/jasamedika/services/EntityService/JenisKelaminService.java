package com.test.jasamedika.services.EntityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.jasamedika.models.JenisKelamin;
import com.test.jasamedika.payload.request.entity.JenisKelaminRequest;
import com.test.jasamedika.payload.response.entity.JenisKelaminResponse;
import com.test.jasamedika.repositories.JenisKelaminRepository;

@Service
public class JenisKelaminService {
    @Autowired
    private JenisKelaminRepository repository;

    public List<JenisKelaminResponse> findAll() {
        List<JenisKelamin> sexs = repository.findAll();
        return sexs.stream().map(this::mapToResponse).toList();
    }

    private JenisKelaminResponse mapToResponse(JenisKelamin jenisKelamin) {
        return JenisKelaminResponse.builder().kdJenisKelamin(jenisKelamin.getKode())
                .namaJenisKelamin(jenisKelamin.getNama()).build();
    }

    public JenisKelaminResponse save(JenisKelaminRequest request) {
        JenisKelamin jenisKelamin = JenisKelamin.builder().nama(request.getNama()).build();
        repository.save(jenisKelamin);
        return JenisKelaminResponse.builder().kdJenisKelamin(jenisKelamin.getKode())
                .namaJenisKelamin(jenisKelamin.getNama()).build();
    }
}
