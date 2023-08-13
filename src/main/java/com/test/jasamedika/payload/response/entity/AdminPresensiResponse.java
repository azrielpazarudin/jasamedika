package com.test.jasamedika.payload.response.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminPresensiResponse {
    private int idUser;
    private String namaLengkap;
    private String tglAbsensi;
    private String jamMasuk;
    private String jamKeluar;
    private String namaStatus;
}
