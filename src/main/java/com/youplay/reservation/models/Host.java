package com.youplay.reservation.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Host")
public class Host {

    @Id
    @JsonProperty("id")
    private long hostID;

    private String name;
    private int number;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

