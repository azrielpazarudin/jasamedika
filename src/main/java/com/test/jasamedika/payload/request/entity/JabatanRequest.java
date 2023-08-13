package com.test.jasamedika.payload.request.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JabatanRequest {
    private String nama;
    private int kodeUnitKerja;
}
