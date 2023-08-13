package com.test.jasamedika.services.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.jasamedika.models.StatusAbsensi;
import com.test.jasamedika.payload.request.entity.StatusAbsenRequest;
import com.test.jasamedika.payload.response.entity.StatusAbsenResponse;
import com.test.jasamedika.repositories.StatusAbsenRepository;
import java.util.List;

@Service
public class StatusAbsensiService {
    @Autowired
    private StatusAbsenRepository repository;

    public List<StatusAbsenResponse> findAll() {
        List<StatusAbsensi> absensis = repository.findAll();
        return absensis.stream().map(this::mapToResponse).toList();

    }

    private StatusAbsenResponse mapToResponse(StatusAbsensi status) {
        return StatusAbsenResponse.builder().kdStatus(status.getId()).namaStatus(status.getNama()).build();
    }

    public Object save(StatusAbsenRequest request) {
        var status = StatusAbsensi.builder().nama(request.getNama()).build();
        repository.save(status);
        return StatusAbsenResponse.builder().kdStatus(status.getId()).namaStatus(status.getNama()).build();
    }

}
