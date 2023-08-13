package com.test.jasamedika.controller.pegawai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.test.jasamedika.services.EntityService.PhotoService;

@RestController
@RequestMapping("/pegawai/admin-ubah-photo")
public class AdminPhotoController {
    @Autowired
    private PhotoService service;

    @PostMapping
    public Object uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("request") String request){
        return service.adminUploadPhoto(file,request);
    }
}
