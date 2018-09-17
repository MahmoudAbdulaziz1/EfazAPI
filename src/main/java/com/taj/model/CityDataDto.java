package com.taj.model;

/**
 * Created by User on 9/17/2018.
 */
public class CityDataDto {

    private int cityId;
    private String cityName;


    public CityDataDto(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public CityDataDto() {
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
