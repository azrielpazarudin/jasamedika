package com.test.jasamedika.payload.request.entity;

import java.time.LocalDate;

import com.test.jasamedika.validation.StrongPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PegawaiRequest {
    @Size(min=16,max = 16)
    String nik;
    @NotEmpty
    String namaLengkap;
    @Email
    @NotEmpty
    String email;
    @NotEmpty
    String tempatLahir;
    LocalDate tanggalLahir;
    @NotEmpty
    @Size(min=1,max = 1)
    String kdJenisKelamin;
    @Size(min=1,max = 1)
    @NotEmpty
    String kdPendidikan;
    @Size(min=1,max = 1)
    @NotEmpty
    String kdJabatan;
    @Size(min=1,max = 1)
    @NotEmpty
    String kdDepartemen;
    @NotEmpty
    @Size(min=1,max = 1)
    String kdUnitKerja;
    @StrongPassword
    @NotEmpty
    String password;
    @NotEmpty
    String passwordC;
}
