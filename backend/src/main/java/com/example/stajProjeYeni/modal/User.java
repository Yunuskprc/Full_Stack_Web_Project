package com.example.stajProjeYeni.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String userName;

    private String password;

    private long type;

    private String name;

    private String last_name;

    private long hospitalId;




    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public long getType() {
        return type;
    }

    public long getHospitalId() {
        return hospitalId;
    }

    public String getLastName() {
        return last_name;
    }

    public String getName() {
        return name;
    }



    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(long type) {
        this.type = type;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHospitalId(long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
}
