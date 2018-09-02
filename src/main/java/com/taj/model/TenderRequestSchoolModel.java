package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/30/2018.
 */
public class TenderRequestSchoolModel {

    private long school_id;
    private String school_name;
    private String school_logo_image;
    private long t_date;
    private List<TenderRequestCategoriesModel> categories;

    public TenderRequestSchoolModel(int school_id, String school_name, String school_logo_image, long t_date , List<TenderRequestCategoriesModel> categories) {
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.t_date = t_date;
        this.categories = categories;
    }

    public TenderRequestSchoolModel() {
    }

    public long getSchool_id() {
        return school_id;
    }

    public void setSchool_id(long school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_logo_image() {
        return school_logo_image;
    }

    public void setSchool_logo_image(String school_logo_image) {
        this.school_logo_image = school_logo_image;
    }

    public List<TenderRequestCategoriesModel> getCategories() {
        return categories;
    }

    public void setCategories(List<TenderRequestCategoriesModel> categories) {
        this.categories = categories;
    }

    public long getT_date() {
        return t_date;
    }

    public void setT_date(long t_date) {
        this.t_date = t_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenderRequestSchoolModel that = (TenderRequestSchoolModel) o;

        if (school_id != that.school_id) return false;
        if (t_date != that.t_date) return false;
        if (school_name != null ? !school_name.equals(that.school_name) : that.school_name != null) return false;
        return !(school_logo_image != null ? !school_logo_image.equals(that.school_logo_image) : that.school_logo_image != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (school_id ^ (school_id >>> 32));
        result = 31 * result + (school_name != null ? school_name.hashCode() : 0);
        result = 31 * result + (school_logo_image != null ? school_logo_image.hashCode() : 0);
        result = 31 * result + (int) (t_date ^ (t_date >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TenderRequestSchoolModel{" +
                "school_id=" + school_id +
                ", school_name='" + school_name + '\'' +
                ", school_logo_image='" + school_logo_image + '\'' +
                ", t_date=" + t_date +
                '}';
    }
}
