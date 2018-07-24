package com.taj.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * Created by MahmoudAhmed on 6/4/2018.
 */
public class CompanyOfferModel {


    private int offer_id;
    @NotNull
    private byte[] offer_logo;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="title should have at least 1 characters")
    private String offer_title;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="explian should have at least 1 characters")
    private String offer_explaination;
    @Min(1)
    private double offer_cost;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp offer_display_date;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp offer_expired_date;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp offer_deliver_date;
    @Min(1)
    private int company_id;

    public CompanyOfferModel() {
    }

    public CompanyOfferModel(int offer_id, byte[] offer_logo, String offer_title, String offer_explaination, double offer_cost,
                             Timestamp offer_display_date, Timestamp offer_expired_date, Timestamp offer_deliver_date, int company_id) {
        this.offer_id = offer_id;
        this.offer_logo = offer_logo;
        this.offer_title = offer_title;
        this.offer_explaination = offer_explaination;
        this.offer_cost = offer_cost;
        this.offer_display_date = offer_display_date;
        this.offer_expired_date = offer_expired_date;
        this.offer_deliver_date = offer_deliver_date;
        this.company_id = company_id;

    }

    public CompanyOfferModel(byte[] offer_logo, String offer_title, String offer_explaination, double offer_cost,
                             Timestamp offer_display_date, Timestamp offer_expired_date, Timestamp offer_deliver_date, int company_id) {
        this.offer_logo = offer_logo;
        this.offer_title = offer_title;
        this.offer_explaination = offer_explaination;
        this.offer_cost = offer_cost;
        this.offer_display_date = offer_display_date;
        this.offer_expired_date = offer_expired_date;
        this.offer_deliver_date = offer_deliver_date;
        this.company_id = company_id;

    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public byte[] getOffer_logo() {
        return offer_logo;
    }

    public void setOffer_logo(byte[] offer_logo) {
        this.offer_logo = offer_logo;
    }

    public String getOffer_title() {
        return offer_title;
    }

    public void setOffer_title(String offer_title) {
        this.offer_title = offer_title;
    }

    public String getOffer_explaination() {
        return offer_explaination;
    }

    public void setOffer_explaination(String offer_explaination) {
        this.offer_explaination = offer_explaination;
    }

    public double getOffer_cost() {
        return offer_cost;
    }

    public void setOffer_cost(double offer_cost) {
        this.offer_cost = offer_cost;
    }

    public Timestamp getOffer_display_date() {
        return offer_display_date;
    }

    public void setOffer_display_date(Timestamp offer_display_date) {
        this.offer_display_date = offer_display_date;
    }

    public Timestamp getOffer_expired_date() {
        return offer_expired_date;
    }

    public void setOffer_expired_date(Timestamp offer_expired_date) {
        this.offer_expired_date = offer_expired_date;
    }

    public Timestamp getOffer_deliver_date() {
        return offer_deliver_date;
    }

    public void setOffer_deliver_date(Timestamp offer_deliver_date) {
        this.offer_deliver_date = offer_deliver_date;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}