package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/29/2018.
 */
public class GetSingleCollectiveByIdPartOneDTO {

    private int tender_id;
    private String tender_title;
    private String tender_explain;
    private long tender_display_date;
    private long tender_expire_date;
    private int response_count;


    public GetSingleCollectiveByIdPartOneDTO(int tender_id, String tender_title, String tender_explain,
                                             long tender_display_date, long tender_expire_date, int response_count) {
        this.tender_id = tender_id;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.response_count = response_count;
    }

    public GetSingleCollectiveByIdPartOneDTO() {
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

}
