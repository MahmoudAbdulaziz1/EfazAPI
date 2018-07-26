package com.taj.model;

import javax.validation.constraints.Min;

/**
 * Created by User on 7/9/2018.
 */
public class SchoolSeeRequest {


    private int seen_id;
    @Min(1)
    private int seen_offer_id;
    @Min(1)
    private int seen_offer_school_id;

    public SchoolSeeRequest() {
    }

    public SchoolSeeRequest(int seen_id, int seen_offer_id, int seen_offer_school_id) {
        this.seen_id = seen_id;
        this.seen_offer_id = seen_offer_id;
        this.seen_offer_school_id = seen_offer_school_id;
    }

    public SchoolSeeRequest(int seen_offer_id, int seen_offer_school_id) {
        this.seen_offer_id = seen_offer_id;
        this.seen_offer_school_id = seen_offer_school_id;
    }

    public int getSeen_id() {
        return seen_id;
    }

    public void setSeen_id(int seen_id) {
        this.seen_id = seen_id;
    }

    public int getSeen_offer_id() {
        return seen_offer_id;
    }

    public void setSeen_offer_id(int seen_offer_id) {
        this.seen_offer_id = seen_offer_id;
    }

    public int getSeen_offer_school_id() {
        return seen_offer_school_id;
    }

    public void setSeen_offer_school_id(int seen_offer_school_id) {
        this.seen_offer_school_id = seen_offer_school_id;
    }
}
