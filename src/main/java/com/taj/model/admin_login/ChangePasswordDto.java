package com.taj.model.admin_login;

import javax.validation.constraints.NotNull;

/**
 * Created by User on 10/1/2018.
 */
public class ChangePasswordDto {
    @NotNull
    private int id;
    @NotNull
    private String password;

    public ChangePasswordDto(@NotNull int id, @NotNull String password) {
        this.id = id;
        this.password = password;
    }

    public ChangePasswordDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
