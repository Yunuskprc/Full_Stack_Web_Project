package com.example.ProjeStaj.modal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
public class Session {
    @Id
    @GeneratedValue
    private long sessionId;
    private String sessionNo;

    private long id;


    private long type;

    private LocalDateTime oturumBaslangic;

    private LocalDateTime oturumBitis;


    public long getSessionId() {
        return sessionId;
    }

    public String getSessionNo() {
        return sessionNo;
    }

    public long getId() {
        return id;
    }

    public long getType() {
        return type;
    }

    public LocalDateTime getOturumBaslangic() {
        return oturumBaslangic;
    }

    public LocalDateTime getOturumBitis() {
        return oturumBitis;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public void setSessionNo(String sessionNo) {
        this.sessionNo = sessionNo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(long type) {
        this.type = type;
    }

    public void setOturumBaslangic(LocalDateTime oturumBaslangic) {
        this.oturumBaslangic = oturumBaslangic;
    }

    public void setOturumBitis(LocalDateTime oturumBitis) {
        this.oturumBitis = oturumBitis;
    }
}
