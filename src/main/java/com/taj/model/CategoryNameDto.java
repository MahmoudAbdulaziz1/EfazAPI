package com.taj.model;

/**
 * Created by User on 9/19/2018.
 */
public class CategoryNameDto {

    private String cat_name;

    public CategoryNameDto(String cat_name) {
        this.cat_name = cat_name;
    }

    public CategoryNameDto() {
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
}
