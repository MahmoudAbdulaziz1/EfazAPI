package com.taj.model;

/**
 * Created by User on 9/3/2018.
 */
public class CollectiveTenderCompaniesRequestForCompanyModel {

    private int response_id;
    private double responsed_cost;
    private int company_id;
    private String company_name;
    private String company_desc;
    private byte[] company_logo_image;
    private long response_date;


    public CollectiveTenderCompaniesRequestForCompanyModel(int response_id, double responsed_cost, int company_id,
                                                           String company_name, String company_desc, byte[] company_logo_image, long response_date) {
        this.response_id = response_id;
        this.responsed_cost = responsed_cost;
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_desc = company_desc;
        this.company_logo_image = company_logo_image;
        this.response_date = response_date;
    }

    public CollectiveTenderCompaniesRequestForCompanyModel(double responsed_cost, int company_id, String company_name,
                                                           String company_desc, byte[] company_logo_image, long response_date) {
        this.responsed_cost = responsed_cost;
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_desc = company_desc;
        this.company_logo_image = company_logo_image;
        this.response_date = response_date;
    }

    public CollectiveTenderCompaniesRequestForCompanyModel() {
    }

    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public double getResponsed_cost() {
        return responsed_cost;
    }

    public void setResponsed_cost(double responsed_cost) {
        this.responsed_cost = responsed_cost;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_desc() {
        return company_desc;
    }

    public void setCompany_desc(String company_desc) {
        this.company_desc = company_desc;
    }

    public byte[] getCompany_logo_image() {
        return company_logo_image;
    }

    public void setCompany_logo_image(byte[] company_logo_image) {
        this.company_logo_image = company_logo_image;
    }

    public long getResponse_date() {
        return response_date;
    }

    public void setResponse_date(long response_date) {
        this.response_date = response_date;
    }
}
