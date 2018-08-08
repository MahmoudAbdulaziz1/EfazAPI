package com.taj.model;

/**
 * Created by User on 8/8/2018.
 */
public class CompanyFollowSch0oolDto {

    private String company_name;
    private byte[] company_logo_image;

    public CompanyFollowSch0oolDto(String company_name, byte[] company_logo_image) {
        this.company_name = company_name;
        this.company_logo_image = company_logo_image;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public byte[] getCompany_logo_image() {
        return company_logo_image;
    }

    public void setCompany_logo_image(byte[] company_logo_image) {
        this.company_logo_image = company_logo_image;
    }
}
