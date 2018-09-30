package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/5/2018.
 */
public class getCustomeCompanyOffer {

    private String status;
    private List<CustomeCompanyOfferModel2> offers;

    public getCustomeCompanyOffer(String status, List<CustomeCompanyOfferModel2> list) {
        this.status = status;
        this.offers = list;
    }

    public getCustomeCompanyOffer() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomeCompanyOfferModel2> getList() {
        return offers;
    }

    public void setList(List<CustomeCompanyOfferModel2> list) {
        this.offers = list;
    }
}
