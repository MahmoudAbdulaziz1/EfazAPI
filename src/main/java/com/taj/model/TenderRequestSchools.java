package com.taj.model;

import java.util.List;

/**
 * Created by User on 9/20/2018.
 */
public class TenderRequestSchools {

    private int school_id;
    private String school_name;
    private String school_logo_image;
    private List<TenderRequestCategories> categories;

    public TenderRequestSchools(int school_id, String school_name, String school_logo_image, List<TenderRequestCategories> categories) {
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.categories = categories;
    }

    public TenderRequestSchools() {
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
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

    public List<TenderRequestCategories> getCategories() {
        return categories;
    }

    public void setCategories(List<TenderRequestCategories> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenderRequestSchools that = (TenderRequestSchools) o;

        if (school_id != that.school_id) return false;
        if (school_name != null ? !school_name.equals(that.school_name) : that.school_name != null) return false;
        if (school_logo_image != null ? !school_logo_image.equals(that.school_logo_image) : that.school_logo_image != null)
            return false;
        return !(categories != null ? !categories.equals(that.categories) : that.categories != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (school_id ^ (school_id >>> 32));
        result = 31 * result + (school_name != null ? school_name.hashCode() : 0);
        result = 31 * result + (school_logo_image != null ? school_logo_image.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TenderRequestSchools{" +
                "school_id=" + school_id +
                ", school_name='" + school_name + '\'' +
                ", school_logo_image='" + school_logo_image + '\'' +
                ", categories=" + categories +
                '}';
    }
}
