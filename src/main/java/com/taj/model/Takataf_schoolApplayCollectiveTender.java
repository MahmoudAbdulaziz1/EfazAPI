package com.taj.model;

/**
 * Created by User on 8/27/2018.
 */
public class Takataf_schoolApplayCollectiveTender {

    private String cat_name;
    private int count;

    public Takataf_schoolApplayCollectiveTender(String cat_name, int count) {
        this.cat_name = cat_name;
        this.count = count;
    }

    public Takataf_schoolApplayCollectiveTender() {
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
