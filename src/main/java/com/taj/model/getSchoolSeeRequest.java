package com.taj.model;

import java.util.List;

/**
 * Created by User on 7/26/2018.
 */
public class getSchoolSeeRequest {


    private String status;
    private List<SchoolSeeRequest> schools;

    public getSchoolSeeRequest(String status, List<SchoolSeeRequest> schools) {
        this.status = status;
        this.schools = schools;
    }

    public getSchoolSeeRequest() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SchoolSeeRequest> getSchools() {
        return schools;
    }

    public void setSchools(List<SchoolSeeRequest> schools) {
        this.schools = schools;
    }
}
