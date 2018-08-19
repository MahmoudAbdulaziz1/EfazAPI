package com.taj.model;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * Created by User on 7/5/2018.
 */
public class TakatafTenderModel {



    private int tender_id;
    @NotNull
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
    private Timestamp tender_display_date;
    @NotNull
    private Timestamp tender_expire_date;
    @NotNull
    private Timestamp tender_deliver_date;
    @Min(1)
    private int tender_company_id;
    @Min(0)
    private int tender_is_confirmed;
    @Min(0)
    private int tender_is_available;
    @Min(1)
    private int tender_f_id;
    @Min(1)
    private int tender_s_id;
    @Min(1)
    private int tender_t_id;
    @Min(1)
    private int tender_cat_id;

    public TakatafTenderModel() {
    }

    public TakatafTenderModel(byte[] tender_logo, String tender_title, String tender_explain, Timestamp tender_display_date, Timestamp tender_expire_date,
                              Timestamp tender_deliver_date, int tender_company_id, int tender_is_confirmed, int tender_is_available, int tender_f_id,
                              int tender_s_id, int tender_t_id, int tender_cat_id) {
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_deliver_date = tender_deliver_date;
        this.tender_company_id = tender_company_id;
        this.tender_is_confirmed = tender_is_confirmed;
        this.tender_is_available = tender_is_available;
        this.tender_f_id = tender_f_id;
        this.tender_s_id = tender_s_id;
        this.tender_t_id = tender_t_id;
        this.tender_cat_id = tender_cat_id;
    }

    public TakatafTenderModel(int tender_id, byte[] tender_logo, String tender_title, String tender_explain, Timestamp tender_display_date, Timestamp tender_expire_date,
                              Timestamp tender_deliver_date, int tender_company_id, int tender_is_confirmed, int tender_is_available, int tender_f_id, int tender_s_id,
                              int tender_t_id, int tender_cat_id) {
        this.tender_id = tender_id;
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_deliver_date = tender_deliver_date;
        this.tender_company_id = tender_company_id;
        this.tender_is_confirmed = tender_is_confirmed;
        this.tender_is_available = tender_is_available;
        this.tender_f_id = tender_f_id;
        this.tender_s_id = tender_s_id;
        this.tender_t_id = tender_t_id;
        this.tender_cat_id = tender_cat_id;
    }

    public int getTender_id() {
        return tender_id;
    }

    public void setTender_id(int tender_id) {
        this.tender_id = tender_id;
    }

    public byte[] getTender_logo() {
        return tender_logo;
    }

    public void setTender_logo(byte[] tender_logo) {
        this.tender_logo = tender_logo;
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

    public Timestamp getTender_display_date() {
        return tender_display_date;
    }

    public void setTender_display_date(Timestamp tender_display_date) {
        this.tender_display_date = tender_display_date;
    }

    public Timestamp getTender_expire_date() {
        return tender_expire_date;
    }

    public void setTender_expire_date(Timestamp tender_expire_date) {
        this.tender_expire_date = tender_expire_date;
    }

    public Timestamp getTender_deliver_date() {
        return tender_deliver_date;
    }

    public void setTender_deliver_date(Timestamp tender_deliver_date) {
        this.tender_deliver_date = tender_deliver_date;
    }

    public int getTender_company_id() {
        return tender_company_id;
    }

    public void setTender_company_id(int tender_company_id) {
        this.tender_company_id = tender_company_id;
    }

    public int getTender_is_confirmed() {
        return tender_is_confirmed;
    }

    public void setTender_is_confirmed(int tender_is_confirmed) {
        this.tender_is_confirmed = tender_is_confirmed;
    }

    public int getTender_is_available() {
        return tender_is_available;
    }

    public void setTender_is_available(int tender_is_available) {
        this.tender_is_available = tender_is_available;
    }

    public int getTender_f_id() {
        return tender_f_id;
    }

    public void setTender_f_id(int tender_f_id) {
        this.tender_f_id = tender_f_id;
    }

    public int getTender_s_id() {
        return tender_s_id;
    }

    public void setTender_s_id(int tender_s_id) {
        this.tender_s_id = tender_s_id;
    }

    public int getTender_t_id() {
        return tender_t_id;
    }

    public void setTender_t_id(int tender_t_id) {
        this.tender_t_id = tender_t_id;
    }

    public int getTender_cat_id() {
        return tender_cat_id;
    }

    public void setTender_cat_id(int tender_cat_id) {
        this.tender_cat_id = tender_cat_id;
    }
}
