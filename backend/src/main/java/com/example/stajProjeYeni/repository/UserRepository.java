package com.example.stajProjeYeni.repository;

import com.example.stajProjeYeni.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserNameAndPassword(String userName,String password);

    User findByHospitalId(long hospitalId);
}
