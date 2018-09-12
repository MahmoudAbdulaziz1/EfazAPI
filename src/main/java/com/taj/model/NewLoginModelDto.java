package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 9/12/2018.
 */
public class NewLoginModelDto {


    private int login_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 11, message = "email should have at least 2 characters")
    private String user_email;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 8, message = "password should have at least 8 characters")
    private String user_password;
    @NotNull
    private int is_active;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "role should have at least 1 characters")
    private String login_role;
    private long login_date;

    private String login_token;
    private String registration_organization_name;

    public NewLoginModelDto(int login_id, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 11, message = "email should have at least 2 characters") String user_email, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String user_password, @NotNull int is_active, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters")
    String login_role, long login_date, String login_token, String registration_organization_name) {
        this.login_id = login_id;
        this.user_email = user_email;
        this.user_password = user_password;
        this.is_active = is_active;
        this.login_role = login_role;
        this.login_date = login_date;
        this.login_token = login_token;
        this.registration_organization_name = registration_organization_name;
    }


    public NewLoginModelDto(int login_id, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 11, message = "email should have at least 2 characters") String user_email, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String user_password, @NotNull int is_active, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters")
    String login_role, String login_token, String registration_organization_name) {
        this.login_id = login_id;
        this.user_email = user_email;
        this.user_password = user_password;
        this.is_active = is_active;
        this.login_role = login_role;
        this.login_token = login_token;
        this.registration_organization_name = registration_organization_name;
    }


    public NewLoginModelDto(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 11, message = "email should have at least 2 characters") String user_email, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String user_password, @NotNull int is_active, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters")
    String login_role, long login_date, String login_token, String registration_organization_name) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.is_active = is_active;
        this.login_role = login_role;
        this.login_date = login_date;
        this.login_token = login_token;
        this.registration_organization_name = registration_organization_name;
    }

    public NewLoginModelDto() {
    }

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getLogin_role() {
        return login_role;
    }

    public void setLogin_role(String login_role) {
        this.login_role = login_role;
    }

    public long getLogin_date() {
        return login_date;
    }

    public void setLogin_date(long login_date) {
        this.login_date = login_date;
    }

    public String getLogin_token() {
        return login_token;
    }

    public void setLogin_token(String login_token) {
        this.login_token = login_token;
    }

    public String getRegistration_organization_name() {
        return registration_organization_name;
    }

    public void setRegistration_organization_name(String registration_organization_name) {
        this.registration_organization_name = registration_organization_name;
    }
}
