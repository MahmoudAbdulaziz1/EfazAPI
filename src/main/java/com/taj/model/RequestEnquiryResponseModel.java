package com.taj.model;

/**
 * Created by User on 7/3/2018.
 */
public class RequestEnquiryResponseModel {


    private int response_id, request_enquiry_id;
    private String response_message;

    public RequestEnquiryResponseModel() {
    }

    public RequestEnquiryResponseModel(int response_id, int request_enquiry_id, String response_message) {
        this.response_id = response_id;
        this.request_enquiry_id = request_enquiry_id;
        this.response_message = response_message;
    }

    public RequestEnquiryResponseModel(int request_enquiry_id, String response_message) {
        this.request_enquiry_id = request_enquiry_id;
        this.response_message = response_message;
    }

    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public int getRequest_enquiry_id() {
        return request_enquiry_id;
    }

    public void setRequest_enquiry_id(int request_enquiry_id) {
        this.request_enquiry_id = request_enquiry_id;
    }

    public String getResponse_message() {
        return response_message;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }
}
