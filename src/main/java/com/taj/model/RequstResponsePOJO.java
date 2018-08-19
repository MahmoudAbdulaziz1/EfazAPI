package com.taj.model;

/**
 * Created by User on 8/19/2018.
 */
public class RequstResponsePOJO {


    private int status;
    private SchoolRequestNewDtoWitCompany schoolRequestNewDtoWitCompany;

    public RequstResponsePOJO(int status, SchoolRequestNewDtoWitCompany schoolRequestNewDtoWitCompany) {
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

    public SchoolRequestNewDtoWitCompany getSchoolRequestNewDtoWitCompany() {
        return schoolRequestNewDtoWitCompany;
    }

    public void setSchoolRequestNewDtoWitCompany(SchoolRequestNewDtoWitCompany schoolRequestNewDtoWitCompany) {
        this.schoolRequestNewDtoWitCompany = schoolRequestNewDtoWitCompany;
    }
}
