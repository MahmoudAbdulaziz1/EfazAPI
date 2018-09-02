package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by User on 9/2/2018.
 */
public class TakatafTenderUpdatePOJO {

    private int tender_id;
    private byte[] tender_logo;
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
    private int tender_is_confirmed;
    private int tender_is_available;
    @NotNull
    private long tender_company_display_date;
    @NotNull
    private long tender_company_expired_date;
    private List<TakatfTenderCategoryPOJO> cats;

    public TakatafTenderUpdatePOJO(int tender_id, byte[] tender_logo,
                                   @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title,
                                   @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain,
                                   @NotNull long tender_display_date, @NotNull long tender_expire_date, int tender_is_confirmed, int tender_is_available,
                                   @NotNull long tender_company_display_date, @NotNull long tender_company_expired_date, List<TakatfTenderCategoryPOJO> cats) {
        this.tender_id = tender_id;
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_is_confirmed = tender_is_confirmed;
        this.tender_is_available = tender_is_available;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.cats = cats;
    }

    public TakatafTenderUpdatePOJO(byte[] tender_logo,
                                   @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "title should have at least 1 characters") String tender_title,
                                   @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "explain should have at least 1 characters") String tender_explain
            , @NotNull long tender_display_date, @NotNull long tender_expire_date, int tender_is_confirmed, int tender_is_available,
                                   @NotNull long tender_company_display_date, @NotNull long tender_company_expired_date, List<TakatfTenderCategoryPOJO> cats) {
        this.tender_logo = tender_logo;
        this.tender_title = tender_title;
        this.tender_explain = tender_explain;
        this.tender_display_date = tender_display_date;
        this.tender_expire_date = tender_expire_date;
        this.tender_is_confirmed = tender_is_confirmed;
        this.tender_is_available = tender_is_available;
        this.tender_company_display_date = tender_company_display_date;
        this.tender_company_expired_date = tender_company_expired_date;
        this.cats = cats;
    }

    public TakatafTenderUpdatePOJO() {
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

    public long getTender_company_display_date() {
        return tender_company_display_date;
    }

    public void setTender_company_display_date(long tender_company_display_date) {
        this.tender_company_display_date = tender_company_display_date;
    }

    public long getTender_company_expired_date() {
        return tender_company_expired_date;
    }

    public void setTender_company_expired_date(long tender_company_expired_date) {
        this.tender_company_expired_date = tender_company_expired_date;
    }

    public List<TakatfTenderCategoryPOJO> getCats() {
        return cats;
    }

    public void setCats(List<TakatfTenderCategoryPOJO> cats) {
        this.cats = cats;
    }
}
