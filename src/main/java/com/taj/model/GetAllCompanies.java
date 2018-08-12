package com.taj.model;

import java.util.List;

/**
 * Created by User on 7/24/2018.
 */
public class GetAllCompanies {

    private String states;
    private List<CompantProfileDto> model;

    public GetAllCompanies(String states, List<CompantProfileDto> model) {
        this.states = states;
        this.model = model;
    }

    public GetAllCompanies() {
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
