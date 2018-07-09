package com.taj.model;

/**
 * Created by User on 7/8/2018.
 */
public class TakatafTenderRequestModel {

    private int request_id, request_school_id, request_tender_id, is_aproved;

    public TakatafTenderRequestModel() {
    }

    public TakatafTenderRequestModel(int request_id, int request_school_id, int request_tender_id, int is_aproved) {
        this.request_id = request_id;
        this.request_school_id = request_school_id;
        this.request_tender_id = request_tender_id;
        this.is_aproved = is_aproved;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getRequest_school_id() {
        return request_school_id;
    }

    public void setRequest_school_id(int request_school_id) {
        this.request_school_id = request_school_id;
    }

    public int getRequest_tender_id() {
        return request_tender_id;
    }

    public void setRequest_tender_id(int request_tender_id) {
        this.request_tender_id = request_tender_id;
    }

    public int getIs_aproved() {
        return is_aproved;
    }

    public void setIs_aproved(int is_aproved) {
        this.is_aproved = is_aproved;
    }
}
