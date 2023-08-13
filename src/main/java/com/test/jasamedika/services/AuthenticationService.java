package com.test.jasamedika.services;

import com.test.jasamedika.payload.request.AuthenticationRequest;
import com.test.jasamedika.payload.request.ChangePassword;
import com.test.jasamedika.payload.request.InitRequest;
import com.test.jasamedika.payload.response.AuthenticationResponse;
import com.test.jasamedika.payload.response.InitResponse;
import com.test.jasamedika.payload.response.inner.Hasil;
import com.test.jasamedika.payload.response.inner.Info;
import com.test.jasamedika.enumerations.Profile;
import com.test.jasamedika.models.User;
import com.test.jasamedika.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService  {

        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final UserRepository userRepository;
        private final AuthenticationManager authenticationManager;
        private final RefreshTokenService refreshTokenService;
        public Object init(InitRequest request){
                String defaultPassword = "Admin123";
             var user =  userRepository.save(User.builder()
                .namaLegkap(request.getNamaAdmin()).email("admin@gmail.com").password(passwordEncoder.encode(defaultPassword)).profile(Profile.ADMIN).build());
                return InitResponse.builder().email(user.getEmail()).password(defaultPassword).profile(user.getProfile().name()).build();
        }
        

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

                var user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
                var accesToken = jwtService.generateToken(user);
                var refreshToken = refreshTokenService.createRefreshToken(user.getId());
                Info info = new Info(user);
                Hasil hasil = new Hasil(accesToken, refreshToken.getToken() ,info);
                return AuthenticationResponse.builder().hasil(hasil).build();

        }

        public Object changePassword(String email,ChangePassword changePassword){
                var user = userRepository.findByEmail(email);
                System.out.println("INPUT : "+changePassword.getPasswordAsli());
                if(user.isPresent()&&!passwordEncoder.matches(changePassword.getPasswordAsli(),user.get().getPassword())){
                        return ResponseEntity.badRequest().body("Password Asli Yang Anda Masukan Salah");
                }else if(!changePassword.getPasswordBaru1().equals(changePassword.getPasswordBaru2())){
                        return ResponseEntity.badRequest().body("Password Tidak Sesuai Antara Password Baru Dan Password Konfirmasi");
                }
                user.get().setPassword(passwordEncoder.encode(changePassword.getPasswordBaru1()));
                userRepository.save(user.get());
                return ResponseEntity.ok("Password Berhasil Diubah");


        }
}
