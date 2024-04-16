package com.example.stajProjeYeni.repository;

import com.example.stajProjeYeni.modal.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    Session findBySessionNo(String SessionNo);

    Session findById(long id);

    @Query("SELECT s.id FROM Session s WHERE s.sessionNo = :sessionNo")
    Long findIdBySessionNo(@Param("sessionNo") String sessionNo);

    @Modifying
    @Transactional
    Long deleteBysessionNo(String sessionNo);

}
