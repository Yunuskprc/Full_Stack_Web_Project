package com.example.stajProjeYeni.services;

import com.example.stajProjeYeni.modal.HospitaalRecord;
import com.example.stajProjeYeni.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    RecordRepository recordRepository;
    public ResponseEntity<List<HospitaalRecord>> searchByHastaAd(String aramaKelimesi) {
        List<HospitaalRecord> results = recordRepository.findByHastaAdContaining(aramaKelimesi);
        return ResponseEntity.ok(results);
        // results null olsa bile boş data döneceği için sorun olmayacak.
    }

    public ResponseEntity<List<HospitaalRecord>> searchByHastaSoyad(String aramakelimesi){
        List<HospitaalRecord> results = recordRepository.findByHastaSoyadContaining(aramakelimesi);
        return  ResponseEntity.ok(results);
    }

    public ResponseEntity<List<HospitaalRecord>> searchByHastaTc(long aramakelimesi){
        List<HospitaalRecord> results = recordRepository.findByHastaTC(aramakelimesi);
        return  ResponseEntity.ok(results);
    }

    public ResponseEntity<List<HospitaalRecord>> searchByHastaTani(String aramakelimesi){
        List<HospitaalRecord> results = recordRepository.findByHastaTaniBaslikContaining(aramakelimesi);
        return  ResponseEntity.ok(results);
    }

    public ResponseEntity<List<HospitaalRecord>> searchByHastaTaniDetay(String aramakelimesi){
        List<HospitaalRecord> results = recordRepository.findByHastaTaniDetayContaining(aramakelimesi);
        return  ResponseEntity.ok(results);
    }

    public ResponseEntity<List<HospitaalRecord>> searchByKayitTarih(LocalDate date){
        List<HospitaalRecord> results = recordRepository.findByKayitTarihDate(date);
        return  ResponseEntity.ok(results);
    }


}
