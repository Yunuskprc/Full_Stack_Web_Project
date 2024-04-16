package com.example.ProjeStaj.controller;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.example.ProjeStaj.repository.SessionRepository;
import com.example.ProjeStaj.repository.RecordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ProjeStaj.modal.HospitaalRecord;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RecordController {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("/kayitEkle")
    public ResponseEntity<String> KayitEkle(
            @RequestParam("hastaAd") String hastaAd,
            @RequestParam("hastaSoyad") String hastaSoyad,
            @RequestParam("hastaTC") long hastaTC,
            @RequestParam("hastaTaniBaslik") String hastaTaniBaslik,
            @RequestParam("hastaTaniDetay") String hastaTaniDetay,
            @RequestParam("resimURL") MultipartFile resimURL,
            HttpSession session
    ) {

        try {
            String resimURLFileName = imageService.saveImage(resimURL);
            HospitaalRecord newRecord = new HospitaalRecord();

            newRecord.setHastaAd(hastaAd);
            newRecord.setHastaSoyad(hastaSoyad);
            newRecord.setHastaTC(hastaTC);
            newRecord.setHastaTaniBaslik(hastaTaniBaslik);
            newRecord.setHastaTaniDetay(hastaTaniDetay);
            newRecord.setResimURL(resimURLFileName);

            Object userId = session.getAttribute("userId");

            System.out.println(userId.toString());

            /*
            long id = sessionRepository.findIdBySessionNo(sessionId);
            System.out.println("Deneme2" + id);
            newRecord.setId(id);

            recordRepository.save(newRecord);


             */
            return ResponseEntity.ok("Kayıt başarılı");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("Kayıt başarısız");
        }

    }

}
