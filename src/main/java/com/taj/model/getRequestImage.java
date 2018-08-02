package com.taj.model;

/**
 * Created by User on 8/2/2018.
 */
public class getRequestImage{

    private String status;
    private  SchoolRequstImagesModel model;

    public getRequestImage(String status, SchoolRequstImagesModel model) {
        this.status = status;
        this.model = model;
    }

    public getRequestImage() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SchoolRequstImagesModel getModel() {
        return model;
    }

    public void setModel(SchoolRequstImagesModel model) {
        this.model = model;
    }
}
