package com.example.ProjeStaj.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ImageService {

    @Value("${upload.dir}/")
    private String uploadDir;

    public String saveImage(MultipartFile imageFile) throws IOException {

        //anlık saat tarih verisini alıp kayıt yapılacak şekilde formatlama işlemi
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");


        String originalFilename = imageFile.getOriginalFilename();

        int lastDotIndex = originalFilename.lastIndexOf('.');
        String fileName = originalFilename.substring(0, lastDotIndex);
        String fileExtension = originalFilename.substring(lastDotIndex);

        String newFileName = now.format(formatter) + "_" + fileName + fileExtension;

        Path filePath = Paths.get(uploadDir + newFileName);
        Files.copy(imageFile.getInputStream(), filePath);
        return newFileName;
    }
}
