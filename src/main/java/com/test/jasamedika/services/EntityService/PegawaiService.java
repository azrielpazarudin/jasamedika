package com.test.jasamedika.services.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.test.jasamedika.enumerations.Profile;
import com.test.jasamedika.models.User;
import com.test.jasamedika.payload.request.entity.PegawaiRequest;
import com.test.jasamedika.payload.request.entity.UbahPegawaiRequest;
import com.test.jasamedika.payload.response.entity.DepartementHRD;
import com.test.jasamedika.payload.response.entity.PegawaiResponse;
import com.test.jasamedika.repositories.JabatanRepository;
import com.test.jasamedika.repositories.JenisKelaminRepository;
import com.test.jasamedika.repositories.PendidikanRepository;
import com.test.jasamedika.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PegawaiService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JabatanRepository jabatanRepository;
    @Autowired
    private JenisKelaminRepository jenisKelaminRepository;
    @Autowired
    private PendidikanRepository pendidikanRepository;

    public List<PegawaiResponse> findAll() {
        List<User> pegawaiList = userRepository.findByProfile(Profile.PEGAWAI);
        return pegawaiList.stream().map(this::mapToResponse).toList();
    }

    private PegawaiResponse mapToResponse(User user) {
        return PegawaiResponse.builder().profile(user.getProfile().name())
                .idUser(user.getId())
                .namaLengkap(user.getNamaLegkap())
                .tempatLahir(user.getTempatLahir())
                .tanggalLahir(user.getTanggalLahir().toString())
                .email(user.getEmail())
                .password(user.getPassword())
                .nikUser(user.getNik())
                .kdJabatan(user.getJabatan().getKode())
                .namaJabatan(user.getJabatan().getNama())
                .kdDepartemen(user.getJabatan().getUnitKerja().getDepartement().getKode())
                .namaDepartemen(user.getJabatan().getUnitKerja().getDepartement().getNama())
                .kdUnitKerja(user.getJabatan().getUnitKerja().getKode())
                .namaUnitKerja(user.getJabatan().getUnitKerja().getNama())
                .kdJenisKelamin(user.getJenisKelamin().getKode())
                .namaJenisKelamin(user.getJenisKelamin().getNama())
                .kdPendidikan(user.getPendidikan().getKode())
                .namaPendidikan(user.getPendidikan().getNama())
                .photo(user.getPhoto())
                .build();
    }

    public Object save(PegawaiRequest request, Errors errors) {
        List<String> errorList = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError err : errors.getAllErrors()) {
                errorList.add(err.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
        }
        if (!request.getPassword().equals(request.getPasswordC())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password Tidak Sesiai");
        }
        User user = User.builder()
                .namaLegkap(request.getNamaLengkap())
                .tempatLahir(request.getTempatLahir())
                .tanggalLahir(request.getTanggalLahir())
                .email(request.getEmail())
                .password(passwordEncoder().encode(request.getPassword()))
                .nik(request.getNik())
                .profile(Profile.PEGAWAI)
                .jabatan(jabatanRepository.findById(Integer.parseInt(request.getKdJabatan())).get())
                .jenisKelamin(jenisKelaminRepository.findById(Integer.parseInt(request.getKdJenisKelamin())).get())
                .pendidikan(pendidikanRepository.findById(Integer.parseInt(request.getKdPendidikan())).get())
                .photo(null)
                .build();
        userRepository.save(user);
        return PegawaiResponse.builder().profile(user.getProfile().name())
                .idUser(user.getId())
                .namaLengkap(user.getNamaLegkap())
                .tempatLahir(user.getTempatLahir())
                .tanggalLahir(user.getTanggalLahir().toString())
                .email(user.getEmail())
                .password(user.getPassword())
                .nikUser(user.getNik())
                .kdJabatan(user.getJabatan().getKode())
                .namaJabatan(user.getJabatan().getNama())
                .kdDepartemen(user.getJabatan().getUnitKerja().getDepartement().getKode())
                .namaDepartemen(user.getJabatan().getUnitKerja().getDepartement().getNama())
                .kdUnitKerja(user.getJabatan().getUnitKerja().getKode())
                .namaUnitKerja(user.getJabatan().getUnitKerja().getNama())
                .kdJenisKelamin(user.getJenisKelamin().getKode())
                .namaJenisKelamin(user.getJenisKelamin().getNama())
                .kdPendidikan(user.getPendidikan().getKode())
                .namaPendidikan(user.getPendidikan().getNama())
                .photo(user.getPhoto())
                .build();

    }

    public List<DepartementHRD> getAllHrd() {
        List<User> users = userRepository.findByJabatanUnitKerjaDepartementKode(1);
        return users.stream().map(this::mapTopResponse).toList();
    }

    private DepartementHRD mapTopResponse(User user) {
        return DepartementHRD.builder().kdJabatan(user.getId())
                .namaLengkap(user.getNamaLegkap())
                .namaJabatan(user.getJabatan().getNama())
                .kdJabatan(user.getJabatan().getKode())
                .build();
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Object update(UbahPegawaiRequest request,Errors errors) {
        List<String> errorList = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError err : errors.getAllErrors()) {
                errorList.add(err.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
        }
        if (userRepository.findById(Integer.parseInt(request.getIdUser())).isEmpty()) {
            return ResponseEntity.badRequest().body("User Tidak Ditemukan");
        }
        var user = userRepository.findById(Integer.parseInt(request.getIdUser())).get();

        user.setNamaLegkap(request.getNamaLengkap());
        user.setTempatLahir(request.getTempatLahir());
        user.setTanggalLahir(request.getTanggalLahir());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder().encode(request.getPassword()));
        user.setNik(request.getNik());
        user.setProfile(Profile.PEGAWAI);
        user.setJabatan(jabatanRepository.findById(Integer.parseInt(request.getKdJabatan())).get());
        user.setJenisKelamin(jenisKelaminRepository.findById(Integer.parseInt(request.getKdJenisKelamin())).get());
        user.setPendidikan(pendidikanRepository.findById(Integer.parseInt(request.getKdPendidikan())).get());
        user.setPhoto(null);
        userRepository.save(user);
        return PegawaiResponse.builder().profile(user.getProfile().name())
                .idUser(user.getId())
                .namaLengkap(user.getNamaLegkap())
                .tempatLahir(user.getTempatLahir())
                .tanggalLahir(user.getTanggalLahir().toString())
                .email(user.getEmail())
                .password(user.getPassword())
                .nikUser(user.getNik())
                .kdJabatan(user.getJabatan().getKode())
                .namaJabatan(user.getJabatan().getNama())
                .kdDepartemen(user.getJabatan().getUnitKerja().getDepartement().getKode())
                .namaDepartemen(user.getJabatan().getUnitKerja().getDepartement().getNama())
                .kdUnitKerja(user.getJabatan().getUnitKerja().getKode())
                .namaUnitKerja(user.getJabatan().getUnitKerja().getNama())
                .kdJenisKelamin(user.getJenisKelamin().getKode())
                .namaJenisKelamin(user.getJenisKelamin().getNama())
                .kdPendidikan(user.getPendidikan().getKode())
                .namaPendidikan(user.getPendidikan().getNama())
                .photo(user.getPhoto())
                .build();

    }

}
