package com.example.ProjeStaj.controller;

import com.example.ProjeStaj.modal.RegisterModal;
import com.example.ProjeStaj.modal.User;
import com.example.ProjeStaj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/Register")
    public ResponseEntity<String> registerControl(@RequestBody RegisterModal request) {
        String userName = request.getUserName();
        String password = request.getPassword();
        String name = request.getName();
        String last_name = request.getLast_name();
        long type = 1;

        User user = userRepository.findByUserNameAndPassword(userName, password);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı adı veya şifre hatalı");
        } else {
            long randomHospitalId = generateRandomHospitalId();

            while (userRepository.findByHospitalId(randomHospitalId) != null) {
                randomHospitalId = generateRandomHospitalId(); // Yeniden rastgele bir sayı oluştur
            }

            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setPassword(password);
            newUser.setName(name);
            newUser.setLastName(last_name);
            newUser.setType(type);
            newUser.setHospitalId(randomHospitalId);
            userRepository.save(newUser);
            return ResponseEntity.ok("Kayıt Başarılı");
        }
    }

    private long generateRandomHospitalId() {
        Random random = new Random();
        return 1000000 + random.nextInt(9000000);
    }

}
