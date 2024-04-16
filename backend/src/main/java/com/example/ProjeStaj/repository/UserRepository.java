package com.example.ProjeStaj.repository;
import  org.springframework.data.jpa.repository.JpaRepository;
import  com.example.ProjeStaj.modal.User;
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserNameAndPassword(String userName,String password);

    User findByHospitalId(long hospitalId);
}
