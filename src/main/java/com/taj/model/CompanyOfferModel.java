package com.taj.model;

import java.sql.Timestamp;

/**
 * Created by MahmoudAhmed on 6/4/2018.
 */
public class CompanyOfferModel {

    private int offer_id;
    private byte[] offer_logo;
    private String offer_title;
    private String offer_explaination;
    private double offer_cost;
    private Timestamp offer_display_date;
    private Timestamp offer_expired_date;
    private Timestamp offer_deliver_date;
    private int offer_is_good;
    private String offer_contact;
    private String offer_website;

    public CompanyOfferModel() {
    }

    public CompanyOfferModel(int offer_id, byte[] offer_logo, String offer_title, String offer_explaination, double offer_cost,
                             Timestamp offer_display_date, Timestamp offer_expired_date, Timestamp offer_deliver_date, int offer_is_good,
                             String offer_contact, String offer_website) {
        this.offer_id = offer_id;
        this.offer_logo = offer_logo;
        this.offer_title = offer_title;
        this.offer_explaination = offer_explaination;
        this.offer_cost = offer_cost;
        this.offer_display_date = offer_display_date;
        this.offer_expired_date = offer_expired_date;
        this.offer_deliver_date = offer_deliver_date;
        this.offer_is_good = offer_is_good;
        this.offer_contact = offer_contact;
        this.offer_website = offer_website;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public byte[] getOffer_logo() {
        return offer_logo;
    }

    public void setOffer_logo(byte[] offer_logo) {
        this.offer_logo = offer_logo;
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

    public Timestamp getOffer_display_date() {
        return offer_display_date;
    }

    public void setOffer_display_date(Timestamp offer_display_date) {
        this.offer_display_date = offer_display_date;
    }

    public Timestamp getOffer_expired_date() {
        return offer_expired_date;
    }

    public void setOffer_expired_date(Timestamp offer_expired_date) {
        this.offer_expired_date = offer_expired_date;
    }

    public Timestamp getOffer_deliver_date() {
        return offer_deliver_date;
    }

    public void setOffer_deliver_date(Timestamp offer_deliver_date) {
        this.offer_deliver_date = offer_deliver_date;
    }

    public int getOffer_is_good() {
        return offer_is_good;
    }

    public void setOffer_is_good(int offer_is_good) {
        this.offer_is_good = offer_is_good;
    }

    public String getOffer_contact() {
        return offer_contact;
    }

    public void setOffer_contact(String offer_contact) {
        this.offer_contact = offer_contact;
    }

    public String getOffer_website() {
        return offer_website;
    }

    public void setOffer_website(String offer_website) {
        this.offer_website = offer_website;
    }
}