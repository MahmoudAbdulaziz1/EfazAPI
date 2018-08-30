package com.taj.model;

import javax.validation.constraints.*;

/**
 * Created by User on 8/30/2018.
 */
public class SchoolRequestsByIdModel {

    private int request_id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "title should have at least 1 characters")
    private String request_title;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "explain should have at least 1 characters")
    private String request_explaination;
    @NotNull
    private long request_display_date;
    @NotNull
    private long request_expired_date;
    @NotNull
    @Min(0)
    private int request_is_available;
    @NotNull
    @Min(0)
    private int request_is_conformied;//التعميد
    @NotNull
    @Min(1)
    private int school_id;
    private String school_name;
    private byte[] school_logo_image;

    @NotNull
    @Min(1)
    private int request_category_id;
    private String request_category_name;

    @NotNull
    @Min(0)
    private int extended_payment;   //مده الدفع
    @Min(1)
    private int request_count;


    public SchoolRequestsByIdModel(int request_id,
                                   @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String request_title,
                                   @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String request_explaination,
                                   @NotNull long request_display_date, @NotNull long request_expired_date, @NotNull @Min(0) int request_is_available,
                                   @NotNull @Min(0) int request_is_conformied, @NotNull @Min(1) int school_id, String school_name, byte[] school_logo_image,
                                   @NotNull @Min(1) int request_category_id, String request_category_name,
                                   @NotNull @Min(0) int extended_payment, @Min(1) int request_count) {
        this.request_id = request_id;
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.request_is_available = request_is_available;
        this.request_is_conformied = request_is_conformied;
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.request_category_id = request_category_id;
        this.request_category_name = request_category_name;
        this.extended_payment = extended_payment;
        this.request_count = request_count;
    }


    public SchoolRequestsByIdModel(
            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String request_title,
            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String request_explaination,
            @NotNull long request_display_date, @NotNull long request_expired_date, @NotNull @Min(0) int request_is_available,
            @NotNull @Min(0) int request_is_conformied, @NotNull @Min(1) int school_id, String school_name, byte[] school_logo_image,
            @NotNull @Min(1) int request_category_id, String request_category_name,
             @NotNull @Min(0) int extended_payment, @Min(1) int request_count) {
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.request_is_available = request_is_available;
        this.request_is_conformied = request_is_conformied;
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.request_category_id = request_category_id;
        this.request_category_name = request_category_name;
        this.extended_payment = extended_payment;
        this.request_count = request_count;
    }

    public SchoolRequestsByIdModel() {
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
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

    public long getRequest_display_date() {
        return request_display_date;
    }

    public void setRequest_display_date(long request_display_date) {
        this.request_display_date = request_display_date;
    }

    public long getRequest_expired_date() {
        return request_expired_date;
    }

    public void setRequest_expired_date(long request_expired_date) {
        this.request_expired_date = request_expired_date;
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

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public byte[] getSchool_logo_image() {
        return school_logo_image;
    }

    public void setSchool_logo_image(byte[] school_logo_image) {
        this.school_logo_image = school_logo_image;
    }

    public int getRequest_category_id() {
        return request_category_id;
    }

    public void setRequest_category_id(int request_category_id) {
        this.request_category_id = request_category_id;
    }

    public String getRequest_category_name() {
        return request_category_name;
    }

    public void setRequest_category_name(String request_category_name) {
        this.request_category_name = request_category_name;
    }

    public int getExtended_payment() {
        return extended_payment;
    }

    public void setExtended_payment(int extended_payment) {
        this.extended_payment = extended_payment;
    }

    public int getRequest_count() {
        return request_count;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
    }
}
