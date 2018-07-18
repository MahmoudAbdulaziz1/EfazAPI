package com.taj.model;

import javax.validation.constraints.*;

/**
 * Created by User on 7/5/2018.
 */
public class TakatafCategoryModel {
    @Min(1)
    private int cat_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="category_name should have at least 1 characters")
    private String cat_name;

    public TakatafCategoryModel() {
    }

    public TakatafCategoryModel(int cat_id, String cat_name) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
    }

    public TakatafCategoryModel(String cat_name) {
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
