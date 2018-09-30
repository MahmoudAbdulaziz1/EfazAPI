package com.taj.model.offer_description;

import com.taj.model.CustomCompanyModelWithView;

/**
 * Created by User on 9/27/2018.
 */
public class getCustomeOffer2 {

    private String status;
    private CustomCompanyModelWithViewAndDescRes model;

    public getCustomeOffer2(String status, CustomCompanyModelWithViewAndDescRes model) {
        this.status = status;
        this.model = model;
    }

    public getCustomeOffer2() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomCompanyModelWithViewAndDescRes getModel() {
        return model;
    }

    public void setModel(CustomCompanyModelWithViewAndDescRes model) {
        this.model = model;
    }
}
