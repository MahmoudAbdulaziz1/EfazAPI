package com.taj.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
public class SchoolRequestsModel {


    @Min(1)
    private int request_id;
    @NotNull
    private byte[] request_details_file;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="title should have at least 1 characters")
    private String request_title;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="explain should have at least 1 characters")
    private String request_explaination;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp request_display_date;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp request_expired_date;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp request_deliver_date;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp request_payment_date;
    @NotNull
    @Min(0)
    private int request_is_available;
    @NotNull
    @Min(0)
    private int request_is_conformied;
    @NotNull
    @Min(1)
    private int school_id;
    @NotNull
    @Min(1)
    private int request_category_id;
    @NotNull
    @Min(1)
    private int receive_palce_id;
    @NotNull
    @Min(0)
    private int extended_payment;


    public SchoolRequestsModel() {
    }

    public SchoolRequestsModel(int request_id, byte[] request_details_file, String request_title, String request_explaination,
                               Timestamp request_display_date, Timestamp request_expired_date, Timestamp request_deliver_date,
                               int request_is_available, int request_is_conformied, Timestamp request_payment_date, int school_id,
                               int request_category_id, int receive_palce_id, int extended_payment) {
        this.request_id = request_id;
        this.request_details_file = request_details_file;
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.request_deliver_date = request_deliver_date;
        this.request_payment_date = request_payment_date;
        this.request_is_available = request_is_available;
        this.request_is_conformied = request_is_conformied;
        this.school_id = school_id;
        this.request_category_id = request_category_id;
        this.receive_palce_id = receive_palce_id;
        this.extended_payment = extended_payment;
    }

    public SchoolRequestsModel(byte[] request_details_file, String request_title, String request_explaination,
                               Timestamp request_display_date, Timestamp request_expired_date, Timestamp request_deliver_date,
                               int request_is_available, int request_is_conformied, Timestamp request_payment_date, int school_id,
                               int request_category_id, int receive_palce_id, int extended_payment) {
        this.request_details_file = request_details_file;
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.request_deliver_date = request_deliver_date;
        this.request_payment_date = request_payment_date;
        this.request_is_available = request_is_available;
        this.request_is_conformied = request_is_conformied;
        this.school_id = school_id;
        this.request_category_id = request_category_id;
        this.receive_palce_id = receive_palce_id;
        this.extended_payment = extended_payment;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public byte[] getRequest_details_file() {
        return request_details_file;
    }

    public void setRequest_details_file(byte[] request_details_file) {
        this.request_details_file = request_details_file;
    }

    public String getRequest_title() {
        return request_title;
    }

    public void setRequest_title(String request_title) {
        this.request_title = request_title;
    }

    public String getRequest_explaination() {
        return request_explaination;
    }

    public void setRequest_explaination(String request_explaination) {
        this.request_explaination = request_explaination;
    }


    public Timestamp getRequest_display_date() {
        return request_display_date;
    }

    public void setRequest_display_date(Timestamp request_display_date) {
        this.request_display_date = request_display_date;
    }

    public Timestamp getRequest_expired_date() {
        return request_expired_date;
    }

    public void setRequest_expired_date(Timestamp request_expired_date) {
        this.request_expired_date = request_expired_date;
    }

    public Timestamp getRequest_deliver_date() {
        return request_deliver_date;
    }

    public void setRequest_deliver_date(Timestamp request_deliver_date) {
        this.request_deliver_date = request_deliver_date;
    }

    public Timestamp getRequest_payment_date() {
        return request_payment_date;
    }

    public void setRequest_payment_date(Timestamp request_payment_date) {
        this.request_payment_date = request_payment_date;
    }

    public int getRequest_is_available() {
        return request_is_available;
    }

    public void setRequest_is_available(int request_is_available) {
        this.request_is_available = request_is_available;
    }

    public int getRequest_is_conformied() {
        return request_is_conformied;
    }

    public void setRequest_is_conformied(int request_is_conformied) {
        this.request_is_conformied = request_is_conformied;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getRequest_category_id() {
        return request_category_id;
    }

    public void setRequest_category_id(int request_category_id) {
        this.request_category_id = request_category_id;
    }

    public int getReceive_palce_id() {
        return receive_palce_id;
    }

    public void setReceive_palce_id(int receive_palce_id) {
        this.receive_palce_id = receive_palce_id;
    }

    public int getExtended_payment() {
        return extended_payment;
    }

    public void setExtended_payment(int extended_payment) {
        this.extended_payment = extended_payment;
    }
}
