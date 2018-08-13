package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 8/13/2018.
 */
public class UpdatePasswordModel {

    @NotNull
    private int login_id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 11, message="email should have at least 2 characters")
    private String user_email;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 8, message="password should have at least 8 characters")
    private String user_password;

    public UpdatePasswordModel(int login_id, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 11, message = "email should have at least 2 characters") String user_email, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String user_password) {
        this.login_id = login_id;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public UpdatePasswordModel() {
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
}
