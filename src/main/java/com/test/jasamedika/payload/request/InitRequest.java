package com.test.jasamedika.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitRequest{
    private String namaAdmin;
    private String perusahaan;

}