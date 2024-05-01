package com.youplay.reservation.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Host")
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hostID;

    private String name;

    public Host() {
    }

    public long getHostID() {
        return hostID;
    }

    public void setHostID(long hostID) {
        this.hostID = hostID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

