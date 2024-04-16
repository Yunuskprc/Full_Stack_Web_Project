package com.example.stajProjeYeni.repository;

import com.example.stajProjeYeni.modal.HospitaalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<HospitaalRecord,Long> {
    List<HospitaalRecord> findByHastaAdContaining(String aramaKelimesi);
    List<HospitaalRecord> findByHastaSoyadContaining(String aramaKelimesi);

    List<HospitaalRecord> findByHastaTC(Long aramaKelimesi);

    List<HospitaalRecord> findByHastaTaniBaslikContaining(String aramaKelimesi);

    List<HospitaalRecord> findByHastaTaniDetayContaining(String aramaKelimesi);

    @Query("SELECT r FROM HospitaalRecord r WHERE DATE(r.kayitTarih) = :date")
    List<HospitaalRecord> findByKayitTarihDate(LocalDate date);
}
