package com.example.stajProjeYeni.contoller;


import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/isLoggin")
    public ResponseEntity<String> isLoggin(HttpSession session){
        Object sessionId = session.getAttribute("sessionId");

        if (sessionId != null) {
            return ResponseEntity.ok("");
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lütfen giriş yapınız");
        }

    }

}
