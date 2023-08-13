package com.test.jasamedika.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.jasamedika.models.StatusAbsensi;

public interface StatusAbsenRepository extends JpaRepository<StatusAbsensi,Integer> {
    
}
