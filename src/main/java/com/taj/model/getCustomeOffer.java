package com.taj.model;

/**
 * Created by User on 8/5/2018.
 */
public class getCustomeOffer {

    private String status;
    private  CustomCompanyOfferModel model;

    public getCustomeOffer(String status, CustomCompanyOfferModel model) {
        this.status = status;
        this.model = model;
    }

    public getCustomeOffer() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomCompanyOfferModel getModel() {
        return model;
    }

    public void setModel(CustomCompanyOfferModel model) {
        this.model = model;
    }
}
