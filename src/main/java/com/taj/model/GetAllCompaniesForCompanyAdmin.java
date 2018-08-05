package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/5/2018.
 */
public class GetAllCompaniesForCompanyAdmin {

    private String states;
    private List<ComapnyDashBoradProfileModel> model;

    public GetAllCompaniesForCompanyAdmin(String states, List<ComapnyDashBoradProfileModel> model) {
        this.states = states;
        this.model = model;
    }

    public GetAllCompaniesForCompanyAdmin() {
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public List<ComapnyDashBoradProfileModel> getModel() {
        return model;
    }

    public void setModel(List<ComapnyDashBoradProfileModel> model) {
        this.model = model;
    }
}
