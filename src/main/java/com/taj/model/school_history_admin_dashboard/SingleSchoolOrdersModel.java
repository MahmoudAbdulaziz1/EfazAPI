package com.taj.model.school_history_admin_dashboard;

/**
 * Created by User on 10/1/2018.
 */
public class SingleSchoolOrdersModel {

    private int request_id;
    private byte[] request_image;
    private String request_title;
    private String request_explaination;
    private long request_display_date;
    private long request_expired_date;
    private double responsed_cost;
    private long response_date;
    private String response_desc;
    private int school_id;
    private String school_name;
    private byte[] school_logo_image;
    private int company_id;
    private String company_name;
    private byte[] company_logo_image;
    private int request_count;
    private double ship;

    public SingleSchoolOrdersModel(int request_id, byte[] request_image, String request_title, String request_explaination, long request_display_date, long request_expired_date, double responsed_cost, long response_date, String response_desc, int school_id,
                                   String school_name, byte[] school_logo_image, int company_id, String company_name, byte[] company_logo_image, int request_count, double ship) {
        this.request_id = request_id;
        this.request_image = request_image;
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.responsed_cost = responsed_cost;
        this.response_date = response_date;
        this.response_desc = response_desc;
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_logo_image = company_logo_image;
        this.request_count = request_count;
        this.ship = ship;
    }

    public SingleSchoolOrdersModel() {
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public byte[] getRequest_image() {
        return request_image;
    }

    public void setRequest_image(byte[] request_image) {
        this.request_image = request_image;
    }

    public String getRequest_title() {
        return request_title;
    }

    public void setRequest_title(String request_title) {
        this.request_title = request_title;
    }

    public String getRequest_explaination() {
        return request_explaination;
    }

    public void setRequest_explaination(String request_explaination) {
        this.request_explaination = request_explaination;
    }

    public long getRequest_display_date() {
        return request_display_date;
    }

    public void setRequest_display_date(long request_display_date) {
        this.request_display_date = request_display_date;
    }

    public long getRequest_expired_date() {
        return request_expired_date;
    }

    public void setRequest_expired_date(long request_expired_date) {
        this.request_expired_date = request_expired_date;
    }

    public double getResponsed_cost() {
        return responsed_cost;
    }

    public void setResponsed_cost(double responsed_cost) {
        this.responsed_cost = responsed_cost;
    }

    public long getResponse_date() {
        return response_date;
    }

    public void setResponse_date(long response_date) {
        this.response_date = response_date;
    }

    public String getResponse_desc() {
        return response_desc;
    }

    public void setResponse_desc(String response_desc) {
        this.response_desc = response_desc;
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

    public int getRequest_count() {
        return request_count;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
    }

    public double getShip() {
        return ship;
    }

    public void setShip(double ship) {
        this.ship = ship;
    }
}
