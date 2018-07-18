package com.taj.model;

import javax.validation.constraints.*;

/**
 * Created by User on 7/3/2018.
 */
public class SchoolRequestCategoryModel {

    @Min(1)
    private int request_category_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="category_name should have at least 1 characters")
    private String request_category_name;

    public SchoolRequestCategoryModel() {
    }

    public SchoolRequestCategoryModel(int request_category_id, String request_category_name) {
        this.request_category_id = request_category_id;
        this.request_category_name = request_category_name;
    }

    public SchoolRequestCategoryModel(String request_category_name) {
        this.request_category_name = request_category_name;
    }

    public int getRequest_category_id() {
        return request_category_id;
    }

    public void setRequest_category_id(int request_category_id) {
        this.request_category_id = request_category_id;
    }

    public String getRequest_category_name() {
        return request_category_name;
    }

    public void setRequest_category_name(String request_category_name) {
        this.request_category_name = request_category_name;
    }
}
