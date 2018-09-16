package com.taj.model;

/**
 * Created by User on 9/16/2018.
 */
public class CityModel {
    private int citId;
    private String cityName;
    private int cityAreaId;


    public CityModel(int citId, String cityName, int cityAreaId) {
        this.citId = citId;
        this.cityName = cityName;
        this.cityAreaId = cityAreaId;
    }

    public CityModel() {
    }

    public int getCitId() {
        return citId;
    }

    public void setCitId(int citId) {
        this.citId = citId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityAreaId() {
        return cityAreaId;
    }

    public void setCityAreaId(int cityAreaId) {
        this.cityAreaId = cityAreaId;
    }
}
