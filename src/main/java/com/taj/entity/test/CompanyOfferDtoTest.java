package com.taj.entity.test;

import javax.persistence.Column;

/**
 * Created by User on 10/10/2018.
 */
public class CompanyOfferDtoTest {

    private int offer_id;
    private byte[] offer_image_id;
    private String offer_title;
    private String offer_explaination;
    private double offer_cost;
    private long offer_display_date;
    private long offer_expired_date;
    private long offer_deliver_date;
    private int offer_company_id;
    private int offer_count;
    private long date;

    public CompanyOfferDtoTest(int offer_id, byte[] offer_image_id, String offer_title, String offer_explaination,
                               double offer_cost, long offer_display_date, long offer_expired_date, long offer_deliver_date, int offer_company_id, int offer_count, long date) {
        this.offer_id = offer_id;
        this.offer_image_id = offer_image_id;
        this.offer_title = offer_title;
        this.offer_explaination = offer_explaination;
        this.offer_cost = offer_cost;
        this.offer_display_date = offer_display_date;
        this.offer_expired_date = offer_expired_date;
        this.offer_deliver_date = offer_deliver_date;
        this.offer_company_id = offer_company_id;
        this.offer_count = offer_count;
        this.date = date;
    }

    public CompanyOfferDtoTest() {
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public byte[] getOffer_image_id() {
        return offer_image_id;
    }

    public void setOffer_image_id(byte[] offer_image_id) {
        this.offer_image_id = offer_image_id;
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

    public double getOffer_cost() {
        return offer_cost;
    }

    public void setOffer_cost(double offer_cost) {
        this.offer_cost = offer_cost;
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

    public long getOffer_deliver_date() {
        return offer_deliver_date;
    }

    public void setOffer_deliver_date(long offer_deliver_date) {
        this.offer_deliver_date = offer_deliver_date;
    }

    public int getOffer_company_id() {
        return offer_company_id;
    }

    public void setOffer_company_id(int offer_company_id) {
        this.offer_company_id = offer_company_id;
    }

    public int getOffer_count() {
        return offer_count;
    }

    public void setOffer_count(int offer_count) {
        this.offer_count = offer_count;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
