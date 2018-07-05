package com.taj.model;

/**
 * Created by User on 7/5/2018.
 */
public class TakatfCategoryModel {
    private int cat_id;
    private String cat_name;

    public TakatfCategoryModel() {
    }

    public TakatfCategoryModel(int cat_id, String cat_name) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
    }

    public TakatfCategoryModel(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
}
