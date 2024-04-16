package com.example.stajProjeYeni.contoller;

import com.example.stajProjeYeni.modal.HospitaalRecord;
import com.example.stajProjeYeni.modal.User;
import com.example.stajProjeYeni.repository.RecordRepository;
import com.example.stajProjeYeni.repository.SessionRepository;
import com.example.stajProjeYeni.repository.UserRepository;
import com.example.stajProjeYeni.services.ImageService;
import com.example.stajProjeYeni.services.SearchService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RecordController {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SearchService searchService;



    @GetMapping("/arama")
    public ResponseEntity<?> searchRecord(@RequestParam("aranacakTur") long aranacakTur,@RequestParam("aramaKelimesi") String aramaKelimesi) {
        ResponseEntity<?> response = null;

        if (aranacakTur == 1) {
            response = searchService.searchByHastaAd(aramaKelimesi);
        } else if (aranacakTur == 2) {
            response = searchService.searchByHastaSoyad(aramaKelimesi);
        } else if (aranacakTur == 3){
            try {
                long tc = Long.parseLong(aramaKelimesi);
                response = searchService.searchByHastaTc(tc);
            } catch (NumberFormatException e) {
                response = ResponseEntity.badRequest().body("Hatalı TC Kimlik Numarası formatı. Tekrar Deneyin");
            }

        } else if (aranacakTur == 4) {
            response = searchService.searchByHastaTani(aramaKelimesi);
        } else if (aranacakTur == 5) {
            response = searchService.searchByHastaTaniDetay(aramaKelimesi);
        } else if (aranacakTur == 6) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(aramaKelimesi, formatter);
            response = searchService.searchByKayitTarih(date);
        }

        if (response == null) {
            System.out.println("contorl");
            response = ResponseEntity.badRequest().body("Geçersiz aranacak tür değeri.");
        }
        return response;
    }


    // Kayıt eklendikten sonra server reslenmeden yeni foto gözükmüyor.
        @PostMapping("/kayitEkle")
        public ResponseEntity<String> addRecocrd(
                @RequestParam("hastaAd") String hastaAd,
                @RequestParam("hastaSoyad") String hastaSoyad,
                @RequestParam("hastaTC") long hastaTC,
                @RequestParam("hastaTaniBaslik") String hastaTaniBaslik,
                @RequestParam("hastaTaniDetay") String hastaTaniDetay,
                @RequestParam("resimURL") MultipartFile resimURL,
                @NotNull HttpSession session, HttpServletRequest request
        ) {

            try {
                LocalDateTime now = LocalDateTime.now();

                String resimURLFileName = imageService.saveImage(resimURL);
                HospitaalRecord newRecord = new HospitaalRecord();
                newRecord.setHastaAd(hastaAd);
                newRecord.setHastaSoyad(hastaSoyad);
                newRecord.setHastaTC(hastaTC);
                newRecord.setHastaTaniBaslik(hastaTaniBaslik);
                newRecord.setHastaTaniDetay(hastaTaniDetay);
                newRecord.setResimURL(resimURLFileName);
                newRecord.setKayitTarih(now);
                Cookie[] cookies = request.getCookies();

                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("JSESSIONID")) {
                            String sessionId = cookie.getValue();
                            long id = sessionRepository.findIdBySessionNo(sessionId);
                            newRecord.setId(id);

                            recordRepository.save(newRecord);
                        }
                    }
                }

                return ResponseEntity.ok("Kayıt başarılı");


            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.ok("Kayıt başarısız");
            }

        }


        @GetMapping("/kayitGoster")
        public List<HospitaalRecord> gettAllRecord(){
            return recordRepository.findAll();
        }


    @PutMapping("/kayitGuncelle")
    public ResponseEntity<String> updateRecord(@RequestBody HospitaalRecord updateRecord){
        Optional<HospitaalRecord> optionalRecord = recordRepository.findById(updateRecord.getDosyaİd());

        optionalRecord.map(record -> {
            record.setHastaAd(updateRecord.getHastaAd());
            record.setHastaSoyad(updateRecord.getHastaSoyad());
            record.setHastaTC(updateRecord.getHastaTC());
            record.setHastaTaniBaslik(updateRecord.getHastaTaniBaslik());
            record.setHastaTaniDetay(updateRecord.getHastaTaniDetay());
            record.setKayitTarih(updateRecord.getKayitTarih());
            recordRepository.save(record);
            return ResponseEntity.ok("Güncelleme işlemi başarı ile gerçekleşti");
        });

        if (optionalRecord.isPresent()) {
            return ResponseEntity.ok("Güncelleme işlemi başarı ile gerçekleşti");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kayıt bulunamadı");
        }
    }

    @DeleteMapping("/kayitSil")
    public ResponseEntity<String> deleteRecord(@RequestParam long id,@RequestParam long dosyaİd) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getType() == 0){
                Optional<HospitaalRecord> optionalRecord = recordRepository.findById(dosyaİd);

                if (optionalRecord.isPresent()) {
                    recordRepository.deleteById(dosyaİd);
                    return ResponseEntity.ok("Kayıt başarılı bir şekilde silindi.");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kayıt bulunamadı.");
                }
            }else{
                return ResponseEntity.status(400).body("Silmek için yetkiniz yok");
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kayıt bulunamadı.");
        }
    }


}
