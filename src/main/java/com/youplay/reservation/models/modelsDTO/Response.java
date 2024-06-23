package com.youplay.reservation.models.modelsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("version")
    private Object version;

    @JsonProperty("result")
    private Result result;

    @JsonProperty("httpStatusCode")
    private int httpStatusCode;

    @JsonProperty("message")
    private Object message;

    @JsonProperty("isError")
    private boolean isError;

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
