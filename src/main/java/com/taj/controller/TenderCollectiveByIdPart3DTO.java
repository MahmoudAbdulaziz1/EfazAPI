package com.taj.controller;

/**
 * Created by User on 8/29/2018.
 */
public class TenderCollectiveByIdPart3DTO {

    private int id;
    private String category_name;
    private int count;

    public TenderCollectiveByIdPart3DTO(int id, String category_name, int count) {
        this.id = id;
        this.category_name = category_name;
        this.count = count;
    }

    public TenderCollectiveByIdPart3DTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
