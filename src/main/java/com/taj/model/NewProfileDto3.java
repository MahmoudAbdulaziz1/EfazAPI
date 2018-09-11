package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by User on 9/11/2018.
 */
public class NewProfileDto3 {

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
    private int offerCount;
    private String companyDesc;
    private String city;
    private String area;
    private List<TakatfTenderCategoryPOJO> category;

    public NewProfileDto3(int companyId, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String companyName, @NotNull byte[] companyLogoImage, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address should have at least 1 characters") String companyAddress, String companyLinkYoutube, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "website should have at least 1 characters") String companyWebsiteUrl, float companyLng, float companyLat, @NotNull byte[] companyCoverImage,
                          String companyPhoneNumber, int followerCount, int offerCount, String companyDesc, String city, String area, List<TakatfTenderCategoryPOJO> category) {
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
        this.offerCount = offerCount;
        this.companyDesc = companyDesc;
        this.city = city;
        this.area = area;
        this.category = category;
    }

    public NewProfileDto3(@NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String companyName, @NotNull byte[] companyLogoImage, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address should have at least 1 characters") String companyAddress, String companyLinkYoutube, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "website should have at least 1 characters") String companyWebsiteUrl, float companyLng, float companyLat, @NotNull byte[] companyCoverImage, String companyPhoneNumber,
                          int followerCount, int offerCount, String companyDesc, String city, String area, List<TakatfTenderCategoryPOJO> category) {
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
        this.offerCount = offerCount;
        this.companyDesc = companyDesc;
        this.city = city;
        this.area = area;
        this.category = category;
    }

    public NewProfileDto3() {
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

    public int getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(int offerCount) {
        this.offerCount = offerCount;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
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

    public List<TakatfTenderCategoryPOJO> getCategory() {
        return category;
    }

    public void setCategory(List<TakatfTenderCategoryPOJO> category) {
        this.category = category;
    }
}
