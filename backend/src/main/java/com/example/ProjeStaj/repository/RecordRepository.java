package com.example.ProjeStaj.repository;

import com.example.ProjeStaj.modal.HospitaalRecord;
import com.example.ProjeStaj.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository  extends JpaRepository<HospitaalRecord,Long> {
}
