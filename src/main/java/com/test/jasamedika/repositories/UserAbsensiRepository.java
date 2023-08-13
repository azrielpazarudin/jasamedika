package com.test.jasamedika.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.jasamedika.models.UserAbsensi;
import com.test.jasamedika.models.UserAbsensiId;

public interface UserAbsensiRepository extends JpaRepository<UserAbsensi,UserAbsensiId>{
    public List<UserAbsensi> findBytanggalAbsensi(LocalDate tanggal);
    public List<UserAbsensi> findBytanggalAbsensiBetween(LocalDate awal,LocalDate akhir);
}
