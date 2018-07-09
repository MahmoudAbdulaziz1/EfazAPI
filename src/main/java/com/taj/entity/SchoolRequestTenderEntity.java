package com.taj.entity;

import javax.persistence.*;

/**
 * Created by User on 7/9/2018.
 */
@Entity(name = "takatf_request_tender")
@Table
public class SchoolRequestTenderEntity {

    @Id
    @GeneratedValue
    private int request_id;
    @Column
    private int request_school_id;
    @Column
    private int request_tender_id;
    @Column
    private int is_aproved;

    public SchoolRequestTenderEntity() {
    }

    public SchoolRequestTenderEntity(int request_id, int request_school_id, int request_tender_id, int is_aproved) {
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
