package com.taj.model.new_profile_map;

import java.util.List;

/**
 * Created by User on 9/30/2018.
 */
public class NewProfileDto4DTO {


    private String states;
    private List<NewProfileDto2DTO> model;

    public NewProfileDto4DTO(String states, List<NewProfileDto2DTO> model) {
        this.states = states;
        this.model = model;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public List<NewProfileDto2DTO> getModel() {
        return model;
    }

    public void setModel(List<NewProfileDto2DTO> model) {
        this.model = model;
    }


}
