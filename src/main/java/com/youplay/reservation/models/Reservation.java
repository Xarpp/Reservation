package com.youplay.reservation.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationID;

    @ManyToOne
    @JoinColumn(name = "StatusID", referencedColumnName = "StatusID")
    private Status status;

    private int reservationNumber;

    private String user;

    @Temporal(TemporalType.DATE)
    private Date reservationDateStart;

    @Temporal(TemporalType.DATE)
    private Date reservationDateEnd;

    @Temporal(TemporalType.DATE)
    private Date DateCreate;

    @ManyToOne
    @JoinColumn(name = "PlatformID", referencedColumnName = "PlatformID")
    private Platform platform;

    private int userPlatformID;

    private int pcNumber;

    private String comment;

    private boolean isDeleted;

    public Reservation() {
    }

    public long getReservationID() {
        return reservationID;
    }

    public void setReservationID(long reservationID) {
        this.reservationID = reservationID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getReservationDateStart() {
        return reservationDateStart;
    }

    public void setReservationDateStart(Date reservationDateStart) {
        this.reservationDateStart = reservationDateStart;
    }

    public Date getReservationDateEnd() {
        return reservationDateEnd;
    }

    public void setReservationDateEnd(Date reservationDateEnd) {
        this.reservationDateEnd = reservationDateEnd;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public int getUserPlatformID() {
        return userPlatformID;
    }

    public void setUserPlatformID(int userPlatformID) {
        this.userPlatformID = userPlatformID;
    }

    public int getPcNumber() {
        return pcNumber;
    }

    public void setPcNumber(int pcNumber) {
        this.pcNumber = pcNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        DateCreate = dateCreate;
    }
}