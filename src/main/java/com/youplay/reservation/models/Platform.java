package com.youplay.reservation.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Platform")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long platformID;

    private String name;

    public Platform() {
    }

    public long getPlatformID() {
        return platformID;
    }

    public void setPlatformID(long platformID) {
        this.platformID = platformID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
