package com.test.jasamedika.repositories;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.test.jasamedika.enumerations.Profile;
import com.test.jasamedika.models.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByEmail(String email);
    public List<User> findByJabatanUnitKerjaDepartementKode(int departId);
    public List<User> findByProfile(Profile profile);
}
