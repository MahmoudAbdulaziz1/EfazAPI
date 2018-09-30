package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 9/11/2018.
 */
public class NewLoginModel {

    private int loginId;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 11, message = "email should have at least 2 characters")
    private String userEmail;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 8, message = "password should have at least 8 characters")
    private String userPassword;
    private int isActive;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "role should have at least 1 characters")
    private String loginRole;
    private long loginDate;
    private String loginToken;
    private String city;
    private String area;
    private float lng;
    private float lat;

    public NewLoginModel(int loginId,
                         @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 11, message = "email should have at least 2 characters") String userEmail,
                         @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String userPassword,
                         @NotNull int isActive, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String loginRole,
                         long loginDate, String loginToken, String city, String area, float lng, float lat) {
        this.loginId = loginId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isActive = isActive;
        this.loginRole = loginRole;
        this.loginDate = loginDate;
        this.loginToken = loginToken;
        this.city = city;
        this.area = area;
        this.lat = lat;
        this.lng = lng;
    }

    public NewLoginModel(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 11, message = "email should have at least 2 characters") String userEmail,
                         @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String userPassword,
                         @NotNull int isActive, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String loginRole,
                         long loginDate, String loginToken, String city, String area, float lng, float lat) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isActive = isActive;
        this.loginRole = loginRole;
        this.loginDate = loginDate;
        this.loginToken = loginToken;
        this.city = city;
        this.area = area;
        this.lat = lat;
        this.lng = lng;
    }

    public NewLoginModel() {
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getLoginRole() {
        return loginRole;
    }

    public void setLoginRole(String loginRole) {
        this.loginRole = loginRole;
    }

    public long getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(long loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
