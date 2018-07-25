package com.taj.model;

import java.util.List;

/**
 * Created by User on 7/25/2018.
 */
public class getCompanyOffer {
    private String status;
    private List<CompanyOfferModel> offers;

    public getCompanyOffer(String status, List<CompanyOfferModel> list) {
        this.status = status;
        this.offers = list;
    }

    public getCompanyOffer() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CompanyOfferModel> getList() {
        return offers;
    }

    public void setList(List<CompanyOfferModel> list) {
        this.offers = list;
    }
}
