package com.taj.model;

import java.util.List;

/**
 * Created by User on 7/25/2018.
 */
public class getFollowedList {

    private String status;
    private List<SchoolProfileModel> followed;

    public getFollowedList(String status, List<SchoolProfileModel> model) {
        this.status = status;
        this.followed = model;
    }

    public getFollowedList() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SchoolProfileModel> getModel() {
        return followed;
    }

    public void setModel(List<SchoolProfileModel> model) {
        this.followed = model;
    }
}
