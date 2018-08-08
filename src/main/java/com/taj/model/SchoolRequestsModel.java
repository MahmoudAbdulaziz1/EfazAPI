package com.taj.model;

import javax.validation.constraints.*;


/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
public class SchoolRequestsModel {


    private int request_id;
    private byte[] request_details_file;//attachment file
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "title should have at least 1 characters")
    private String request_title;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "explain should have at least 1 characters")
    private String request_explaination;
    @NotNull
    private long request_display_date;
    @NotNull
    private long request_expired_date;
    @NotNull
    private long request_deliver_date;
    @NotNull
    private long request_payment_date;//مده التسليم
    @NotNull
    @Min(0)
    private int request_is_available;
    @NotNull
    @Min(0)
    private int request_is_conformied;//التعميد
    @NotNull
    @Min(1)
    private int school_id;
    @NotNull
    @Min(1)
    private int request_category_id;
    @NotNull
    @Min(1)
    private int receive_palce_id;
    @NotNull
    @Min(0)
    private int extended_payment;   //مده الدفع
    @Min(1)
    private int images_id;
    @Min(1)
    private int request_count;


    public SchoolRequestsModel() {
    }

    public SchoolRequestsModel(int request_id, byte[] request_details_file, int images_id, String request_title, String request_explaination,
                               long request_display_date, long request_expired_date, long request_deliver_date,
                               int request_is_available, int request_is_conformied, long request_payment_date, int school_id,
                               int request_category_id, int receive_palce_id, int extended_payment, int request_count) {
        this.request_id = request_id;
        this.request_details_file = request_details_file;
        this.images_id = images_id;
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.request_deliver_date = request_deliver_date;
        this.request_payment_date = request_payment_date;
        this.request_is_available = request_is_available;
        this.request_is_conformied = request_is_conformied;
        this.school_id = school_id;
        this.request_category_id = request_category_id;
        this.receive_palce_id = receive_palce_id;
        this.extended_payment = extended_payment;
        this.request_count = request_count;
    }

    public SchoolRequestsModel(byte[] request_details_file, int images_id, String request_title, String request_explaination,
                               long request_display_date, long request_expired_date, long request_deliver_date,
                               int request_is_available, int request_is_conformied, long request_payment_date, int school_id,
                               int request_category_id, int receive_palce_id, int extended_payment, int request_count) {
        this.request_details_file = request_details_file;
        this.images_id = images_id;
        this.request_title = request_title;
        this.request_explaination = request_explaination;
        this.request_display_date = request_display_date;
        this.request_expired_date = request_expired_date;
        this.request_deliver_date = request_deliver_date;
        this.request_payment_date = request_payment_date;
        this.request_is_available = request_is_available;
        this.request_is_conformied = request_is_conformied;
        this.school_id = school_id;
        this.request_category_id = request_category_id;
        this.receive_palce_id = receive_palce_id;
        this.extended_payment = extended_payment;
        this.request_count = request_count;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public byte[] getRequest_details_file() {
        return request_details_file;
    }

    public void setRequest_details_file(byte[] request_details_file) {
        this.request_details_file = request_details_file;
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

    public long getRequest_deliver_date() {
        return request_deliver_date;
    }

    public void setRequest_deliver_date(long request_deliver_date) {
        this.request_deliver_date = request_deliver_date;
    }

    public long getRequest_payment_date() {
        return request_payment_date;
    }

    public void setRequest_payment_date(long request_payment_date) {
        this.request_payment_date = request_payment_date;
    }

    public int getRequest_is_available() {
        return request_is_available;
    }

    public void setRequest_is_available(int request_is_available) {
        this.request_is_available = request_is_available;
    }

    public int getRequest_is_conformied() {
        return request_is_conformied;
    }

    public void setRequest_is_conformied(int request_is_conformied) {
        this.request_is_conformied = request_is_conformied;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getRequest_category_id() {
        return request_category_id;
    }

    public void setRequest_category_id(int request_category_id) {
        this.request_category_id = request_category_id;
    }

    public int getReceive_palce_id() {
        return receive_palce_id;
    }

    public void setReceive_palce_id(int receive_palce_id) {
        this.receive_palce_id = receive_palce_id;
    }

    public int getExtended_payment() {
        return extended_payment;
    }

    public void setExtended_payment(int extended_payment) {
        this.extended_payment = extended_payment;
    }

    public int getImages_id() {
        return images_id;
    }

    public void setImages_id(int images_id) {
        this.images_id = images_id;
    }

    public int getRequest_count() {
        return request_count;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
    }
}
