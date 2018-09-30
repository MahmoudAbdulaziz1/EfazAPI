package com.taj.model.admin_login;

import javax.validation.constraints.NotNull;

/**
 * Created by User on 10/4/2018.
 */
public class AdminLogin {

    private int id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private int is_active;
    private String role;
    private String token;
    private long date;
    private String city;
    private String area;
    private float lng;
    private float lat;
    private String name;

    public AdminLogin(int id, @NotNull String email,
                      @NotNull String password, int is_active, String role, String token, long date, String city, String area, float lng, float lat, @NotNull String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.is_active = is_active;
        this.role = role;
        this.token = token;
        this.date = date;
        this.city = city;
        this.area = area;
        this.lng = lng;
        this.lat = lat;
        this.name = name;
    }

    public AdminLogin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
