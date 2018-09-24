package com.taj.model;

/**
 * Created by User on 9/24/2018.
 */
public class SchoolRequestOfferDateModel {

    private int id;
    private long date;

    public SchoolRequestOfferDateModel(int id, long date) {
        this.id = id;
        this.date = date;
    }

    public SchoolRequestOfferDateModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
