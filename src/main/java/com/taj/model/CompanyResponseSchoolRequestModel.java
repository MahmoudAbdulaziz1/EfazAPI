package com.taj.model;

import javax.validation.constraints.Min;

/**
 * Created by User on 7/8/2018.
 */
public class CompanyResponseSchoolRequestModel {

    private int response_id;
    @Min(1)
    private int responsed_company_id;
    @Min(1)
    private int responsed_request_id;
    private int responsed_from;
    private int responsed_to;
    private int is_aproved;
    @Min(1)
    private double responsed_cost;
    private long response_date;
    private String response_desc;

    public CompanyResponseSchoolRequestModel() {
    }

    public CompanyResponseSchoolRequestModel(int response_id, int responsed_company_id, int responsed_request_id, int responsed_from,
                                             int responsed_to, double responsed_cost, int is_aproved, long response_date, String response_desc) {
        this.response_id = response_id;
        this.responsed_company_id = responsed_company_id;
        this.responsed_request_id = responsed_request_id;
        this.responsed_from = responsed_from;
        this.responsed_to = responsed_to;
        this.responsed_cost = responsed_cost;
        this.is_aproved = is_aproved;
        this.response_date = response_date;
        this.response_desc = response_desc;
    }

    public CompanyResponseSchoolRequestModel(@Min(1) int responsed_company_id, @Min(1) int responsed_request_id,
                                             @Min(1) int responsed_from, @Min(1) int responsed_to, @Min(0) int is_aproved,
                                             @Min(1) double responsed_cost, @Min(1) int response_id, String response_desc) {
        this.responsed_company_id = responsed_company_id;
        this.responsed_request_id = responsed_request_id;
        this.responsed_from = responsed_from;
        this.responsed_to = responsed_to;
        this.is_aproved = is_aproved;
        this.responsed_cost = responsed_cost;
        this.response_id = response_id;
        this.response_desc = response_desc;
    }

    public CompanyResponseSchoolRequestModel(@Min(1) int responsed_company_id, @Min(1) int responsed_request_id,
                                             @Min(1) int responsed_from, @Min(1) int responsed_to, @Min(0) int is_aproved,
                                             @Min(1) double responsed_cost, long response_date, String response_desc) {
        this.responsed_company_id = responsed_company_id;
        this.responsed_request_id = responsed_request_id;
        this.responsed_from = responsed_from;
        this.responsed_to = responsed_to;
        this.is_aproved = is_aproved;
        this.responsed_cost = responsed_cost;
        this.response_date = response_date;
        this.response_desc = response_desc;
    }

    public CompanyResponseSchoolRequestModel(@Min(1) int response_id, @Min(1) int responsed_company_id, @Min(1) int responsed_request_id,
                                             @Min(1) int responsed_from, @Min(1) int responsed_to, @Min(0) int is_aproved, @Min(1) double responsed_cost,
                                             long response_date, String response_desc) {
        this.response_id = response_id;
        this.responsed_company_id = responsed_company_id;
        this.responsed_request_id = responsed_request_id;
        this.responsed_from = responsed_from;
        this.responsed_to = responsed_to;
        this.is_aproved = is_aproved;
        this.responsed_cost = responsed_cost;
        this.response_date = response_date;
        this.response_desc = response_desc;
    }


    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public int getResponsed_company_id() {
        return responsed_company_id;
    }

    public void setResponsed_company_id(int responsed_company_id) {
        this.responsed_company_id = responsed_company_id;
    }

    public int getResponsed_request_id() {
        return responsed_request_id;
    }

    public void setResponsed_request_id(int responsed_request_id) {
        this.responsed_request_id = responsed_request_id;
    }

    public int getResponsed_from() {
        return responsed_from;
    }

    public void setResponsed_from(int responsed_from) {
        this.responsed_from = responsed_from;
    }

    public int getResponsed_to() {
        return responsed_to;
    }

    public void setResponsed_to(int responsed_to) {
        this.responsed_to = responsed_to;
    }

    public double getResponsed_cost() {
        return responsed_cost;
    }

    public void setResponsed_cost(double responsed_cost) {
        this.responsed_cost = responsed_cost;
    }

    public int getIs_aproved() {
        return is_aproved;
    }

    public void setIs_aproved(int is_aproved) {
        this.is_aproved = is_aproved;
    }

    public long getResponse_date() {
        return response_date;
    }

    public void setResponse_date(long response_date) {
        this.response_date = response_date;
    }

    public String getResponse_desc() {
        return response_desc;
    }

    public void setResponse_desc(String response_desc) {
        this.response_desc = response_desc;
    }
}
