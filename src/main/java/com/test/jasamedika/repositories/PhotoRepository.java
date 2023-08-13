package com.test.jasamedika.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.jasamedika.models.Photos;

public interface PhotoRepository extends JpaRepository<Photos,Integer> {
    
}
