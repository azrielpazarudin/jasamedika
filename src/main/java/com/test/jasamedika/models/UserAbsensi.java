package com.test.jasamedika.models;

import java.time.LocalDate;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.user",joinColumns = @JoinColumn(name = "id_user")),
    @AssociationOverride(name = "primaryKey.status_absensi",joinColumns = @JoinColumn(name = "status_absensi_id"))
})
public class UserAbsensi {
    @EmbeddedId
    private UserAbsensiId primaryKey = new UserAbsensiId();
    @Transient
    public User getUser(){
        return getPrimaryKey().getUser();
    }

    public void setUser(User user){
        getPrimaryKey().setUser(user);
    }

    @Transient
    public StatusAbsensi getStatusAbsensi(){
        return getPrimaryKey().getStatusAbsensi();
    }

    public void setStatusAbsensi(StatusAbsensi statusAbsensi){
        getPrimaryKey().setStatusAbsensi(statusAbsensi);
    }
    private LocalDate tanggalAbsensi;
    private String jamMasuk;
    private String jamKeluar;
}
