package com.taj.model;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * Created by User on 8/19/2018.
 */
public class TakatafTenderNewModel {

    private int tender_id;
    private byte[] tender_logo;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="title should have at least 1 characters")
    private String tender_title;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="explain should have at least 1 characters")
    private String tender_explain;
    @NotNull
    private String tender_cat_id;
    private @NotNull long tender_display_date;
    @NotNull
    private long tender_expire_date;
    private int response_count;

    public TakatafTenderNewModel(int tender_id, byte[] tender_logo,
                                 @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title,
                                 @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain,
                                 String tender_cat_id, @NotNull long tender_display_date, @NotNull long tender_expire_date, int response_count) {
        this.tender_id = tender_id;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_cat_id = tender_cat_id;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_logo = tender_logo;
        this.response_count = response_count;
    }


    public TakatafTenderNewModel(byte[] tender_logo,@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title,
                                 @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain,
                                 String tender_cat_id, @NotNull long tender_display_date, @NotNull long tender_expire_date, int response_count) {
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_cat_id = tender_cat_id;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.response_count = response_count;
    }

    public TakatafTenderNewModel() {
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

    public String getTender_cat_id() {
        return tender_cat_id;
    }

    public void setTender_cat_id(String tender_cat_id) {
        this.tender_cat_id = tender_cat_id;
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

    public byte[] getTender_logo() {
        return tender_logo;
    }

    public void setTender_logo(byte[] tender_logo) {
        this.tender_logo = tender_logo;
    }

    public int getResponse_count() {
        return response_count;
    }

    public void setResponse_count(int response_count) {
        this.response_count = response_count;
    }
}
