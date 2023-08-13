package com.test.jasamedika.services.EntityService;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.jasamedika.models.Photos;
import com.test.jasamedika.payload.request.entity.AdminPhotoRequest;
import com.test.jasamedika.payload.request.entity.PhotoRequest;
import com.test.jasamedika.repositories.PhotoRepository;
import com.test.jasamedika.repositories.UserRepository;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private UserRepository userRepository;
    @Value("${upload.path}")
    private String uploadPath;
    public Object adminUploadPhoto(MultipartFile file,String requestJson){
         try {
            if (file.isEmpty()) {
                return "Gagal mengunggah foto: Foto kosong";
            }
            ObjectMapper objectMapper = new ObjectMapper();
            AdminPhotoRequest request = objectMapper.readValue(requestJson,AdminPhotoRequest.class);
            String fileName = request.getNamaFile()+".jpeg";
            File destFile = new File(uploadPath + "/" + fileName);            
            Photos photos = Photos.builder()
            .nama(fileName)
            .lokasiData(uploadPath + "/" + fileName)
            .user(userRepository.findById(request.getUserId()).get())
            .build();
            photoRepository.save(photos);
            var user = userRepository.findById(photos.getUser().getId());
            user.get().setPhoto(photos.getLokasiData());
            userRepository.save(user.get());
            file.transferTo(destFile);
            return "Foto berhasil diunggah dan disimpan di: " + destFile.getAbsolutePath();
        } catch (IOException e) {
            return "Gagal mengunggah foto: " + e.getMessage();
        }
    }

    public Object uploadPhoto(Authentication auth,MultipartFile file,String requestJson){
         try {
            if (file.isEmpty()) {
                return "Gagal mengunggah foto: Foto kosong";
            }
            var user = userRepository.findByEmail(auth.getName());
            ObjectMapper objectMapper = new ObjectMapper();
            PhotoRequest request = objectMapper.readValue(requestJson,PhotoRequest.class);
            String fileName = request.getNamaFile()+".jpeg";
            File destFile = new File(uploadPath + "/" + fileName);            
            Photos photos = Photos.builder()
            .nama(fileName)
            .lokasiData(uploadPath + "/" + fileName)
            .user(user.get())
            .build();
            photoRepository.save(photos);
            user.get().setPhoto(photos.getLokasiData());
            userRepository.save(user.get());
            file.transferTo(destFile);
            return "Foto berhasil diunggah dan disimpan di: " + destFile.getAbsolutePath();
        } catch (IOException e) {
            return "Gagal mengunggah foto: " + e.getMessage();
        }
    }
}
