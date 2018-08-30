package com.taj.model;

/**
 * Created by User on 8/30/2018.
 */
public class SchoolHistoryRequestDTO {

    private int request_id;
    private String request_title;
    private int request_count;
    private long request_date;
    private long response_date;

    public SchoolHistoryRequestDTO(int request_id, String request_title, int request_count, long request_date, long response_date) {
        this.request_id = request_id;
        this.request_title = request_title;
        this.request_count = request_count;
        this.request_date = request_date;
        this.response_date = response_date;
    }

    public SchoolHistoryRequestDTO() {
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

    public long getRequest_date() {
        return request_date;
    }

    public void setRequest_date(long request_date) {
        this.request_date = request_date;
    }

    public long getResponse_date() {
        return response_date;
    }

    public void setResponse_date(long response_date) {
        this.response_date = response_date;
    }
}
