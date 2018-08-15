package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 8/15/2018.
 */
public class SchoolRequestNewDto {

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
    private int school_id;
    @NotNull
    private String request_category_name;
    @NotNull
    private int response_count;

    public SchoolRequestNewDto(int request_id,
                               @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String request_title,
                               @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String request_explaination,
                               @NotNull long request_display_date, @NotNull long request_expired_date, int school_id, @NotNull String request_category_name,
                               int response_count) {
        this.request_id = request_id;
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.school_id = school_id;
        this.request_category_name = request_category_name;
        this.response_count = response_count;
    }

    public SchoolRequestNewDto(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String request_title,
                               @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String request_explaination, @NotNull long request_display_date,
                               @NotNull long request_expired_date, int school_id, @NotNull String request_category_name, @NotNull int response_count) {
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.school_id = school_id;
        this.request_category_name = request_category_name;
        this.response_count = response_count;
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

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getRequest_category_name() {
        return request_category_name;
    }

    public void setRequest_category_name(String request_category_name) {
        this.request_category_name = request_category_name;
    }

    public int getResponse_count() {
        return response_count;
    }

    public void setResponse_count(int response_count) {
        this.response_count = response_count;
    }
}
