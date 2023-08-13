package com.test.jasamedika.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword {
    private String passwordAsli;
    private String passwordBaru1;
    private String passwordBaru2;

}
