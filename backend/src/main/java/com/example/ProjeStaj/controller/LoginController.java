package com.example.ProjeStaj.controller;

import com.example.ProjeStaj.repository.SessionRepository;
import com.example.ProjeStaj.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ProjeStaj.modal.LoginRequest;
import com.example.ProjeStaj.modal.User;
import com.example.ProjeStaj.modal.Session;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository  sessionRepository;

    @PostMapping("/Login")
    ResponseEntity<String> loginControl(@RequestBody LoginRequest request, HttpSession session){

        String userName = request.getUserName();
        String password = request.getPassword();
        User user = userRepository.findByUserNameAndPassword(userName,password);
        System.out.println(userName + "   " + password);

        if(user != null){
            LocalDateTime now = LocalDateTime.now();
            session.setAttribute("sessionId", session.getId());
            System.out.println(session.getAttribute("sessionId"));
            Session newSession = new  Session();
            newSession.setSessionNo(session.getId());
            newSession.setId(user.getId());
            newSession.setType(user.getType());
            newSession.setOturumBaslangic(now);
            sessionRepository.save(newSession);
            System.out.println(session.getId());
            return ResponseEntity.ok("Giriş Başarılı");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı adı veya şifre hatalı");
        }
    }

    @GetMapping("/logOut")
    ResponseEntity<String> logOutControl(HttpSession session){
        String userId = (String) session.getAttribute("sessionId");
        System.out.println(userId);
        if (userId != null) {
            LocalDateTime now = LocalDateTime.now();
            Session updateSession = sessionRepository.findBySessionNo(userId);
            updateSession.setOturumBitis(now);
            sessionRepository.save(updateSession);
        }

        session.invalidate(); // Oturumu sonlandır

        return ResponseEntity.ok("Başarıyla çıkış yapıldı");
    }




}
