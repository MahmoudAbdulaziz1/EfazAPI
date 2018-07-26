package com.taj.model;

/**
 * Created by User on 7/26/2018.
 */
public class getSchoolSeeRequestById {

    private String status;
    private SchoolSeeRequest school;

    public getSchoolSeeRequestById(String status, SchoolSeeRequest school) {
        this.status = status;
        this.school = school;
    }

    public getSchoolSeeRequestById() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SchoolSeeRequest getSchool() {
        return school;
    }

    public void setSchool(SchoolSeeRequest school) {
        this.school = school;
    }
}
