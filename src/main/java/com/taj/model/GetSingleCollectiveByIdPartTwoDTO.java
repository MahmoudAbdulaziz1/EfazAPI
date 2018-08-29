package com.taj.model;

import com.taj.controller.TenderCollectiveByIdPart3DTO;

import java.util.List;

/**
 * Created by User on 8/29/2018.
 */
public class GetSingleCollectiveByIdPartTwoDTO {


    private String school_name;
    private byte[] school_logo_image;
    private List<TenderCollectiveByIdPart3DTO> categories;

    public GetSingleCollectiveByIdPartTwoDTO(String school_name, byte[] school_logo_image, List<TenderCollectiveByIdPart3DTO> categories) {
        this.school_name = school_name;
        this.school_logo_image = school_logo_image;
        this.categories = categories;
    }

    public GetSingleCollectiveByIdPartTwoDTO() {
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public byte[] getSchool_logo_image() {
        return school_logo_image;
    }

    public void setSchool_logo_image(byte[] school_logo_image) {
        this.school_logo_image = school_logo_image;
    }

    public List<TenderCollectiveByIdPart3DTO> getCategories() {
        return categories;
    }

    public void setCategories(List<TenderCollectiveByIdPart3DTO> categories) {
        this.categories = categories;
    }
}
