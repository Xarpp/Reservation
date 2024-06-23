package com.youplay.reservation.models.modelsDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDTO {
    private Long id = null;
    private LocalDateTime date;
    private int duration;
    private String note;
    private String pin;
    private int status = 0;
    private List<HostDTO> hosts;

    public ReservationDTO() {
    }

    public ReservationDTO(LocalDateTime date, int duration, String note, String pin, List<HostDTO> hosts) {
        this.date = date;
        this.duration = duration;
        this.note = note;
        this.pin = pin;
        this.hosts = hosts;
    }

    public ReservationDTO(Long id, LocalDateTime date, int duration, String note, String pin, List<HostDTO> hosts) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.note = note;
        this.pin = pin;
        this.hosts = hosts;
    }

    public ReservationDTO(Long id, int status) {
        this.id = id;
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<HostDTO> getHosts() {
        return hosts;
    }

    public void setHosts(List<HostDTO> hosts) {
        this.hosts = hosts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}