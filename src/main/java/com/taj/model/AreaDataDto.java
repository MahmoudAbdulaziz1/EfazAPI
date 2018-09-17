package com.taj.model;

import java.util.List;

/**
 * Created by User on 6/6/2018.
 */
public class AreaDataDto {

    private int areaId;
    private String areaName;
    private List<CityDataDto> categories;

    public AreaDataDto(int areaId, String areaName, List<CityDataDto> categories) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.categories = categories;
    }

    public AreaDataDto() {
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<CityDataDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CityDataDto> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaDataDto that = (AreaDataDto) o;

        if (areaId != that.areaId) return false;
        if (areaName != null ? !areaName.equals(that.areaName) : that.areaName != null) return false;
        return !(categories != null ? !categories.equals(that.categories) : that.categories != null);

    }

    @Override
    public int hashCode() {
        int result = areaId;
        result = 31 * result + (areaName != null ? areaName.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AreaDataDto{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", categories=" + categories +
                '}';
    }
}
