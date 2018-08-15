package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 8/14/2018.
 */
public class GetCompaniesWithFollowANDOffers {


    private int company_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "company_name should have at least 1 characters")
    private String company_name;
    @NotNull
    private byte[] company_logo_image;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "address should have at least 1 characters")
    private String company_address;
    @NotNull
    private String company_category_id;
    //    @NotNull
//    @NotBlank
//    @NotEmpty
//    @Size(max = 450, min = 1, message="youtube should have at least 1 characters")
    private String company_link_youtube;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "website should have at least 1 characters")
    private String company_website_url;

    private float company_lng, company_lat;
    @NotNull
    private byte[] company_cover_image;
    private String company_phone_number;
    private boolean is_follow;
    private int follower_count;
    private int offer_count;

    public GetCompaniesWithFollowANDOffers(int company_id, @NotNull @NotBlank
    @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String company_name, @NotNull byte[] company_logo_image,
                                     @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address should have at least 1 characters") String company_address, @NotNull String company_category_id, String company_link_youtube, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "website should have at least 1 characters") String company_website_url,
                                     float company_lng, float company_lat, @NotNull byte[] company_cover_image, String company_phone_number,
                                           boolean is_follow, int follower_count, int offer_count) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_logo_image = company_logo_image;
        this.company_address = company_address;
        this.company_category_id = company_category_id;
        this.company_link_youtube = company_link_youtube;
        this.company_website_url = company_website_url;
        this.company_lng = company_lng;
        this.company_lat = company_lat;
        this.company_cover_image = company_cover_image;
        this.company_phone_number = company_phone_number;
        this.is_follow = is_follow;
        this.follower_count = follower_count;
        this.offer_count = offer_count;
    }

    public GetCompaniesWithFollowANDOffers(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String company_name,
                                     @NotNull byte[] company_logo_image, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address should have at least 1 characters") String company_address,
                                     @NotNull String company_category_id, String company_link_youtube, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "website should have at least 1 characters") String company_website_url,
                                     float company_lng, float company_lat, @NotNull byte[] company_cover_image, String company_phone_number,
                                           boolean is_follow, int follower_count, int offer_count) {
        this.company_name = company_name;
        this.company_logo_image = company_logo_image;
        this.company_address = company_address;
        this.company_category_id = company_category_id;
        this.company_link_youtube = company_link_youtube;
        this.company_website_url = company_website_url;
        this.company_lng = company_lng;
        this.company_lat = company_lat;
        this.company_cover_image = company_cover_image;
        this.company_phone_number = company_phone_number;
        this.is_follow = is_follow;
        this.follower_count = follower_count;
        this.offer_count = offer_count;
    }

    public GetCompaniesWithFollowANDOffers() {
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public byte[] getCompany_logo_image() {
        return company_logo_image;
    }

    public void setCompany_logo_image(byte[] company_logo_image) {
        this.company_logo_image = company_logo_image;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_category_id() {
        return company_category_id;
    }

    public void setCompany_category_id(String company_category_id) {
        this.company_category_id = company_category_id;
    }

    public String getCompany_link_youtube() {
        return company_link_youtube;
    }

    public void setCompany_link_youtube(String company_link_youtube) {
        this.company_link_youtube = company_link_youtube;
    }

    public String getCompany_website_url() {
        return company_website_url;
    }

    public void setCompany_website_url(String company_website_url) {
        this.company_website_url = company_website_url;
    }

    public float getCompany_lng() {
        return company_lng;
    }

    public void setCompany_lng(float company_lng) {
        this.company_lng = company_lng;
    }

    public float getCompany_lat() {
        return company_lat;
    }

    public void setCompany_lat(float company_lat) {
        this.company_lat = company_lat;
    }

    public byte[] getCompany_cover_image() {
        return company_cover_image;
    }

    public void setCompany_cover_image(byte[] company_cover_image) {
        this.company_cover_image = company_cover_image;
    }

    public String getCompany_phone_number() {
        return company_phone_number;
    }

    public void setCompany_phone_number(String company_phone_number) {
        this.company_phone_number = company_phone_number;
    }

    public boolean is_follow() {
        return is_follow;
    }

    public void setIs_follow(boolean is_follow) {
        this.is_follow = is_follow;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }

    public int getOffer_count() {
        return offer_count;
    }

    public void setOffer_count(int offer_count) {
        this.offer_count = offer_count;
    }


}
