package com.taj.model;

/**
 * Created by User on 9/16/2018.
 */
public class AreaModel {

    private int areaId;
    private String areaName;

    public AreaModel(int areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public AreaModel() {
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
}
