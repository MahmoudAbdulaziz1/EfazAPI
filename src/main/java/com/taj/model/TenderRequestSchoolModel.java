package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/30/2018.
 */
public class TenderRequestSchoolModel {

    private long school_id;
    private String school_name;
    private String school_logo_image;
    private List<TenderRequestCategoriesModel> categories;

    public TenderRequestSchoolModel(int school_id, String school_name, String school_logo_image, List<TenderRequestCategoriesModel> categories) {
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenderRequestSchoolModel that = (TenderRequestSchoolModel) o;

        if (school_id != that.school_id) return false;
        if (!school_name.equals(that.school_name)) return false;
        return school_logo_image.equals(that.school_logo_image);

    }

    @Override
    public int hashCode() {
        int result = (int) (school_id ^ (school_id >>> 32));
        result = 31 * result + school_name.hashCode();
        result = 31 * result + school_logo_image.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "school_id=" + school_id +
                ", school_name='" + school_name + '\'' +
                ", school_logo_image='" + school_logo_image + '\'' +
                '}';
    }
}
