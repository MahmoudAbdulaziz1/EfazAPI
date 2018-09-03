package com.taj.model;

/**
 * Created by User on 9/3/2018.
 */
public class AdminSingleOrderModel {

    private int offer_id;
    private byte[] offer_image;
    private String offer_title;
    private String offer_explaination;
    private long offer_display_date;
    private long offer_expired_date;
    private int request_offer_count;
    private int school_id;
    private byte[] school_logo_image;
    private int company_id;
    private double offer_cost;
    private byte[] company_logo_image;

    public AdminSingleOrderModel(int offer_id, byte[] offer_image, String offer_title, String offer_explaination, long offer_display_date,
                                 long offer_expired_date, int request_offer_count, int school_id, byte[] school_logo_image, int company_id,
                                 double offer_cost, byte[] company_logo_image) {
        this.offer_id = offer_id;
        this.offer_image = offer_image;
        this.offer_title = offer_title;
        this.offer_explaination = offer_explaination;
        this.offer_display_date = offer_display_date;
        this.offer_expired_date = offer_expired_date;
        this.request_offer_count = request_offer_count;
        this.school_id = school_id;
        this.school_logo_image = school_logo_image;
        this.company_id = company_id;
        this.offer_cost = offer_cost;
        this.company_logo_image = company_logo_image;
    }

    public AdminSingleOrderModel(byte[] offer_image, String offer_title, String offer_explaination, long offer_display_date,
                                 long offer_expired_date, int request_offer_count, int school_id, byte[] school_logo_image, int company_id,
                                 double offer_cost, byte[] company_logo_image) {
        this.offer_image = offer_image;
        this.offer_title = offer_title;
        this.offer_explaination = offer_explaination;
        this.offer_display_date = offer_display_date;
        this.offer_expired_date = offer_expired_date;
        this.request_offer_count = request_offer_count;
        this.school_id = school_id;
        this.school_logo_image = school_logo_image;
        this.company_id = company_id;
        this.offer_cost = offer_cost;
        this.company_logo_image = company_logo_image;
    }

    public AdminSingleOrderModel() {
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public byte[] getOffer_image() {
        return offer_image;
    }

    public void setOffer_image(byte[] offer_image) {
        this.offer_image = offer_image;
    }

    public String getOffer_title() {
        return offer_title;
    }

    public void setOffer_title(String offer_title) {
        this.offer_title = offer_title;
    }

    public String getOffer_explaination() {
        return offer_explaination;
    }

    public void setOffer_explaination(String offer_explaination) {
        this.offer_explaination = offer_explaination;
    }

    public long getOffer_display_date() {
        return offer_display_date;
    }

    public void setOffer_display_date(long offer_display_date) {
        this.offer_display_date = offer_display_date;
    }

    public long getOffer_expired_date() {
        return offer_expired_date;
    }

    public void setOffer_expired_date(long offer_expired_date) {
        this.offer_expired_date = offer_expired_date;
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
}
