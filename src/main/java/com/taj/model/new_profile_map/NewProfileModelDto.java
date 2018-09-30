package com.taj.model.new_profile_map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 9/30/2018.
 */
public class NewProfileModelDTO {

    private int companyId;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "company_name should have at least 1 characters")
    private String companyName;
    @NotNull
    private byte[] companyLogoImage;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "address should have at least 1 characters")
    private String companyAddress;
    private String companyLinkYoutube;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "website should have at least 1 characters")
    private String companyWebsiteUrl;
    private float companyLng;
    private float companyLat;
    @NotNull
    private byte[] companyCoverImage;
    private String companyPhoneNumber;
    private int followerCount;
    private int orderCount;
    private String companyDesc;
    private int categoryNum;
    private String city;
    private String area;
    private float lng;
    private float lat;

    public NewProfileModelDTO(int companyId, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters")
    String companyName, @NotNull byte[] companyLogoImage, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address should have at least 1 characters")
                           String companyAddress, String companyLinkYoutube, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "website should have at least 1 characters")
                           String companyWebsiteUrl, float companyLng, float companyLat, @NotNull byte[] companyCoverImage, String companyPhoneNumber, int followerCount,
                              int orderCount, String companyDesc, int categoryNum, String city, String area, float lng, float lat) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyLogoImage = companyLogoImage;
        this.companyAddress = companyAddress;
        this.companyLinkYoutube = companyLinkYoutube;
        this.companyWebsiteUrl = companyWebsiteUrl;
        this.companyLng = companyLng;
        this.companyLat = companyLat;
        this.companyCoverImage = companyCoverImage;
        this.companyPhoneNumber = companyPhoneNumber;
        this.followerCount = followerCount;
        this.orderCount = orderCount;
        this.companyDesc = companyDesc;
        this.categoryNum = categoryNum;
        this.city = city;
        this.area = area;
        this.lat = lat;
        this.lng = lng;
    }

    public NewProfileModelDTO(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String companyName, @NotNull byte[] companyLogoImage, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address should have at least 1 characters") String companyAddress, String companyLinkYoutube, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "website should have at least 1 characters") String companyWebsiteUrl, float companyLng,
                              float companyLat, @NotNull byte[] companyCoverImage, String companyPhoneNumber, int followerCount, int orderCount, String companyDesc,
                              int categoryNum, String city, String area, float lng, float lat) {
        this.companyName = companyName;
        this.companyLogoImage = companyLogoImage;
        this.companyAddress = companyAddress;
        this.companyLinkYoutube = companyLinkYoutube;
        this.companyWebsiteUrl = companyWebsiteUrl;
        this.companyLng = companyLng;
        this.companyLat = companyLat;
        this.companyCoverImage = companyCoverImage;
        this.companyPhoneNumber = companyPhoneNumber;
        this.followerCount = followerCount;
        this.orderCount = orderCount;
        this.companyDesc = companyDesc;
        this.categoryNum = categoryNum;
        this.city = city;
        this.area = area;
        this.lat = lat;
        this.lng = lng;
    }

    public NewProfileModelDTO() {
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public byte[] getCompanyLogoImage() {
        return companyLogoImage;
    }

    public void setCompanyLogoImage(byte[] companyLogoImage) {
        this.companyLogoImage = companyLogoImage;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyLinkYoutube() {
        return companyLinkYoutube;
    }

    public void setCompanyLinkYoutube(String companyLinkYoutube) {
        this.companyLinkYoutube = companyLinkYoutube;
    }

    public String getCompanyWebsiteUrl() {
        return companyWebsiteUrl;
    }

    public void setCompanyWebsiteUrl(String companyWebsiteUrl) {
        this.companyWebsiteUrl = companyWebsiteUrl;
    }

    public float getCompanyLng() {
        return companyLng;
    }

    public void setCompanyLng(float companyLng) {
        this.companyLng = companyLng;
    }

    public float getCompanyLat() {
        return companyLat;
    }

    public void setCompanyLat(float companyLat) {
        this.companyLat = companyLat;
    }

    public byte[] getCompanyCoverImage() {
        return companyCoverImage;
    }

    public void setCompanyCoverImage(byte[] companyCoverImage) {
        this.companyCoverImage = companyCoverImage;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        this.categoryNum = categoryNum;
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
