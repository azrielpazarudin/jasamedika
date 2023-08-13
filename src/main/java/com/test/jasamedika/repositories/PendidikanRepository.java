package com.test.jasamedika.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.jasamedika.models.Pendidikan;
public interface PendidikanRepository extends JpaRepository<Pendidikan,Integer> {
    
}
