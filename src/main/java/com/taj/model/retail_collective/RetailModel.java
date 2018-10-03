package com.taj.model.retail_collective;

import javax.validation.constraints.NotNull;

/**
 * Created by User on 10/2/2018.
 */
public class RetailModel {

    private int id;
    private int retail;
    @NotNull
    private int retail_school_id;
    @NotNull
    private int retail_tender_id;

    public RetailModel(int id, int retail, int retail_school_id, int retail_tender_id) {
        this.id = id;
        this.retail = retail;
        this.retail_school_id = retail_school_id;
        this.retail_tender_id = retail_tender_id;
    }

    public RetailModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRetail() {
        return retail;
    }

    public void setRetail(int retail) {
        this.retail = retail;
    }

    public int getRetail_school_id() {
        return retail_school_id;
    }

    public void setRetail_school_id(int retail_school_id) {
        this.retail_school_id = retail_school_id;
    }

    public int getRetail_tender_id() {
        return retail_tender_id;
    }

    public void setRetail_tender_id(int retail_tender_id) {
        this.retail_tender_id = retail_tender_id;
    }
}
