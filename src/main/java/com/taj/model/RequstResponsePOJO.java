package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/19/2018.
 */
public class RequstResponsePOJO {


    private int status;
    private List<SchoolRequestNewDtoWitCompany> schoolRequestNewDtoWitCompany;

    public RequstResponsePOJO(int status, List<SchoolRequestNewDtoWitCompany> schoolRequestNewDtoWitCompany) {
        this.status = status;
        this.schoolRequestNewDtoWitCompany = schoolRequestNewDtoWitCompany;
    }

    public RequstResponsePOJO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SchoolRequestNewDtoWitCompany> getSchoolRequestNewDtoWitCompany() {
        return schoolRequestNewDtoWitCompany;
    }

    public void setSchoolRequestNewDtoWitCompany(List<SchoolRequestNewDtoWitCompany> schoolRequestNewDtoWitCompany) {
        this.schoolRequestNewDtoWitCompany = schoolRequestNewDtoWitCompany;
    }
}
