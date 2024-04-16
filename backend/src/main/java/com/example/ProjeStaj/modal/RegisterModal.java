package com.example.ProjeStaj.modal;

public class RegisterModal {
    private String userName;
    private String password;

    private String name;

    private String last_name;

    private long hospitalId;

    private long type;



    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public long getHospitalId() {
        return hospitalId;
    }

    public String getLast_name() {
        return last_name;
    }

    public long getType() {
        return type;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setType(long type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHospitalId(long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

}
