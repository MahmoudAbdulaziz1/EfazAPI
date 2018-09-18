package com.taj.model;

import javax.validation.constraints.*;

/**
 * Created by User on 9/18/2018.
 */
public class NewCustomSchoolProfileModel {


    @Min(1)
    private int school_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="name should have at least 1 characters")
    private String school_name;
    @NotNull
    private byte[] school_logo_image;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="address should have at least 1 characters")
    private String school_address;
    private String school_service_desc;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="service should have at least 1 characters")
    private String school_link_youtube;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="website should have at least 1 characters")
    private String school_website_url;
    @NotNull
    private byte[] school_cover_image;
    private String school_phone_number;
    private String area;
    private String city;

    public NewCustomSchoolProfileModel() {
    }

    public NewCustomSchoolProfileModel(@Min(1) int school_id, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "name should have at least 1 characters") String school_name, @NotNull byte[] school_logo_image, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address should have at least 1 characters") String school_address, String school_service_desc, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "service should have at least 1 characters") String school_link_youtube, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "website should have at least 1 characters")
    String school_website_url, @NotNull byte[] school_cover_image, String school_phone_number, String area, String city) {
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.school_address = school_address;
        this.school_service_desc = school_service_desc;
        this.school_link_youtube = school_link_youtube;
        this.school_website_url = school_website_url;
        this.school_cover_image = school_cover_image;
        this.school_phone_number = school_phone_number;
        this.area = area;
        this.city = city;
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

    public byte[] getSchool_logo_image() {
        return school_logo_image;
    }

    public void setSchool_logo_image(byte[] school_logo_image) {
        this.school_logo_image = school_logo_image;
    }

    public String getSchool_address() {
        return school_address;
    }

    public void setSchool_address(String school_address) {
        this.school_address = school_address;
    }

    public String getSchool_service_desc() {
        return school_service_desc;
    }

    public void setSchool_service_desc(String school_service_desc) {
        this.school_service_desc = school_service_desc;
    }

    public String getSchool_link_youtube() {
        return school_link_youtube;
    }

    public void setSchool_link_youtube(String school_link_youtube) {
        this.school_link_youtube = school_link_youtube;
    }

    public String getSchool_website_url() {
        return school_website_url;
    }

    public void setSchool_website_url(String school_website_url) {
        this.school_website_url = school_website_url;
    }

    public byte[] getSchool_cover_image() {
        return school_cover_image;
    }

    public void setSchool_cover_image(byte[] school_cover_image) {
        this.school_cover_image = school_cover_image;
    }

    public String getSchool_phone_number() {
        return school_phone_number;
    }

    public void setSchool_phone_number(String school_phone_number) {
        this.school_phone_number = school_phone_number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
