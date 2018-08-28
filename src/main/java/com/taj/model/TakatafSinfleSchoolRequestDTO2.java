package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Array;
import java.util.List;

/**
 * Created by User on 8/28/2018.
 */
public class TakatafSinfleSchoolRequestDTO2 {

    private int tender_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "title should have at least 1 characters")
    private String tender_title;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "explain should have at least 1 characters")
    private String tender_explain;

    private @NotNull long tender_display_date;
    @NotNull
    private long tender_expire_date;
    private int response_count;
    private Array id;
    private Array category_name;
    private Array count;
    private Array school_name;
    private Array school_logo_image;

    public TakatafSinfleSchoolRequestDTO2(int tender_id, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain, @NotNull long tender_display_date, @NotNull long tender_expire_date,
                                          int response_count, Array id, Array category_name, Array count, Array school_name, Array school_logo_image) {
        this.tender_id = tender_id;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.response_count = response_count;
        this.id = id;
        this.category_name = category_name;
        this.count = count;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
    }

    public TakatafSinfleSchoolRequestDTO2(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain, @NotNull long tender_display_date, @NotNull long tender_expire_date,
                                          int response_count, Array id, Array category_name, Array count, Array school_name, Array school_logo_image) {
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.response_count = response_count;
        this.id = id;
        this.category_name = category_name;
        this.count = count;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
    }

    public TakatafSinfleSchoolRequestDTO2() {
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

    public Array getId() {
        return id;
    }

    public void setId(Array id) {
        this.id = id;
    }

    public Array getCategory_name() {
        return category_name;
    }

    public void setCategory_name(Array category_name) {
        this.category_name = category_name;
    }

    public Array getCount() {
        return count;
    }

    public void setCount(Array count) {
        this.count = count;
    }

    public Array getSchool_name() {
        return school_name;
    }

    public void setSchool_name(Array school_name) {
        this.school_name = school_name;
    }

    public Array getSchool_logo_image() {
        return school_logo_image;
    }

    public void setSchool_logo_image(Array school_logo_image) {
        this.school_logo_image = school_logo_image;
    }
}
