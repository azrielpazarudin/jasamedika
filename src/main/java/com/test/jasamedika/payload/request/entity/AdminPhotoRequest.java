package com.test.jasamedika.payload.request.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPhotoRequest{
    private int userId;
    private String namaFile;
}