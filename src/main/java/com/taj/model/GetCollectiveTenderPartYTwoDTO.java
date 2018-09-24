package com.taj.model;

/**
 * Created by User on 8/29/2018.
 */
public class GetCollectiveTenderPartYTwoDTO {

    private String request_category_name;
    private String company_name;
    private byte[] company_logo_image;
    private String category_name;
    private double responsed_cost;
    private long response_date;
    private int response_id;
    private int responsed_company_id;
    private int is_aproved;

    public GetCollectiveTenderPartYTwoDTO(String request_category_name, String company_name,
                                          byte[] company_logo_image, String category_name, double responsed_cost, long response_date
            , int response_id, int responsed_company_id, int is_aproved) {
        this.request_category_name = request_category_name;
        this.company_name = company_name;
        this.company_logo_image = company_logo_image;
        this.category_name = category_name;
        this.responsed_cost = responsed_cost;
        this.response_date = response_date;
        this.response_id = response_id;
        this.responsed_company_id = responsed_company_id;
        this.is_aproved = is_aproved;
    }

    public GetCollectiveTenderPartYTwoDTO() {
    }

    public String getRequest_category_name() {
        return request_category_name;
    }

    public void setRequest_category_name(String request_category_name) {
        this.request_category_name = request_category_name;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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

    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public int getResponsed_company_id() {
        return responsed_company_id;
    }

    public void setResponsed_company_id(int responsed_company_id) {
        this.responsed_company_id = responsed_company_id;
    }

    public int getIs_aproved() {
        return is_aproved;
    }

    public void setIs_aproved(int is_aproved) {
        this.is_aproved = is_aproved;
    }
}
