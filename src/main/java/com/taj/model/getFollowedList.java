package com.taj.model;

import java.util.List;

/**
 * Created by User on 7/25/2018.
 */
public class getFollowedList {

    private String status;
    private List<SchoolFollowCompany> followed;

    public getFollowedList(String status, List<SchoolFollowCompany> model) {
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

    public List<SchoolFollowCompany> getModel() {
        return followed;
    }

    public void setModel(List<SchoolFollowCompany> model) {
        this.followed = model;
    }
}
