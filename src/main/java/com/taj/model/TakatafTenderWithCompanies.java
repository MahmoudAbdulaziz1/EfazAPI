package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 8/19/2018.
 */
public class TakatafTenderWithCompanies {

    //from school request
    private int tender_id;
    private byte[] tender_logo;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="title should have at least 1 characters")
    private String tender_title;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="explain should have at least 1 characters")
    private String tender_explain;
    @NotNull
    private String tender_cat_id;
    private @NotNull long tender_display_date;
    @NotNull
    private long tender_expire_date;
    private int response_count;
    private @NotNull long tender_company_display_date;
    @NotNull
    private long tender_company_expired_date;


    //from company

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "company_name should have at least 1 characters")
    private String school_name;
    @NotNull
    private byte[] school_logo_image;
    @NotNull
    private String school_service_desc;

    public TakatafTenderWithCompanies(int tender_id, byte[] tender_logo, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title,
                                      @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain,
                                      @NotNull String tender_cat_id, @NotNull long tender_display_date, @NotNull long tender_expire_date, @NotNull long tender_company_display_date, @NotNull long tender_company_expired_date
                                      , int response_count, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String school_name,
                                      @NotNull byte[] school_logo_image, @NotNull String school_service_desc) {
        this.tender_id = tender_id;
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_cat_id = tender_cat_id;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.response_count = response_count;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.school_service_desc = school_service_desc;
    }

    public TakatafTenderWithCompanies(byte[] tender_logo, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title,
                                      @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain,
                                      @NotNull String tender_cat_id, @NotNull long tender_display_date, @NotNull long tender_expire_date,
                                      @NotNull long tender_company_display_date, @NotNull long tender_company_expired_date, int response_count,
                                      @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String school_name,
                                      @NotNull byte[] school_logo_image, @NotNull String school_service_desc) {
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_cat_id = tender_cat_id;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.response_count = response_count;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.school_service_desc = school_service_desc;
    }

    public TakatafTenderWithCompanies() {
    }

    public int getTender_id() {
        return tender_id;
    }

    public void setTender_id(int tender_id) {
        this.tender_id = tender_id;
    }

    public byte[] getTender_logo() {
        return tender_logo;
    }

    public void setTender_logo(byte[] tender_logo) {
        this.tender_logo = tender_logo;
    }

    public String getTender_title() {
        return tender_title;
    }

    public void setTender_title(String tender_title) {
        this.tender_title = tender_title;
    }

    public String getTender_explain() {
        return tender_explain;
    }

    public void setTender_explain(String tender_explain) {
        this.tender_explain = tender_explain;
    }

    public String getTender_cat_id() {
        return tender_cat_id;
    }

    public void setTender_cat_id(String tender_cat_id) {
        this.tender_cat_id = tender_cat_id;
    }

    public long getTender_display_date() {
        return tender_display_date;
    }

    public void setTender_display_date(long tender_display_date) {
        this.tender_display_date = tender_display_date;
    }

    public long getTender_expire_date() {
        return tender_expire_date;
    }

    public void setTender_expire_date(long tender_expire_date) {
        this.tender_expire_date = tender_expire_date;
    }

    public int getResponse_count() {
        return response_count;
    }

    public void setResponse_count(int response_count) {
        this.response_count = response_count;
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

    public String getSchool_service_desc() {
        return school_service_desc;
    }

    public void setSchool_service_desc(String school_service_desc) {
        this.school_service_desc = school_service_desc;
    }

    public long getTender_company_display_date() {
        return tender_company_display_date;
    }

    public void setTender_company_display_date(long tender_company_display_date) {
        this.tender_company_display_date = tender_company_display_date;
    }

    public long getTender_company_expired_date() {
        return tender_company_expired_date;
    }

    public void setTender_company_expired_date(long tender_company_expired_date) {
        this.tender_company_expired_date = tender_company_expired_date;
    }
}
