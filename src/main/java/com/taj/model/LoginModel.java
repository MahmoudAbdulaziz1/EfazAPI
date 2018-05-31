package com.taj.model;

/**
 * Created by MahmoudAhmed on 5/30/2018.
 */
public class LoginModel {

    private int login_id;
    private String user_email;
    private String user_password;
    private int is_active;
    private int login_type;

    public LoginModel(int login_id, String user_email, String user_password, int is_active, int login_type) {
        this.login_id = login_id;
        this.user_email = user_email;
        this.user_password = user_password;
        this.is_active = is_active;
        this.login_type = login_type;
    }

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
}
