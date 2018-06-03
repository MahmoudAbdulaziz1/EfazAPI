package com.taj.model;

import java.sql.Timestamp;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
public class SchoolRequestsModel {


    private int request_id;
    private byte[] request_logo;
    private String request_title;
    private String request_explaination;
    private String request_category;
    private Timestamp request_display_date;
    private Timestamp request_expired_date;
    private Timestamp request_deliver_date;


    public SchoolRequestsModel() {
    }

    public SchoolRequestsModel(int request_id, byte[] request_logo, String request_title, String request_explaination,
                               String request_category, Timestamp request_display_date, Timestamp request_expired_date, Timestamp request_deliver_date) {
        this.request_id = request_id;
        this.request_logo = request_logo;
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_category = request_category;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.request_deliver_date = request_deliver_date;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public byte[] getRequest_logo() {
        return request_logo;
    }

    public void setRequest_logo(byte[] request_logo) {
        this.request_logo = request_logo;
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

    public String getRequest_category() {
        return request_category;
    }

    public void setRequest_category(String request_category) {
        this.request_category = request_category;
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
}
