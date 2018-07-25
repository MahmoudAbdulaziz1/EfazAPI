package com.taj.model;

import java.util.List;

/**
 * Created by User on 7/25/2018.
 */
public class getFollowersList {

    private String status;
    private List<SchoolFollowCompany> followers;

    public getFollowersList(String status, List<SchoolFollowCompany> model) {
        this.status = status;
        this.followers = model;
    }

    public getFollowersList() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SchoolFollowCompany> getModel() {
        return followers;
    }

    public void setModel(List<SchoolFollowCompany> model) {
        this.followers = model;
    }
}
