package com.taj.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 8/27/2018.
 */
@NotNull
public class Takataf_schoolApplayCollectiveTender {
    @NotNull
    private String cat_name;

    private int count;

    public Takataf_schoolApplayCollectiveTender() {
    }

    public Takataf_schoolApplayCollectiveTender(@NotNull String cat_name, @NotNull @Min(1) int count) {
        this.cat_name = cat_name;
        this.count = count;
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
