package com.taj.model.company_offer_response_count;

import com.taj.model.offer_description.CustomeCompanyOfferModel2DTo;

import java.util.List;

/**
 * Created by User on 10/1/2018.
 */
public class getCustomeCompanyOffer2Model {

    private String status;
    private List<CustomeCompanyOfferModel2DToModel> offers;

    public getCustomeCompanyOffer2Model(String status, List<CustomeCompanyOfferModel2DToModel> offers) {
        this.status = status;
        this.offers = offers;
    }

    public getCustomeCompanyOffer2Model() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomeCompanyOfferModel2DToModel> getOffers() {
        return offers;
    }

    public void setOffers(List<CustomeCompanyOfferModel2DToModel> offers) {
        this.offers = offers;
    }
}
