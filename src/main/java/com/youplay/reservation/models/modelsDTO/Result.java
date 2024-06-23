package com.youplay.reservation.models.modelsDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.youplay.reservation.models.Host;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    @JsonProperty("data")
    private List<Host> data;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("nextCursor")
    private String nextCursor;

    @JsonProperty("prevCursor")
    private String prevCursor;

    public List<Host> getData() {
        return data;
    }

    public void setData(List<Host> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public String getPrevCursor() {
        return prevCursor;
    }

    public void setPrevCursor(String prevCursor) {
        this.prevCursor = prevCursor;
    }
}
