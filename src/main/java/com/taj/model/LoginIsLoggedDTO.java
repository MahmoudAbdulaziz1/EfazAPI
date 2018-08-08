package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 8/6/2018.
 */
public class LoginIsLoggedDTO {


    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 8, message="password should have at least 8 characters")
    private String user_email;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 8, message="password should have at least 8 characters")
    private String user_password;
    @NotNull
    private int is_active;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="role should have at least 1 characters")
    private String login_role;

    public LoginIsLoggedDTO(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String user_email, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String user_password, @NotNull int is_active, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String login_role) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.is_active = is_active;
        this.login_role = login_role;
    }

    public LoginIsLoggedDTO() {
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
}
