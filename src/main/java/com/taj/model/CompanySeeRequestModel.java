package com.taj.model;

import javax.validation.constraints.Min;

/**
 * Created by User on 7/3/2018.
 */
public class CompanySeeRequestModel {


    private int seen_id;
    @Min(1)
    private int request_id;
    @Min(1)
    private int request_company_id;

    public CompanySeeRequestModel() {
    }

    public CompanySeeRequestModel(int seen_id, int request_id, int request_company_id) {
        this.seen_id = seen_id;
        this.request_id = request_id;
        this.request_company_id = request_company_id;
    }

    public CompanySeeRequestModel(int request_id, int request_company_id) {
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
