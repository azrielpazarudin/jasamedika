package com.test.jasamedika.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UnitKerja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kode;
    private String nama;
    @JoinColumn(name="kd_departement")
    @ManyToOne
    private Departement departement;
}
