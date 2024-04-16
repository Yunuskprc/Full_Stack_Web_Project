package com.example.ProjeStaj.controller;


import com.example.ProjeStaj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ProjeStaj.modal.User;
import com.example.ProjeStaj.exception.UserNotFound;
import com.example.ProjeStaj.exception.ResponseMessage;

import java.util.List;

@RestController
@CrossOrigin("https://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/user")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getByUserId(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFound(id) );
    }



    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id).map(user -> {
            user.setUserName(newUser.getUserName());
            user.setPassword(newUser.getPassword());
            user.setType(newUser.getType());
            return userRepository.save(user);
        }).orElseThrow(()->new UserNotFound(id));
    }


    @DeleteMapping("/user/{id}")
    ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long id){
        return userRepository.findById(id).map(user -> {
            userRepository.deleteById(id);
            String message = "Kullanıcı başarıyla silindi.";
            return ResponseEntity.ok(new ResponseMessage(message));
        }).orElseThrow(() -> new UserNotFound(id));
    }

}
