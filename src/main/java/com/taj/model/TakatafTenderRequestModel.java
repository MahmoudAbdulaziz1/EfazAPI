package com.taj.model;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
public class TakatafTenderRequestModel {


    private int request_id;
    @Min(1)
    private int request_school_id;
    @Min(1)
    private int request_tender_id;
    @Min(0)
    private int is_aproved;
    private long date;
    private List<Takataf_schoolApplayCollectiveTender> category;

    public TakatafTenderRequestModel() {
    }

    public TakatafTenderRequestModel(int request_id, int request_school_id, int request_tender_id, int is_aproved,
                                     long date, List<Takataf_schoolApplayCollectiveTender> category) {

        this.request_id = request_id;
        this.request_school_id = request_school_id;
        this.request_tender_id = request_tender_id;
        this.is_aproved = is_aproved;
        this.date = date;
        this.category = category;
    }

    public TakatafTenderRequestModel( int request_school_id, int request_tender_id, int is_aproved, long date, List<Takataf_schoolApplayCollectiveTender> category) {

        this.request_school_id = request_school_id;
        this.request_tender_id = request_tender_id;
        this.is_aproved = is_aproved;
        this.date = date;
        this.category = category;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getRequest_school_id() {
        return request_school_id;
    }

    public void setRequest_school_id(int request_school_id) {
        this.request_school_id = request_school_id;
    }

    public int getRequest_tender_id() {
        return request_tender_id;
    }

    public void setRequest_tender_id(int request_tender_id) {
        this.request_tender_id = request_tender_id;
    }

    public int getIs_aproved() {
        return is_aproved;
    }

    public void setIs_aproved(int is_aproved) {
        this.is_aproved = is_aproved;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<Takataf_schoolApplayCollectiveTender> getCategory() {
        return category;
    }

    public void setCategory(List<Takataf_schoolApplayCollectiveTender> category) {
        this.category = category;
    }
}
