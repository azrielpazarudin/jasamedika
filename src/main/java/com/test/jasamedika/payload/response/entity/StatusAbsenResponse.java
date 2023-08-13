package com.test.jasamedika.payload.response.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusAbsenResponse {
    private int kdStatus;
    private String namaStatus;
}
