package com.taj.model;

import javax.validation.constraints.Min;

/**
 * Created by User on 7/8/2018.
 */
public class TakatafSchoolSeeTenderModel {



    private int seen_id;
    @Min(1)
    private int  seen_tender_id;
    @Min(1)
    private int seen_school_id;

    public TakatafSchoolSeeTenderModel() {
    }

    public TakatafSchoolSeeTenderModel(int seen_id, int seen_tender_id, int seen_school_id) {
        this.seen_id = seen_id;
        this.seen_tender_id = seen_tender_id;
        this.seen_school_id = seen_school_id;
    }

    public int getSeen_id() {
        return seen_id;
    }

    public void setSeen_id(int seen_id) {
        this.seen_id = seen_id;
    }

    public int getSeen_tender_id() {
        return seen_tender_id;
    }

    public void setSeen_tender_id(int seen_tender_id) {
        this.seen_tender_id = seen_tender_id;
    }

    public int getSeen_school_id() {
        return seen_school_id;
    }

    public void setSeen_school_id(int seen_school_id) {
        this.seen_school_id = seen_school_id;
    }
}
