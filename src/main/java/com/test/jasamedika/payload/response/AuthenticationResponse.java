package com.test.jasamedika.payload.response;


import com.test.jasamedika.payload.response.inner.Hasil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
   private Hasil hasil;
}


