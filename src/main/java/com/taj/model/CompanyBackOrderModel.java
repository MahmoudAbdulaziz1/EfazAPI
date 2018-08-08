package com.taj.model;

/**
 * Created by User on 8/6/2018.
 */
public class CompanyBackOrderModel {

    private int company_id;
    private int request_offer_count;
    private String offer_title;
    private double offer_cost;


    public CompanyBackOrderModel(int company_id, int request_offer_count, String offer_title, double offer_cost) {
        this.company_id = company_id;
        this.request_offer_count = request_offer_count;
        this.offer_title = offer_title;
        this.offer_cost = offer_cost;
    }

    public CompanyBackOrderModel() {
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getRequest_offer_count() {
        return request_offer_count;
    }

    public void setRequest_offer_count(int request_offer_count) {
        this.request_offer_count = request_offer_count;
    }

    public String getOffer_title() {
        return offer_title;
    }

    public void setOffer_title(String offer_title) {
        this.offer_title = offer_title;
    }

    public double getOffer_cost() {
        return offer_cost;
    }

    public void setOffer_cost(double offer_cost) {
        this.offer_cost = offer_cost;
    }
}

