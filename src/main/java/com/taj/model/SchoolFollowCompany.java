package com.taj.model;

import javax.validation.constraints.Min;

/**
 * Created by User on 7/19/2018.
 */
public class SchoolFollowCompany {


    private int follow_id;
    @Min(1)
    private int organization_id;
    @Min(1)
    private int follower_id;

    public SchoolFollowCompany() {
    }

    public SchoolFollowCompany(int follow_id, int organization_id, int follower_id) {
        this.follow_id = follow_id;
        this.organization_id = organization_id;
        this.follower_id = follower_id;
    }

    public SchoolFollowCompany(int organization_id, int follower_id) {
        this.follow_id = follow_id;
        this.organization_id = organization_id;
        this.follower_id = follower_id;
    }

    public int getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(int follow_id) {
        this.follow_id = follow_id;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public int getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(int follower_id) {
        this.follower_id = follower_id;
    }
}
