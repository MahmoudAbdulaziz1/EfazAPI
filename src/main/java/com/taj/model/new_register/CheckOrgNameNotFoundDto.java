package com.taj.model.new_register;

import javax.validation.constraints.NotNull;

/**
 * Created by User on 9/26/2018.
 */
public class CheckOrgNameNotFoundDto {
    @NotNull
    private String registration_organization_name;

    public CheckOrgNameNotFoundDto(String registration_organization_name) {
        this.registration_organization_name = registration_organization_name;
    }

    public CheckOrgNameNotFoundDto() {
    }

    public String getRegistration_organization_name() {
        return registration_organization_name;
    }

    public void setRegistration_organization_name(String registration_organization_name) {
        this.registration_organization_name = registration_organization_name;
    }
}
