package com.test.jasamedika.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserAbsensiId {
    @ManyToOne
    private User user;
    @ManyToOne
    private StatusAbsensi statusAbsensi;
}
