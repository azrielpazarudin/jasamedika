package com.test.jasamedika.payload.response.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JenisKelaminResponse {
    private int kdJenisKelamin;
    private String namaJenisKelamin;
}
