package com.taj.model;

import java.util.List;

/**
 * Created by User on 7/30/2018.
 */
public class getOfferImages {

    private String status;
    private  List<AddOfferImage> model;

    public getOfferImages(String status, List<AddOfferImage> model) {
        this.status = status;
        this.model = model;
    }

    public getOfferImages() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AddOfferImage> getModel() {
        return model;
    }

    public void setModel(List<AddOfferImage> model) {
        this.model = model;
    }
}
