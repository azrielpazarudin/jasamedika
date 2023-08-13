package com.test.jasamedika.services.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.test.jasamedika.models.Pendidikan;
import com.test.jasamedika.payload.request.entity.PendidikanRequest;
import com.test.jasamedika.payload.response.entity.PendidikanResponse;
import com.test.jasamedika.repositories.PendidikanRepository;

@Service
public class PendidikanService {
    @Autowired
    private PendidikanRepository repository;
    
    public List<PendidikanResponse> findAll(){
        List<Pendidikan> pendidikans = repository.findAll();
        return pendidikans.stream().map(this::mapToResponse).toList();

    }

    private PendidikanResponse mapToResponse(Pendidikan pendidikan){
        return PendidikanResponse.builder()
        .kdPendidikan(pendidikan.getKode())
        .namaPendidikan(pendidikan.getNama())
        .build();
    }

    public PendidikanResponse save(PendidikanRequest request){
        Pendidikan pendidikan = Pendidikan.builder().nama(request.getNama()).build();
        repository.save(pendidikan);
        return PendidikanResponse.builder()
        .kdPendidikan(pendidikan.getKode())
        .namaPendidikan(request.getNama())
        .build();
    }
}


