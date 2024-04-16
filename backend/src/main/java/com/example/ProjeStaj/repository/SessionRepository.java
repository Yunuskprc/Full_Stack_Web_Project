package com.example.ProjeStaj.repository;

import com.example.ProjeStaj.modal.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    Session findBySessionNo(String SessionNo);

    Session findById(long id);

    @Query("SELECT s.id FROM Session s WHERE s.sessionNo = :sessionNo")
    Long findIdBySessionNo(@Param("sessionNo") String sessionNo);

}
