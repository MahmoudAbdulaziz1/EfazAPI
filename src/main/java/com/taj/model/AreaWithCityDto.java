package com.taj.model;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 9/17/2018.
 */
public class AreaWithCityDto {

    Map<AreaDataDto, List<CityDataDto>> res;
    private List<AreaDataDto> schools;

    public AreaWithCityDto(Map<AreaDataDto, List<CityDataDto>> res, List<AreaDataDto> schools) {
        this.res = res;
        this.schools = schools;
    }

    public AreaWithCityDto(List<AreaDataDto> schools) {
        this.res = res;
        this.schools = schools;
    }

    public AreaWithCityDto() {
    }

    public Map<AreaDataDto, List<CityDataDto>> getRes() {
        return res;
    }

    public void setRes(Map<AreaDataDto, List<CityDataDto>> res) {
        this.res = res;
    }

    public List<AreaDataDto> getSchools() {
        return schools;
    }

    public void setSchools(List<AreaDataDto> schools) {
        this.schools = schools;
    }
}
