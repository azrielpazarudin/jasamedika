package com.test.jasamedika.payload.response.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PegawaiResponse {
    String profile;
    int idUser;
    String namaLengkap;
    String tempatLahir;
    String tanggalLahir;
    String email;
    String password;
    String nikUser;
    int kdJabatan;
    String namaJabatan;
    int kdDepartemen;
    String namaDepartemen;
    int kdUnitKerja;
    String namaUnitKerja;
    int kdJenisKelamin;
    String namaJenisKelamin;
    int kdPendidikan;
    String namaPendidikan;
    String photo;
}
