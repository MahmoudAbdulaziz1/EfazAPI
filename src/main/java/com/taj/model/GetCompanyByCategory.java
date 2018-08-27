package com.taj.model;

import java.util.List;

/**
 * Created by User on 8/1/2018.
 */
public class GetCompanyByCategory {


    private String states;
    private List<CompantProfileDto> model;

    public GetCompanyByCategory(String states, List<CompantProfileDto> model) {
        this.states = states;
        this.model = model;
    }

    public GetCompanyByCategory() {
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public List<CompantProfileDto> getModel() {
        return model;
    }

    public void setModel(List<CompantProfileDto> model) {
        this.model = model;
    }


}
