package com.taj.model;

/**
 * Created by User on 9/2/2018.
 */
public class CompanyRequestCollectiveTenderModel {

    private int response_id;
    private int response_takataf_company_id;
    private int response_takataf_request_id;
    private double responsed_cost;
    private int is_aproved;
    private long response_date;
    private int responsed_from;
    private int responsed_to;

    public CompanyRequestCollectiveTenderModel(int response_id, int response_takataf_company_id, int response_takataf_request_id,
                                               double responsed_cost, int is_aproved, long response_date, int responsed_from, int responsed_to) {
        this.response_id = response_id;
        this.response_takataf_company_id = response_takataf_company_id;
        this.response_takataf_request_id = response_takataf_request_id;
        this.responsed_cost = responsed_cost;
        this.is_aproved = is_aproved;
        this.response_date = response_date;
        this.responsed_from = responsed_from;
        this.responsed_to = responsed_to;
    }

    public CompanyRequestCollectiveTenderModel(int response_takataf_company_id, int response_takataf_request_id,
                                               double responsed_cost, int is_aproved, long response_date, int responsed_from, int responsed_to) {
        this.response_takataf_company_id = response_takataf_company_id;
        this.response_takataf_request_id = response_takataf_request_id;
        this.responsed_cost = responsed_cost;
        this.is_aproved = is_aproved;
        this.response_date = response_date;
        this.responsed_from = responsed_from;
        this.responsed_to = responsed_to;
    }

    public CompanyRequestCollectiveTenderModel() {
    }

    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public int getResponse_takataf_company_id() {
        return response_takataf_company_id;
    }

    public void setResponse_takataf_company_id(int response_takataf_company_id) {
        this.response_takataf_company_id = response_takataf_company_id;
    }

    public int getResponse_takataf_request_id() {
        return response_takataf_request_id;
    }

    public void setResponse_takataf_request_id(int response_takataf_request_id) {
        this.response_takataf_request_id = response_takataf_request_id;
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
}
