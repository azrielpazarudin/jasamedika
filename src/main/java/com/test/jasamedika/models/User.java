package com.test.jasamedika.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.jasamedika.enumerations.Profile;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String namaLegkap;
    private String tempatLahir;
    private LocalDate tanggalLahir;
    private String email;
    private String password;
    private String nik;
    @Enumerated(EnumType.STRING)
    private Profile profile;
    @JoinColumn(name = "kd_jabatan")
    @ManyToOne
    private Jabatan jabatan;
    @JoinColumn(name = "kd_jenis_kelamin")
    @ManyToOne
    private JenisKelamin jenisKelamin;
    @JoinColumn(name = "kd_pendidikan")
    @ManyToOne
    private Pendidikan pendidikan;
    private String photo;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+profile.name()));
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
