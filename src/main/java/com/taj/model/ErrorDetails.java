package com.taj.model;

import java.sql.Date;

/**
 * Created by User on 7/17/2018.
 */
public class ErrorDetails {

    private java.util.Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
