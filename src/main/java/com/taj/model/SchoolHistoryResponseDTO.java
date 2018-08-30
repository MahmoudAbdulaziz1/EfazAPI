package com.taj.model;

/**
 * Created by User on 8/30/2018.
 */
public class SchoolHistoryResponseDTO {

    private double responsed_cost;
    private int responsed_company_id;
    private int response_id;

    public SchoolHistoryResponseDTO(double responsed_cost, int responsed_company_id, int response_id) {
        this.responsed_cost = responsed_cost;
        this.responsed_company_id = responsed_company_id;
        this.response_id = response_id;
    }

    public SchoolHistoryResponseDTO() {
    }

    public double getResponsed_cost() {
        return responsed_cost;
    }

    public void setResponsed_cost(double responsed_cost) {
        this.responsed_cost = responsed_cost;
    }

    public int getResponsed_company_id() {
        return responsed_company_id;
    }

    public void setResponsed_company_id(int responsed_company_id) {
        this.responsed_company_id = responsed_company_id;
    }

    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }
}
