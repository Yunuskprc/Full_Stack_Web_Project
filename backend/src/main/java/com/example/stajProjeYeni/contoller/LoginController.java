package com.example.stajProjeYeni.contoller;

import com.example.stajProjeYeni.modal.Session;
import com.example.stajProjeYeni.modal.User;
import com.example.stajProjeYeni.repository.SessionRepository;
import com.example.stajProjeYeni.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository  sessionRepository;

    @PostMapping("/Login")
    ResponseEntity<String> loginControl(@RequestBody User request, HttpSession session){

        String userName = request.getUserName();
        String password = request.getPassword();
        User user = userRepository.findByUserNameAndPassword(userName,password);


        if(user != null){
            LocalDateTime now = LocalDateTime.now();
            session.setAttribute("sessionId", session.getId());
            Session newSession = new  Session();
            newSession.setSessionNo(session.getId());

            newSession.setId(user.getId());
            newSession.setType(user.getType());
            newSession.setOturumBaslangic(now);
            sessionRepository.save(newSession);

            return ResponseEntity.ok("Giriş Başarılı");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı adı veya şifre hatalı");
        }
    }


    @GetMapping("/logOut")
    @Transactional
    public ResponseEntity<String> logOutControl(HttpSession session) {

        String sessionId = (String) session.getAttribute("sessionId");
        if (sessionId != null) {
            sessionRepository.deleteBysessionNo(sessionId);
        }
        session.invalidate();
        return ResponseEntity.ok("Başarıyla çıkış yapıldı");
    }

}
