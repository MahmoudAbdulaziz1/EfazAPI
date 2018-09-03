package com.taj.model;

/**
 * Created by User on 9/3/2018.
 */
public class AdminApprovalDTO {
    private int company_id;
    private int request_id;
    private long offer_deliver_date;

    public AdminApprovalDTO(int company_id, int request_id, long offer_deliver_date) {
        this.company_id = company_id;
        this.request_id = request_id;
        this.offer_deliver_date = offer_deliver_date;
    }

    public AdminApprovalDTO() {
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public long getOffer_deliver_date() {
        return offer_deliver_date;
    }

    public void setOffer_deliver_date(long offer_deliver_date) {
        this.offer_deliver_date = offer_deliver_date;
    }
}

