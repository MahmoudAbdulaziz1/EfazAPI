package com.taj.model;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

/**
 * Created by MahmoudAhmed on 5/30/2018.
 */
public class RegistrationModel {


    @Min(1)
    private int registration_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 11, message="email should have at least 11 characters")
    private String registeration_email;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 8, message="password should have at least 8 characters")
    private String registeration_password;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="username should have at least 1 characters")
    private String registeration_username;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="phone_number should have at least 1 characters")
    private String registeration_phone_number;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="organization_name should have at least 1 characters")
    private String registration_organization_name;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="address_desc should have at least 1 characters")
    private String registration_address_desc;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 11, message="website_url should have at least 11characters")
    private String registration_website_url;
    @NotNull
    @PositiveOrZero(message = "must add value")
    private int registration_is_school;
    @NotNull
    private int registration_isActive;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="role should have at least 1 characters")
    private String registration_role;//for confirmation


    public RegistrationModel(int registration_id, String registeration_email, String registeration_password, String registeration_username,
                             String registeration_phone_number, String registration_organization_name, String registration_address_desc, String registration_website_url,
                             int registration_is_school, int registration_isActive, String registration_role) {

        this.registration_id = registration_id;
        this.registeration_email = registeration_email;
        this.registeration_password = registeration_password;
        this.registeration_username = registeration_username;
        this.registeration_phone_number = registeration_phone_number;
        this.registration_organization_name = registration_organization_name;
        this.registration_address_desc = registration_address_desc;
        this.registration_website_url = registration_website_url;
        this.registration_is_school = registration_is_school;
        this.registration_isActive = registration_isActive;
        this.registration_role = registration_role;
    }

    public RegistrationModel(String registeration_email, String registeration_password, String registeration_username,
                             String registeration_phone_number, String registration_organization_name,
                             String registration_address_desc, String registration_website_url, int registration_is_school,
                             int registration_isActive, String registration_role) {
        this.registeration_email = registeration_email;
        this.registeration_password = registeration_password;
        this.registeration_username = registeration_username;
        this.registeration_phone_number = registeration_phone_number;
        this.registration_organization_name = registration_organization_name;
        this.registration_address_desc = registration_address_desc;
        this.registration_website_url = registration_website_url;
        this.registration_is_school = registration_is_school;
        this.registration_isActive = registration_isActive;
        this.registration_role = registration_role;
    }

    public RegistrationModel() {
    }

    public int getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(int registration_id) {
        this.registration_id = registration_id;
    }

    public String getRegisteration_email() {
        return registeration_email;
    }

    public void setRegisteration_email(String registeration_email) {
        this.registeration_email = registeration_email;
    }

    public String getRegisteration_password() {
        return registeration_password;
    }

    public void setRegisteration_password(String registeration_password) {
        this.registeration_password = registeration_password;
    }

    public String getRegisteration_username() {
        return registeration_username;
    }

    public void setRegisteration_username(String registeration_username) {
        this.registeration_username = registeration_username;
    }

    public String getRegisteration_phone_number() {
        return registeration_phone_number;
    }

    public void setRegisteration_phone_number(String registeration_phone_number) {
        this.registeration_phone_number = registeration_phone_number;
    }

    public String getRegistration_organization_name() {
        return registration_organization_name;
    }

    public void setRegistration_organization_name(String registration_organization_name) {
        this.registration_organization_name = registration_organization_name;
    }

    public String getRegistration_address_desc() {
        return registration_address_desc;
    }

    public void setRegistration_address_desc(String registration_address_desc) {
        this.registration_address_desc = registration_address_desc;
    }

    public String getRegistration_website_url() {
        return registration_website_url;
    }

    public void setRegistration_website_url(String registration_website_url) {
        this.registration_website_url = registration_website_url;
    }

    public int getRegistration_is_school() {
        return registration_is_school;
    }

    public void setRegistration_is_school(int registration_is_school) {
        this.registration_is_school = registration_is_school;
    }

    public int getRegistration_isActive() {
        return registration_isActive;
    }

    public void setRegistration_isActive(int registration_isActive) {
        this.registration_isActive = registration_isActive;
    }

    public String getRegistration_role() {
        return registration_role;
    }

    public void setRegistration_role(String registration_role) {
        this.registration_role = registration_role;
    }
}
