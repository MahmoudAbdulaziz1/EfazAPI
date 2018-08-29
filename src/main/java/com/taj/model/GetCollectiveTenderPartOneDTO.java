package com.taj.model;

/**
 * Created by User on 8/29/2018.
 */
public class GetCollectiveTenderPartOneDTO {


    private int request_id;
    private String request_title;
    private String request_explaination;
    private long request_display_date;
    private long request_expired_date;
    private int school_id;
    private int response_count;

    public GetCollectiveTenderPartOneDTO(int request_id, String request_title, String request_explaination,
                                         long request_display_date, long request_expired_date, int school_id,
                                         int response_count) {
        this.request_id = request_id;
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.school_id = school_id;
        this.response_count = response_count;


    }

    public GetCollectiveTenderPartOneDTO(String request_title, String request_explaination, long request_display_date, long request_expired_date
            , int school_id, int response_count) {
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.school_id = school_id;
        this.response_count = response_count;

    }

    public GetCollectiveTenderPartOneDTO() {
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

    public int getResponse_count() {
        return response_count;
    }

    public void setResponse_count(int response_count) {
        this.response_count = response_count;
    }
}
