package com.test.jasamedika.payload.response.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DepartementHRD {
    private String namaLengkap;
    private int kdJabatan;
    private String namaJabatan;
}
