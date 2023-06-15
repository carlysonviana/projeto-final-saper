package com.saper.clinicalotus.dto;

import java.time.Instant;

public class ErrorDTO {
    public Instant timeStamp;

    public String status;

    public String error;

    public String message;

    public String path;

    public ErrorDTO(){};

    public ErrorDTO(Instant timestamp, String status, String error, String message, String path) {
        this.timeStamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timestamp) {
        this.timeStamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
