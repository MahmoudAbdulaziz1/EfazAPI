package com.taj.model;

/**
 * Created by User on 8/28/2018.
 */
public class schoolCategoriesToWEBSITE {


    private int request_category_id;
    private String request_category_name;
    private int request_count;
    private int school_count;
    private int offer_count;

    public schoolCategoriesToWEBSITE(int request_category_id, String request_category_name, int request_count, int school_count, int offer_count) {
        this.request_category_id = request_category_id;
        this.request_category_name = request_category_name;
        this.request_count = request_count;
        this.school_count = school_count;
        this.offer_count = offer_count;
    }

    public schoolCategoriesToWEBSITE() {
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

    public int getRequest_count() {
        return request_count;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
    }

    public int getSchool_count() {
        return school_count;
    }

    public void setSchool_count(int school_count) {
        this.school_count = school_count;
    }

    public int getOffer_count() {
        return offer_count;
    }

    public void setOffer_count(int offer_count) {
        this.offer_count = offer_count;
    }
}
