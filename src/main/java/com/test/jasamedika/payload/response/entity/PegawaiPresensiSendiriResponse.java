package com.test.jasamedika.payload.response.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PegawaiPresensiSendiriResponse {
    private String namaLengkap;
    private String tglAbsensi;
    private String jamMasuk;
    private String jamKeluar;
    private String namaStatus;
}
