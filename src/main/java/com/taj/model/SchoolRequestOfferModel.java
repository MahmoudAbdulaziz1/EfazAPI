package com.taj.model;

import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;

/**
 * Created by User on 7/8/2018.
 */
public class SchoolRequestOfferModel {


    private int request_id;
    @NonNull
    @Min(1)
    private int requsted_school_id;
    @NonNull
    @Min(1)
    private int requsted_offer_id;

    private int is_accepted;
    @Min(1)
    private int request_offer_count;

    public SchoolRequestOfferModel() {
    }

    public SchoolRequestOfferModel(int request_id, int requsted_school_id, int requsted_offer_id, int is_accepted, int request_offer_count) {
        this.request_id = request_id;
        this.requsted_school_id = requsted_school_id;
        this.requsted_offer_id = requsted_offer_id;
        this.is_accepted = is_accepted;
        this.request_offer_count = request_offer_count;
    }
    public SchoolRequestOfferModel(int requsted_school_id, int requsted_offer_id, int is_accepted, int request_offer_count) {
        this.requsted_school_id = requsted_school_id;
        this.requsted_offer_id = requsted_offer_id;
        this.is_accepted = is_accepted;
        this.request_offer_count = request_offer_count;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getRequsted_school_id() {
        return requsted_school_id;
    }

    public void setRequsted_school_id(int requsted_school_id) {
        this.requsted_school_id = requsted_school_id;
    }

    public int getRequsted_offer_id() {
        return requsted_offer_id;
    }

    public void setRequsted_offer_id(int requsted_offer_id) {
        this.requsted_offer_id = requsted_offer_id;
    }

    public int getIs_accepted() {
        return is_accepted;
    }

    public void setIs_accepted(int is_accepted) {
        this.is_accepted = is_accepted;
    }

    public int getRequest_offer_count() {
        return request_offer_count;
    }

    public void setRequest_offer_count(int request_offer_count) {
        this.request_offer_count = request_offer_count;
    }
}
