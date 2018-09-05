package com.taj.model;

/**
 * Created by User on 9/4/2018.
 */
public class AdminHistoryOrdersModel {

    private int offer_id;
    private int request_offer_count;
    private int school_id;
    private byte[] school_logo_image;
    private int company_id;
    private double offer_cost;
    private  byte[] company_logo_image;
    private long offer_display_date;
    private long offer_deliver_date;

    public AdminHistoryOrdersModel(int offer_id, int request_offer_count, int school_id, byte[] school_logo_image,
                                   int company_id, double offer_cost, byte[] company_logo_image, long offer_display_date, long offer_deliver_date) {
        this.offer_id = offer_id;
        this.request_offer_count = request_offer_count;
        this.school_id = school_id;
        this.school_logo_image = school_logo_image;
        this.company_id = company_id;
        this.offer_cost = offer_cost;
        this.company_logo_image = company_logo_image;
        this.offer_display_date = offer_display_date;
        this.offer_deliver_date = offer_deliver_date;
    }

    public AdminHistoryOrdersModel(int request_offer_count, int school_id, byte[] school_logo_image, int company_id,
                                   double offer_cost, byte[] company_logo_image, long offer_display_date, long offer_deliver_date) {
        this.request_offer_count = request_offer_count;
        this.school_id = school_id;
        this.school_logo_image = school_logo_image;
        this.company_id = company_id;
        this.offer_cost = offer_cost;
        this.company_logo_image = company_logo_image;
        this.offer_display_date = offer_display_date;
        this.offer_deliver_date = offer_deliver_date;
    }

    public AdminHistoryOrdersModel() {
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public int getRequest_offer_count() {
        return request_offer_count;
    }

    public void setRequest_offer_count(int request_offer_count) {
        this.request_offer_count = request_offer_count;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public byte[] getSchool_logo_image() {
        return school_logo_image;
    }

    public void setSchool_logo_image(byte[] school_logo_image) {
        this.school_logo_image = school_logo_image;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public double getOffer_cost() {
        return offer_cost;
    }

    public void setOffer_cost(double offer_cost) {
        this.offer_cost = offer_cost;
    }

    public byte[] getCompany_logo_image() {
        return company_logo_image;
    }

    public void setCompany_logo_image(byte[] company_logo_image) {
        this.company_logo_image = company_logo_image;
    }

    public long getOffer_display_date() {
        return offer_display_date;
    }

    public void setOffer_display_date(long offer_display_date) {
        this.offer_display_date = offer_display_date;
    }

    public long getOffer_deliver_date() {
        return offer_deliver_date;
    }

    public void setOffer_deliver_date(long offer_deliver_date) {
        this.offer_deliver_date = offer_deliver_date;
    }
}
