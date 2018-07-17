package com.taj.model;

import javax.validation.constraints.*;

/**
 * Created by User on 7/3/2018.
 */
public class RequestEnquiriesModel {


    @Min(1)
    private int enquiry_id;
    @Min(1)
    private int school_request_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="enquiry_message should have at least 1 characters")
    private String enquiry_message;

    public RequestEnquiriesModel(int enquiry_id, int school_request_id, String enquiry_message) {
        this.enquiry_id = enquiry_id;
        this.school_request_id = school_request_id;
        this.enquiry_message = enquiry_message;
    }

    public RequestEnquiriesModel() {
    }

    public RequestEnquiriesModel(int school_request_id, String enquiry_message) {
        this.school_request_id = school_request_id;
        this.enquiry_message = enquiry_message;
    }

    public int getEnquiry_id() {
        return enquiry_id;
    }

    public void setEnquiry_id(int enquiry_id) {
        this.enquiry_id = enquiry_id;
    }

    public int getSchool_request_id() {
        return school_request_id;
    }

    public void setSchool_request_id(int school_request_id) {
        this.school_request_id = school_request_id;
    }

    public String getEnquiry_message() {
        return enquiry_message;
    }

    public void setEnquiry_message(String enquiry_message) {
        this.enquiry_message = enquiry_message;
    }
}
