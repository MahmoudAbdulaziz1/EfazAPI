package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by User on 9/20/2018.
 */
public class CompanyProfileDtoTwo {
    private int companyId;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "company_name should have at least 1 characters")
    private String companyName;
    @NotNull
    private String companyLogoImage;
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
    private String companyCoverImage;
    private String companyPhoneNumber;
    private long followerCount;
    private long orderCount;
    private String companyDesc;
    private long categoryNum;
    private String city;
    private String area;
    private int is_follow;
    private List<CompanyProfileDtoOne> categories;

    public CompanyProfileDtoTwo(int companyId, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "company_name should have at least 1 characters") String companyName, @NotNull String companyLogoImage, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address should have at least 1 characters") String companyAddress, String companyLinkYoutube, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "website should have at least 1 characters") String companyWebsiteUrl, float companyLng, float companyLat, @NotNull String companyCoverImage, String companyPhoneNumber,
                                long followerCount, long orderCount, String companyDesc, long categoryNum, String city, String area, int is_follow, List<CompanyProfileDtoOne> categories) {
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
        this.is_follow = is_follow;
        this.categories = categories;
    }

    public CompanyProfileDtoTwo() {
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

    public String getCompanyLogoImage() {
        return companyLogoImage;
    }

    public void setCompanyLogoImage(String companyLogoImage) {
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

    public String getCompanyCoverImage() {
        return companyCoverImage;
    }

    public void setCompanyCoverImage(String companyCoverImage) {
        this.companyCoverImage = companyCoverImage;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public long getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(long followerCount) {
        this.followerCount = followerCount;
    }

    public long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(long orderCount) {
        this.orderCount = orderCount;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public long getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(long categoryNum) {
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

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }

    public List<CompanyProfileDtoOne> getCategories() {
        return categories;
    }

    public void setCategories(List<CompanyProfileDtoOne> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyProfileDtoTwo that = (CompanyProfileDtoTwo) o;

        if (companyId != that.companyId) return false;
        if (Float.compare(that.companyLng, companyLng) != 0) return false;
        if (Float.compare(that.companyLat, companyLat) != 0) return false;
        if (followerCount != that.followerCount) return false;
        if (orderCount != that.orderCount) return false;
        if (categoryNum != that.categoryNum) return false;
        if (is_follow != that.is_follow) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (companyLogoImage != null ? !companyLogoImage.equals(that.companyLogoImage) : that.companyLogoImage != null)
            return false;
        if (companyAddress != null ? !companyAddress.equals(that.companyAddress) : that.companyAddress != null)
            return false;
        if (companyLinkYoutube != null ? !companyLinkYoutube.equals(that.companyLinkYoutube) : that.companyLinkYoutube != null)
            return false;
        if (companyWebsiteUrl != null ? !companyWebsiteUrl.equals(that.companyWebsiteUrl) : that.companyWebsiteUrl != null)
            return false;
        if (companyCoverImage != null ? !companyCoverImage.equals(that.companyCoverImage) : that.companyCoverImage != null)
            return false;
        if (companyPhoneNumber != null ? !companyPhoneNumber.equals(that.companyPhoneNumber) : that.companyPhoneNumber != null)
            return false;
        if (companyDesc != null ? !companyDesc.equals(that.companyDesc) : that.companyDesc != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        return !(categories != null ? !categories.equals(that.categories) : that.categories != null);

    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyLogoImage != null ? companyLogoImage.hashCode() : 0);
        result = 31 * result + (companyAddress != null ? companyAddress.hashCode() : 0);
        result = 31 * result + (companyLinkYoutube != null ? companyLinkYoutube.hashCode() : 0);
        result = 31 * result + (companyWebsiteUrl != null ? companyWebsiteUrl.hashCode() : 0);
        result = 31 * result + (companyLng != +0.0f ? Float.floatToIntBits(companyLng) : 0);
        result = 31 * result + (companyLat != +0.0f ? Float.floatToIntBits(companyLat) : 0);
        result = 31 * result + (companyCoverImage != null ? companyCoverImage.hashCode() : 0);
        result = 31 * result + (companyPhoneNumber != null ? companyPhoneNumber.hashCode() : 0);
        result = 31 * result + (int) (followerCount ^ (followerCount >>> 32));
        result = 31 * result + (int) (orderCount ^ (orderCount >>> 32));
        result = 31 * result + (companyDesc != null ? companyDesc.hashCode() : 0);
        result = 31 * result + (int) (categoryNum ^ (categoryNum >>> 32));
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + is_follow;
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanyProfileDtoTwo{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyLogoImage='" + companyLogoImage + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyLinkYoutube='" + companyLinkYoutube + '\'' +
                ", companyWebsiteUrl='" + companyWebsiteUrl + '\'' +
                ", companyLng=" + companyLng +
                ", companyLat=" + companyLat +
                ", companyCoverImage='" + companyCoverImage + '\'' +
                ", companyPhoneNumber='" + companyPhoneNumber + '\'' +
                ", followerCount=" + followerCount +
                ", orderCount=" + orderCount +
                ", companyDesc='" + companyDesc + '\'' +
                ", categoryNum=" + categoryNum +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", is_follow=" + is_follow +
                ", categories=" + categories +
                '}';
    }
}
