package com.taj.model.new_company_history;

/**
 * Created by User on 10/1/2018.
 */
public class CompanyHistoryDto2 {

    private int offer_id;
    private byte[] image_one;
    private byte[] image_two;
    private byte[] image_three;
    private byte[] image_four;
    private String offer_title;
    private String offer_explaination;
    private double offer_cost;
    private long offer_display_date;
    private long date;
    private int offer_count;
    private String city;
    private String area;
    private float lng;
    private float lat;
    private int request_id;
    private int request_offer_count;
    private int request_count;
    private int school_id;
    private String school_name;
    private byte[] school_logo_image;

    public CompanyHistoryDto2(int offer_id, byte[] image_one, byte[] image_two, byte[] image_three, byte[] image_four, String offer_title, String offer_explaination, double offer_cost, long offer_display_date, long date, int offer_count, String city,
                              String area, float lng, float lat, int request_id, int request_offer_count, int request_count, int school_id, String school_name, byte[] school_logo_image) {
        this.offer_id = offer_id;
        this.image_one = image_one;
        this.image_two = image_two;
        this.image_three = image_three;
        this.image_four = image_four;
        this.offer_title = offer_title;
        this.offer_explaination = offer_explaination;
        this.offer_cost = offer_cost;
        this.offer_display_date = offer_display_date;
        this.date = date;
        this.offer_count = offer_count;
        this.city = city;
        this.area = area;
        this.lng = lng;
        this.lat = lat;
        this.request_id = request_id;
        this.request_offer_count = request_offer_count;
        this.request_count = request_count;
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
    }

    public CompanyHistoryDto2() {
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public byte[] getImage_one() {
        return image_one;
    }

    public void setImage_one(byte[] image_one) {
        this.image_one = image_one;
    }

    public byte[] getImage_two() {
        return image_two;
    }

    public void setImage_two(byte[] image_two) {
        this.image_two = image_two;
    }

    public byte[] getImage_three() {
        return image_three;
    }

    public void setImage_three(byte[] image_three) {
        this.image_three = image_three;
    }

    public byte[] getImage_four() {
        return image_four;
    }

    public void setImage_four(byte[] image_four) {
        this.image_four = image_four;
    }

    public String getOffer_title() {
        return offer_title;
    }

    public void setOffer_title(String offer_title) {
        this.offer_title = offer_title;
    }

    public String getOffer_explaination() {
        return offer_explaination;
    }

    public void setOffer_explaination(String offer_explaination) {
        this.offer_explaination = offer_explaination;
    }

    public double getOffer_cost() {
        return offer_cost;
    }

    public void setOffer_cost(double offer_cost) {
        this.offer_cost = offer_cost;
    }

    public long getOffer_display_date() {
        return offer_display_date;
    }

    public void setOffer_display_date(long offer_display_date) {
        this.offer_display_date = offer_display_date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getOffer_count() {
        return offer_count;
    }

    public void setOffer_count(int offer_count) {
        this.offer_count = offer_count;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getRequest_offer_count() {
        return request_offer_count;
    }

    public void setRequest_offer_count(int request_offer_count) {
        this.request_offer_count = request_offer_count;
    }

    public int getRequest_count() {
        return request_count;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
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
}
