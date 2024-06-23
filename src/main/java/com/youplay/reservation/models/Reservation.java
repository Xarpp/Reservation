package com.youplay.reservation.models;

import com.youplay.reservation.validation.ValidReservationDateRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

@ValidReservationDateRange
@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationID;

    @ManyToOne
    @JoinColumn(name = "StatusID", referencedColumnName = "StatusID")
    private Status status;

    @Column(name = "reservation_number", nullable = false)
    @NotNull
    private long reservationNumber;

    private String user;


    @Column(name = "reservation_date_start")
    private LocalDateTime reservationDateStart;


    @Column(name = "reservation_date_end")
    private LocalDateTime reservationDateEnd;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "PlatformID", referencedColumnName = "PlatformID")
    private Platform platform;

    private int userPlatformID = -1;

    @ManyToMany()
    @JoinTable(
        name = "reservation_host",
        joinColumns = @JoinColumn(name = "reservationID"),
        inverseJoinColumns = @JoinColumn(name = "hostID")
    )
    private Set<Host> hosts;

    private String comment;

    private boolean isDeleted = false;

    public Reservation() {
    }

    public Set<Host> getHosts() {
        return hosts;
    }

    public void setHosts(Set<Host> hosts) {
        this.hosts = hosts;
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

    public long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getReservationDateStart() {
        return this.reservationDateStart;
    }

    public void setReservationDateStart(LocalDateTime reservationDateStart) {
        this.reservationDateStart = reservationDateStart;
    }

    public LocalDateTime getReservationDateEnd() {
        return this.reservationDateEnd;
    }

    public void setReservationDateEnd(LocalDateTime reservationDateEnd) {
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

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }



}