package com.example.ProjeStaj.exception;

public class UserNotFound extends RuntimeException {

    public UserNotFound(Long id){
        super("id:"+id+" nolu kullanıcı bulunamadı");
    }

}
