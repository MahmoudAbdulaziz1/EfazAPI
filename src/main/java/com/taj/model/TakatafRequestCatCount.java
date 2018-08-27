package com.taj.model;

/**
 * Created by User on 8/27/2018.
 */
public class TakatafRequestCatCount {

    private int id, cat_id, scool_id, tend_id, count;

    public TakatafRequestCatCount(int id, int cat_id, int scool_id, int tend_id, int count) {
        this.id = id;
        this.cat_id = cat_id;
        this.scool_id = scool_id;
        this.tend_id = tend_id;
        this.count = count;
    }

    public TakatafRequestCatCount(int cat_id, int scool_id, int tend_id, int count) {
        this.cat_id = cat_id;
        this.scool_id = scool_id;
        this.tend_id = tend_id;
        this.count = count;
    }

    public TakatafRequestCatCount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getScool_id() {
        return scool_id;
    }

    public void setScool_id(int scool_id) {
        this.scool_id = scool_id;
    }

    public int getTend_id() {
        return tend_id;
    }

    public void setTend_id(int tend_id) {
        this.tend_id = tend_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
