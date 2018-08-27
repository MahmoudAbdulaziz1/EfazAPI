package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 8/15/2018.
 */
public class SchoolRequestNewDtoWitCompany {

    //from school request
    private int c_request_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "title should have at least 1 characters")
    private String c_request_title;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "explain should have at least 1 characters")
    private String c_request_explaination;
    @NotNull
    private long c_request_display_date;
    @NotNull
    private long c_request_expired_date;

    //private int c_response_count;

    //from company response
    @NotNull
    private long c_response_date;
    @NotNull
    private double c_responsed_cost;

    //from company

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "company_name should have at least 1 characters")
    private String c_company_name;
    @NotNull
    private byte[] c_company_logo_image;
    @NotNull
    private String c_company_category_id;



    public SchoolRequestNewDtoWitCompany(int request_id,
                                         @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String request_title,
                                         @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String request_explaination,
                                         @NotNull long request_display_date, @NotNull long request_expired_date, long response_date, @NotNull double responsed_cost,
                                         @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String company_name,
                                         @NotNull byte[] company_logo_image, @NotNull String company_category_id) {
        this.c_request_id = request_id;
        this.c_request_title = request_title;
        this.c_request_explaination = request_explaination;
        this.c_request_display_date = request_display_date;
        this.c_request_expired_date = request_expired_date;
        //this.c_response_count = response_count;
        this.c_response_date = response_date;
        this.c_responsed_cost = responsed_cost;
        this.c_company_name = company_name;
        this.c_company_logo_image = company_logo_image;
        this.c_company_category_id = company_category_id;
    }

    public SchoolRequestNewDtoWitCompany(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String request_title,
                                         @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String request_explaination,
                                         @NotNull long request_display_date, @NotNull long request_expired_date,  @NotNull long response_date, @NotNull double responsed_cost,
                                         @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String company_name,
                                         @NotNull byte[] company_logo_image, @NotNull String company_category_id) {
        this.c_request_title = request_title;
        this.c_request_explaination = request_explaination;
        this.c_request_display_date = request_display_date;
        this.c_request_expired_date = request_expired_date;
        //this.c_response_count = response_count;
        this.c_response_date = response_date;
        this.c_responsed_cost = responsed_cost;
        this.c_company_name = company_name;
        this.c_company_logo_image = company_logo_image;
        this.c_company_category_id = company_category_id;
    }

    public SchoolRequestNewDtoWitCompany() {
    }

    public int getRequest_id() {
        return c_request_id;
    }

    public void setRequest_id(int request_id) {
        this.c_request_id = request_id;
    }

    public String getRequest_title() {
        return c_request_title;
    }

    public void setRequest_title(String request_title) {
        this.c_request_title = request_title;
    }

    public String getRequest_explaination() {
        return c_request_explaination;
    }

    public void setRequest_explaination(String request_explaination) {
        this.c_request_explaination = request_explaination;
    }

    public long getRequest_display_date() {
        return c_request_display_date;
    }

    public void setRequest_display_date(long request_display_date) {
        this.c_request_display_date = request_display_date;
    }

    public long getRequest_expired_date() {
        return c_request_expired_date;
    }

    public void setRequest_expired_date(long request_expired_date) {
        this.c_request_expired_date = request_expired_date;
    }



    public long getResponse_date() {
        return c_response_date;
    }

    public void setResponse_date(long response_date) {
        this.c_response_date = response_date;
    }

    public double getResponsed_cost() {
        return c_responsed_cost;
    }

    public void setResponsed_cost(double responsed_cost) {
        this.c_responsed_cost = responsed_cost;
    }

    public String getCompany_name() {
        return c_company_name;
    }

    public void setCompany_name(String company_name) {
        this.c_company_name = company_name;
    }

    public byte[] getCompany_logo_image() {
        return c_company_logo_image;
    }

    public void setCompany_logo_image(byte[] company_logo_image) {
        this.c_company_logo_image = company_logo_image;
    }

    public String getCompany_category_id() {
        return c_company_category_id;
    }

    public void setCompany_category_id(String company_category_id) {
        this.c_company_category_id = company_category_id;
    }
}
