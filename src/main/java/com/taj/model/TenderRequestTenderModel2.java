package com.taj.model;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 9/26/2018.
 */
public class TenderRequestTenderModel2 {


    Map<TenderRequestSchoolModel, List<TenderRequestCategoriesModel>> res;
    private long tender_id;
    private String tender_title;
    private String tender_explain;
    private long tender_display_date;
    private long tender_expire_date;
    private long tender_company_display_date;
    private long tender_company_expired_date;
    private long response_count;
    private List<CategoryNameDto> category;
    private List<TenderRequestSchoolModel> schools;


    public TenderRequestTenderModel2(long tender_id, String tender_title, String tender_explain,
                                     long tender_display_date, long tender_expire_date, long tender_company_display_date, long tender_company_expired_date, long response_count, List<CategoryNameDto> category, List<TenderRequestSchoolModel> schools) {
        this.tender_id = tender_id;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.response_count = response_count;
//        this.res = res;
        this.schools = schools;
        this.category = category;
    }

    public TenderRequestTenderModel2() {
    }

    public long getTender_id() {
        return tender_id;
    }

    public void setTender_id(long tender_id) {
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

    public long getResponse_count() {
        return response_count;
    }

    public void setResponse_count(long response_count) {
        this.response_count = response_count;
    }

    public Map<TenderRequestSchoolModel, List<TenderRequestCategoriesModel>> getRes() {
        return res;
    }

    public void setRes(Map<TenderRequestSchoolModel, List<TenderRequestCategoriesModel>> res) {
        this.res = res;
    }

    public List<TenderRequestSchoolModel> getSchools() {
        return schools;
    }

    public void setSchools(List<TenderRequestSchoolModel> schools) {
        this.schools = schools;
    }

    public List<CategoryNameDto> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryNameDto> category) {
        this.category = category;
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
