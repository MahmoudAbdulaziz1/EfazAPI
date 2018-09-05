package com.taj.model;

/**
 * Created by User on 8/5/2018.
 */
public class getCustomeOffer {

    private String status;
    private  CustomCompanyModelWithView model;

    public getCustomeOffer(String status, CustomCompanyModelWithView model) {
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

    public CustomCompanyModelWithView getModel() {
        return model;
    }

    public void setModel(CustomCompanyModelWithView model) {
        this.model = model;
    }
}
