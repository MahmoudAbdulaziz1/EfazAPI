package com.taj.model;

import javax.validation.constraints.*;

/**
 * Created by User on 7/4/2018.
 */
public class SchoolReceivePlaceModel {

    @Min(1)
    private int place_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="name should have at least 1 characters")
    private String place_name;

    public SchoolReceivePlaceModel() {
    }

    public SchoolReceivePlaceModel(int place_id, String place_name) {
        this.place_id = place_id;
        this.place_name = place_name;
    }

    public SchoolReceivePlaceModel(String place_name) {
        this.place_name = place_name;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }
}
