package com.taj.model.offer_description;

import com.taj.model.CustomeCompanyOfferModel2;

import java.util.List;

/**
 * Created by User on 9/27/2018.
 */
public class getCustomeCompanyOffer2 {

    private String status;
    private List<CustomeCompanyOfferModel2DTo> offers;

    public getCustomeCompanyOffer2(String status, List<CustomeCompanyOfferModel2DTo> list) {
        this.status = status;
        this.offers = list;
    }

    public getCustomeCompanyOffer2() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomeCompanyOfferModel2DTo> getList() {
        return offers;
    }

    public void setList(List<CustomeCompanyOfferModel2DTo> list) {
        this.offers = list;
    }
}
