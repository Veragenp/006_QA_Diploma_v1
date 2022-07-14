package ru.netology.web.data;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Answer500 {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
