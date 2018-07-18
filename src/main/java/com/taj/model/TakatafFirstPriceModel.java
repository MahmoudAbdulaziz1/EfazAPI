package com.taj.model;

import javax.validation.constraints.Min;

/**
 * Created by User on 7/5/2018.
 */
public class TakatafFirstPriceModel {

    @Min(1)
    private int f_id;
    @Min(1)
    private int f_from;
    @Min(1)
    private int f_to;
    @Min(1)
    private double f_price;

    public TakatafFirstPriceModel() {
    }

    public TakatafFirstPriceModel(int f_id, int f_from, int f_to, double f_price) {
        this.f_id = f_id;
        this.f_from = f_from;
        this.f_to = f_to;
        this.f_price = f_price;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getF_from() {
        return f_from;
    }

    public void setF_from(int f_from) {
        this.f_from = f_from;
    }

    public int getF_to() {
        return f_to;
    }

    public void setF_to(int f_to) {
        this.f_to = f_to;
    }

    public double getF_price() {
        return f_price;
    }

    public void setF_price(double f_price) {
        this.f_price = f_price;
    }
}
