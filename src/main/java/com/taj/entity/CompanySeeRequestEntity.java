package com.taj.entity;

import javax.persistence.*;

/**
 * Created by User on 7/9/2018.
 */
@Entity(name = "efaz_company_see_request")
@Table
public class CompanySeeRequestEntity {

    @Id
    @GeneratedValue
    private int seen_id;
    @Column
    private int request_id;
    @Column
    private int request_company_id;

    public CompanySeeRequestEntity() {
    }

    public CompanySeeRequestEntity(int seen_id, int request_id, int request_company_id) {
        this.seen_id = seen_id;
        this.request_id = request_id;
        this.request_company_id = request_company_id;
    }

    public CompanySeeRequestEntity(int request_id, int request_company_id) {
        this.request_id = request_id;
        this.request_company_id = request_company_id;
    }

    public int getSeen_id() {
        return seen_id;
    }

    public void setSeen_id(int seen_id) {
        this.seen_id = seen_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getRequest_company_id() {
        return request_company_id;
    }

    public void setRequest_company_id(int request_company_id) {
        this.request_company_id = request_company_id;
    }
}
