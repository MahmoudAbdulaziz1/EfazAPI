package com.taj.model;

import javax.validation.constraints.*;

/**
 * Created by MahmoudAhmed on 5/30/2018.
 */
public class LoginModel {

    @Min(1)
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
    @NotNull
    private int is_active;
    @NotNull
    private int login_type;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message="role should have at least 1 characters")
    private String login_role;

    private String login_token;

    public LoginModel(int login_id, String user_email, String user_password, int is_active, int login_type, String login_role, String login_token) {
        this.login_id = login_id;
        this.user_email = user_email;
        this.user_password = user_password;
        this.is_active = is_active;
        this.login_type = login_type;
        this.login_role = login_role;
        this.login_token = login_token;
    }

//    public LoginModel(int login_id, String user_email, String user_password, int is_active, int login_type) {
//        this.login_id = login_id;
//        this.user_email = user_email;
//        this.user_password = user_password;
//        this.is_active = is_active;
//        this.login_type = login_type;
//    }

    public LoginModel() {
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

    public int getLogin_type() {
        return login_type;
    }

    public void setLogin_type(int login_type) {
        this.login_type = login_type;
    }

    public String getLogin_role() {
        return login_role;
    }

    public void setLogin_role(String login_role) {
        this.login_role = login_role;
    }

    public String getLogin_token() {
        return login_token;
    }

    public void setLogin_token(String login_token) {
        this.login_token = login_token;
    }
}
