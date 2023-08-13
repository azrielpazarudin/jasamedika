package com.test.jasamedika.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.jasamedika.models.Departement;

public interface DepartementRepository extends JpaRepository<Departement,Integer> {
    
}
