package com.taj.model;

/**
 * Created by User on 8/27/2018.
 */
public class Takatf_tenderAddCategoryModel {

    private int id, t_tender_id, t_category_id;

    public Takatf_tenderAddCategoryModel(int id, int t_tender_id, int t_category_id) {
        this.id = id;
        this.t_tender_id = t_tender_id;
        this.t_category_id = t_category_id;
    }

    public Takatf_tenderAddCategoryModel(int t_tender_id, int t_category_id) {
        this.t_tender_id = t_tender_id;
        this.t_category_id = t_category_id;
    }

    public Takatf_tenderAddCategoryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getT_tender_id() {
        return t_tender_id;
    }

    public void setT_tender_id(int t_tender_id) {
        this.t_tender_id = t_tender_id;
    }

    public int getT_category_id() {
        return t_category_id;
    }

    public void setT_category_id(int t_category_id) {
        this.t_category_id = t_category_id;
    }
}
