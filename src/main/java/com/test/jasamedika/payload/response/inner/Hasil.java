package com.test.jasamedika.payload.response.inner;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hasil {
    private String accesToken;
    private String refreshToken;
    private Info info;
}
