package com.taj.model;

import java.util.List;

/**
 * Created by User on 9/11/2018.
 */
public class NewProfileDto4 {

    private String states;
    private List<NewProfileDto2> model;

    public NewProfileDto4(String states, List<NewProfileDto2> model) {
        this.states = states;
        this.model = model;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public List<NewProfileDto2> getModel() {
        return model;
    }

    public void setModel(List<NewProfileDto2> model) {
        this.model = model;
    }
}
