package com.taj.model;

/**
 * Created by User on 7/30/2018.
 */
public class getOfferImage {

    private String status;
    private  AddOfferImage model;

    public getOfferImage(String status, AddOfferImage model) {
        this.status = status;
        this.model = model;
    }

    public getOfferImage() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddOfferImage getModel() {
        return model;
    }

    public void setModel(AddOfferImage model) {
        this.model = model;
    }
}
