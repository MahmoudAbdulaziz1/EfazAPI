package com.taj.model.new_school_history;

/**
 * Created by User on 10/1/2018.
 */
public class SchoolRequestHistoryDtoDTO2 {

    private int request_id;
    private String request_title;
    private int request_count;
    private long request_date;
    private long response_date;
    private double responsed_cost;
    private int response_id;
    private String response_desc;
    private int company_id;
    private String company_name;
    private byte[] company_logo_image;
    private String company_desc;

    public SchoolRequestHistoryDtoDTO2(int request_id, String request_title, int request_count, long request_date, long response_date,
                                       double responsed_cost, int response_id, String response_desc, int company_id, String company_name, byte[] company_logo_image, String company_desc) {
        this.request_id = request_id;
        this.request_title = request_title;
        this.request_count = request_count;
        this.request_date = request_date;
        this.response_date = response_date;
        this.responsed_cost = responsed_cost;
        this.response_id = response_id;
        this.response_desc = response_desc;
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_logo_image = company_logo_image;
        this.company_desc = company_desc;
    }

    public SchoolRequestHistoryDtoDTO2() {
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getRequest_title() {
        return request_title;
    }

    public void setRequest_title(String request_title) {
        this.request_title = request_title;
    }

    public int getRequest_count() {
        return request_count;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
    }

    public long getRequest_date() {
        return request_date;
    }

    public void setRequest_date(long request_date) {
        this.request_date = request_date;
    }

    public long getResponse_date() {
        return response_date;
    }

    public void setResponse_date(long response_date) {
        this.response_date = response_date;
    }

    public double getResponsed_cost() {
        return responsed_cost;
    }

    public void setResponsed_cost(double responsed_cost) {
        this.responsed_cost = responsed_cost;
    }

    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public String getResponse_desc() {
        return response_desc;
    }

    public void setResponse_desc(String response_desc) {
        this.response_desc = response_desc;
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

    public String getCompany_desc() {
        return company_desc;
    }

    public void setCompany_desc(String company_desc) {
        this.company_desc = company_desc;
    }
}
