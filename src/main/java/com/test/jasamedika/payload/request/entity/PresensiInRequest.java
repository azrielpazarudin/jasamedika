package com.test.jasamedika.payload.request.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresensiInRequest {
    private LocalDateTime jamMasuk;
}
