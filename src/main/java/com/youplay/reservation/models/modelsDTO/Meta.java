package com.youplay.reservation.models.modelsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {
    @JsonProperty("nextCursor")
    private String nextCursor;

    @JsonProperty("previousCursor")
    private int previousCursor;

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public int getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(int previousCursor) {
        this.previousCursor = previousCursor;
    }
}
