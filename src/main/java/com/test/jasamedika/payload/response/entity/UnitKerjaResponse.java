package com.test.jasamedika.payload.response.entity;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnitKerjaResponse {
    private int kdUnitKerja;
    private String namaUnitKerja;
}
