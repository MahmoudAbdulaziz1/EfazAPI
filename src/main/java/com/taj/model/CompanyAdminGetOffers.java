package com.taj.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * Created by User on 7/30/2018.
 */
public class CompanyAdminGetOffers {

    private int offer_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "title should have at least 1 characters")
    private String offer_title;

    @Min(1)
    private double offer_cost;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private @NotNull Timestamp offer_display_date;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp offer_expired_date;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp offer_deliver_date;
    @Min(1)
    private int company_id;
    @Min(1)
    private int offer_count;

    public CompanyAdminGetOffers() {
    }

    public CompanyAdminGetOffers(int offer_id, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String offer_title, @Min(1) double offer_cost,
                                 @NotNull Timestamp offer_display_date, @NotNull Timestamp offer_expired_date, @NotNull Timestamp offer_deliver_date, @Min(1) int company_id, @Min(1) int offer_count) {
        this.offer_id = offer_id;
        this.offer_title = offer_title;
        this.offer_cost = offer_cost;
        this.offer_display_date = offer_display_date;
        this.offer_expired_date = offer_expired_date;
        this.offer_deliver_date = offer_deliver_date;
        this.company_id = company_id;
        this.offer_count = offer_count;
    }

    public CompanyAdminGetOffers(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String offer_title, @Min(1) double offer_cost,
                                 @NotNull Timestamp offer_display_date, @NotNull Timestamp offer_expired_date, @NotNull Timestamp offer_deliver_date, @Min(1) int company_id, @Min(1) int offer_count) {
        this.offer_title = offer_title;
        this.offer_cost = offer_cost;
        this.offer_display_date = offer_display_date;
        this.offer_expired_date = offer_expired_date;
        this.offer_deliver_date = offer_deliver_date;
        this.company_id = company_id;
        this.offer_count = offer_count;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public String getOffer_title() {
        return offer_title;
    }

    public void setOffer_title(String offer_title) {
        this.offer_title = offer_title;
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

    public int getOffer_count() {
        return offer_count;
    }

    public void setOffer_count(int offer_count) {
        this.offer_count = offer_count;
    }
}
