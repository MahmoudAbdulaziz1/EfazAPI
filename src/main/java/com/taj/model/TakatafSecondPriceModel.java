package com.taj.model;

import javax.validation.constraints.Min;

/**
 * Created by User on 7/5/2018.
 */
public class TakatafSecondPriceModel {


    @Min(1)
    private int s_id;
    @Min(1)
    private int s_from;
    @Min(1)
    private int s_to;
    @Min(1)
    private double s_price;

    public TakatafSecondPriceModel() {
    }

    public TakatafSecondPriceModel(int s_id, int s_from, int s_to, double s_price) {
        this.s_id = s_id;
        this.s_from = s_from;
        this.s_to = s_to;
        this.s_price = s_price;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getS_from() {
        return s_from;
    }

    public void setS_from(int s_from) {
        this.s_from = s_from;
    }

    public int getS_to() {
        return s_to;
    }

    public void setS_to(int s_to) {
        this.s_to = s_to;
    }

    public double getS_price() {
        return s_price;
    }

    public void setS_price(double s_price) {
        this.s_price = s_price;
    }
}
