package com.taj.model;

import java.util.List;

/**
 * Created by User on 9/6/2018.
 */
public class GetCompanyByCategoryCompany {

    private String states;
    private List<CompanyProfileDto> model;

    public GetCompanyByCategoryCompany(String states, List<CompanyProfileDto> model) {
        this.states = states;
        this.model = model;
    }

    public GetCompanyByCategoryCompany() {
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public List<CompanyProfileDto> getModel() {
        return model;
    }

    public void setModel(List<CompanyProfileDto> model) {
        this.model = model;
    }


}
