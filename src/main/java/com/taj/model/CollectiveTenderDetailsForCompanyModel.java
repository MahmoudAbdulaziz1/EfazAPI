package com.taj.model;

/**
 * Created by User on 9/3/2018.
 */
public class CollectiveTenderDetailsForCompanyModel {

    private int tender_id;
    private byte[] tender_logo;
    private String tender_title;
    private String tender_explain;
    private long tender_company_display_date;
    private long tender_company_expired_date;
    private double response_count;
    private int cat_id;
    private String category_name;
    private int sum;

    public CollectiveTenderDetailsForCompanyModel(int tender_id, byte[] tender_logo, String tender_title, String tender_explain,
                                                  long tender_company_display_date, long tender_company_expired_date,
                                                  double response_count, int cat_id, String category_name, int sum) {
        this.tender_id = tender_id;
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.response_count = response_count;
        this.cat_id = cat_id;
        this.category_name = category_name;
        this.sum = sum;
    }

    public CollectiveTenderDetailsForCompanyModel(String tender_title, byte[] tender_logo, String tender_explain,
                                                  long tender_company_display_date, long tender_company_expired_date,
                                                  double response_count, int cat_id, String category_name, int sum) {
        this.tender_title = tender_title;
        this.tender_logo = tender_logo;
        this.tender_explain = tender_explain;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.response_count = response_count;
        this.cat_id = cat_id;
        this.category_name = category_name;
        this.sum = sum;
    }

    public CollectiveTenderDetailsForCompanyModel() {
    }

    public int getTender_id() {
        return tender_id;
    }

    public void setTender_id(int tender_id) {
        this.tender_id = tender_id;
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

    public double getResponse_count() {
        return response_count;
    }

    public void setResponse_count(double response_count) {
        this.response_count = response_count;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public byte[] getTender_logo() {
        return tender_logo;
    }

    public void setTender_logo(byte[] tender_logo) {
        this.tender_logo = tender_logo;
    }
}
