package com.taj.model;

/**
 * Created by User on 8/8/2018.
 */
public class SchoolRequestDto {


    private int request_id;
    private String request_title;
    private int request_count;
    private long request_display_date;

    public SchoolRequestDto(int request_id, String request_title, int request_count, long request_display_date) {
        this.request_id = request_id;
        this.request_title = request_title;
        this.request_count = request_count;
        this.request_display_date = request_display_date;
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

    public int getRequest_count() {
        return request_count;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
    }

    public long getRequest_display_date() {
        return request_display_date;
    }

    public void setRequest_display_date(long request_display_date) {
        this.request_display_date = request_display_date;
    }
}
