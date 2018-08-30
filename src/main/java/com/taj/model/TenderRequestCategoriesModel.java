package com.taj.model;

/**
 * Created by User on 8/30/2018.
 */
public class TenderRequestCategoriesModel {


    private long id;
    private String category_name;
    private long count;

    public TenderRequestCategoriesModel(long id, String category_name, long count) {
        this.id = id;
        this.category_name = category_name;
        this.count = count;
    }

    public TenderRequestCategoriesModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
