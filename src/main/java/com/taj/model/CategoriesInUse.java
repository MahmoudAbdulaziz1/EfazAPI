package com.taj.model;

/**
 * Created by User on 9/17/2018.
 */
public class CategoriesInUse {

    private String category_name;

    public CategoriesInUse(String category_name) {
        this.category_name = category_name;
    }

    public CategoriesInUse() {
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
