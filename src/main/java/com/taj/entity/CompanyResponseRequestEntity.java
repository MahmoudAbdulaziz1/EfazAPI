package com.taj.entity;

import javax.persistence.*;

/**
 * Created by User on 7/9/2018.
 */
@Entity(name = "efaz_company_response_school_request")
@Table
public class CompanyResponseRequestEntity {

    @Id
    @GeneratedValue
    private int response_id;
    @Column
    private int responsed_company_id;
    @Column
    private int responsed_request_id;
    @Column
    private int responsed_from;
    @Column
    private int responsed_to;
    @Column
    private int is_aproved;
    @Column
    private double responsed_cost;

    public CompanyResponseRequestEntity() {
    }

    public CompanyResponseRequestEntity(int response_id, int responsed_company_id, int responsed_request_id, int responsed_from, int responsed_to, double responsed_cost, int is_aproved) {
        this.response_id = response_id;
        this.responsed_company_id = responsed_company_id;
        this.responsed_request_id = responsed_request_id;
        this.responsed_from = responsed_from;
        this.responsed_to = responsed_to;
        this.responsed_cost = responsed_cost;
        this.is_aproved = is_aproved;
    }

    public int getResponse_id() {
        return response_id;
    }

    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }

    public int getResponsed_company_id() {
        return responsed_company_id;
    }

    public void setResponsed_company_id(int responsed_company_id) {
        this.responsed_company_id = responsed_company_id;
    }

    public int getResponsed_request_id() {
        return responsed_request_id;
    }

    public void setResponsed_request_id(int responsed_request_id) {
        this.responsed_request_id = responsed_request_id;
    }

    public int getResponsed_from() {
        return responsed_from;
    }

    public void setResponsed_from(int responsed_from) {
        this.responsed_from = responsed_from;
    }

    public int getResponsed_to() {
        return responsed_to;
    }

    public void setResponsed_to(int responsed_to) {
        this.responsed_to = responsed_to;
    }

    public double getResponsed_cost() {
        return responsed_cost;
    }

    public void setResponsed_cost(double responsed_cost) {
        this.responsed_cost = responsed_cost;
    }

    public int getIs_aproved() {
        return is_aproved;
    }

    public void setIs_aproved(int is_aproved) {
        this.is_aproved = is_aproved;
    }

}
