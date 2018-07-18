package com.taj.model;

import javax.validation.constraints.Min;

/**
 * Created by User on 7/5/2018.
 */
public class TakatafThirdPriceModel {

    @Min(1)
    private int t_id;
    @Min(1)
    private int t_from;
    @Min(1)
    private int t_to  ;
    @Min(0)
    private double t_price;

    public TakatafThirdPriceModel() {
    }

    public TakatafThirdPriceModel(int t_id, int t_from, int t_to, double t_price) {
        this.t_id = t_id;
        this.t_from = t_from;
        this.t_to = t_to;
        this.t_price = t_price;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getT_from() {
        return t_from;
    }

    public void setT_from(int t_from) {
        this.t_from = t_from;
    }

    public int getT_to() {
        return t_to;
    }

    public void setT_to(int t_to) {
        this.t_to = t_to;
    }

    public double getT_price() {
        return t_price;
    }

    public void setT_price(double t_price) {
        this.t_price = t_price;
    }
}
