package com.taj.model.school_history_admin_dashboard;

/**
 * Created by User on 10/1/2018.
 */
public class SchoolOrdersModel {


    private int request_id;
    private double responsed_cost;
    private long response_date;
    private String response_desc;
    private long request_display_date;
    private int school_id;
    private String school_name;
    private byte[] school_logo_image;
    private int company_id;
    private String company_name;
    private byte[] company_logo_image;
    private int request_count;

    public SchoolOrdersModel(int request_id, double responsed_cost, long response_date, String response_desc, long request_display_date,
                             int school_id, String school_name, byte[] school_logo_image, int company_id, String company_name, byte[] company_logo_image, int request_count) {
        this.request_id = request_id;
        this.responsed_cost = responsed_cost;
        this.response_date = response_date;
        this.response_desc = response_desc;
        this.request_display_date = request_display_date;
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_logo_image = company_logo_image;
        this.request_count = request_count;
    }

    public SchoolOrdersModel() {
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public double getResponsed_cost() {
        return responsed_cost;
    }

    public void setResponsed_cost(double responsed_cost) {
        this.responsed_cost = responsed_cost;
    }

    public long getResponse_date() {
        return response_date;
    }

    public void setResponse_date(long response_date) {
        this.response_date = response_date;
    }

    public String getResponse_desc() {
        return response_desc;
    }

    public void setResponse_desc(String response_desc) {
        this.response_desc = response_desc;
    }

    public long getRequest_display_date() {
        return request_display_date;
    }

    public void setRequest_display_date(long request_display_date) {
        this.request_display_date = request_display_date;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public byte[] getSchool_logo_image() {
        return school_logo_image;
    }

    public void setSchool_logo_image(byte[] school_logo_image) {
        this.school_logo_image = school_logo_image;
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

    public byte[] getCompany_logo_image() {
        return company_logo_image;
    }

    public void setCompany_logo_image(byte[] company_logo_image) {
        this.company_logo_image = company_logo_image;
    }

    public int getRequest_count() {
        return request_count;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
    }
}
