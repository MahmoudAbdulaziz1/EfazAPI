package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/29/2018.
 */
public class GetCollectiveTenders {
    private GetCollectiveTenderPartOneDTO tender;
    private List<GetCollectiveTenderPartYTwoDTO> companies;

    public GetCollectiveTenders(GetCollectiveTenderPartOneDTO tender, List<GetCollectiveTenderPartYTwoDTO> companies) {
        this.tender = tender;
        this.companies = companies;
    }

    public GetCollectiveTenders() {
    }

    public GetCollectiveTenderPartOneDTO getTender() {
        return tender;
    }

    public void setTender(GetCollectiveTenderPartOneDTO tender) {
        this.tender = tender;
    }

    public List<GetCollectiveTenderPartYTwoDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<GetCollectiveTenderPartYTwoDTO> companies) {
        this.companies = companies;
    }
}
