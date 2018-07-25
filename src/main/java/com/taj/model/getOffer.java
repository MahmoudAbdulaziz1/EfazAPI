package com.taj.model;

/**
 * Created by User on 7/25/2018.
 */
public class getOffer {

    private String status;
    private  CompanyOfferModel model;

    public getOffer(String status, CompanyOfferModel model) {
        this.status = status;
        this.model = model;
    }

    public getOffer() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CompanyOfferModel getModel() {
        return model;
    }

    public void setModel(CompanyOfferModel model) {
        this.model = model;
    }
}
