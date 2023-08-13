package com.test.jasamedika.payload.response.inner;

import com.test.jasamedika.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    User user;
}
