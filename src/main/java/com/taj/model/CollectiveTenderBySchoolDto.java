package com.taj.model;

/**
 * Created by User on 9/6/2018.
 */
public class CollectiveTenderBySchoolDto {

    private int tender_id;
    private byte[]tender_logo;
    private String tender_title;
    private String tender_explain;
    private long tender_display_date;
    private long tender_expire_date;
    private long tender_company_display_date;
    private long tender_company_expired_date;
    private int response_count;
    private int view_count;
    private String category_name;

    public CollectiveTenderBySchoolDto(int tender_id, byte[] tender_logo, String tender_title, String tender_explain, long tender_display_date,
                                       long tender_expire_date, long tender_company_display_date, long tender_company_expired_date,
                                       int response_count, int view_count, String category_name) {
        this.tender_id = tender_id;
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.response_count = response_count;
        this.view_count = view_count;
        this.category_name = category_name;
    }

    public CollectiveTenderBySchoolDto(byte[] tender_logo, String tender_title, String tender_explain, long tender_display_date,
                                       long tender_expire_date, long tender_company_display_date, long tender_company_expired_date,
                                       int response_count, int view_count, String category_name) {
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.response_count = response_count;
        this.view_count = view_count;
        this.category_name = category_name;
    }

    public CollectiveTenderBySchoolDto() {
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

    public int getResponse_count() {
        return response_count;
    }

    public void setResponse_count(int response_count) {
        this.response_count = response_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
