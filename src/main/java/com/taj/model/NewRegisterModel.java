package com.taj.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 9/10/2018.
 */
public class NewRegisterModel {


    private int registrationId;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 10, message = "email should have at least 11 characters")
    private String registerationEmail;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 8, message = "password should have at least 8 characters")
    private String registerationPassword;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "username should have at least 1 characters")
    private String registerationUsername;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "phone_number should have at least 1 characters")
    private String registerationPhoneNumber;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "organization_name should have at least 1 characters")
    private String registrationOrganizationName;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "address_desc should have at least 1 characters")
    private String registrationAddressDesc;

    private String registrationWebsiteUrl;

    private int registrationIsActive;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "role should have at least 1 characters")
    private String registrationRole;//for confirmation
    private long registerationDate;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "role should have at least 1 characters")
    private String city;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 450, min = 1, message = "role should have at least 1 characters")
    private String area;
    private int archive;
    private int consider;
    private float lng;
    private float lat;

    public NewRegisterModel() {
    }

    public NewRegisterModel(int registrationId,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 10, message = "email should have at least 11 characters") String registerationEmail,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String registerationPassword,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "username should have at least 1 characters") String registerationUsername,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "phone_number should have at least 1 characters") String registerationPhoneNumber,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "organization_name should have at least 1 characters") String registrationOrganizationName,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address_desc should have at least 1 characters") String registrationAddressDesc,
                            String registrationWebsiteUrl, @NotNull int registrationIsActive,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String registrationRole, long registerationDate,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String city,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String area, int archive, int consider,
                            float lng, float lat) {
        this.registrationId = registrationId;
        this.registerationEmail = registerationEmail;
        this.registerationPassword = registerationPassword;
        this.registerationUsername = registerationUsername;
        this.registerationPhoneNumber = registerationPhoneNumber;
        this.registrationOrganizationName = registrationOrganizationName;
        this.registrationAddressDesc = registrationAddressDesc;
        this.registrationWebsiteUrl = registrationWebsiteUrl;
        this.registrationIsActive = registrationIsActive;
        this.registrationRole = registrationRole;
        this.registerationDate = registerationDate;
        this.city = city;
        this.area = area;
        this.archive = archive;
        this.consider = consider;
        this.lat = lat;
        this.lng = lng;
    }


    public NewRegisterModel(int registrationId,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 10, message = "email should have at least 11 characters") String registerationEmail,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String registerationPassword,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "username should have at least 1 characters") String registerationUsername,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "phone_number should have at least 1 characters") String registerationPhoneNumber,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "organization_name should have at least 1 characters") String registrationOrganizationName,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address_desc should have at least 1 characters") String registrationAddressDesc,
                            String registrationWebsiteUrl,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String registrationRole, long registerationDate,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String city,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String area,float lng,
                            float lat) {
        this.registrationId = registrationId;
        this.registerationEmail = registerationEmail;
        this.registerationPassword = registerationPassword;
        this.registerationUsername = registerationUsername;
        this.registerationPhoneNumber = registerationPhoneNumber;
        this.registrationOrganizationName = registrationOrganizationName;
        this.registrationAddressDesc = registrationAddressDesc;
        this.registrationWebsiteUrl = registrationWebsiteUrl;
        this.registrationRole = registrationRole;
        this.registerationDate = registerationDate;
        this.city = city;
        this.area = area;
        this.lat = lat;
        this.lng = lng;
    }



    public NewRegisterModel(@NotNull @NotBlank @NotEmpty @Size(min = 10, message = "email should have at least 11 characters") String registerationEmail, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 8, message = "password should have at least 8 characters") String registerationPassword, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "username should have at least 1 characters") String registerationUsername, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "phone_number should have at least 1 characters")
    String registerationPhoneNumber, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "organization_name should have at least 1 characters") String registrationOrganizationName, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "address_desc should have at least 1 characters") String registrationAddressDesc, String registrationWebsiteUrl, @NotNull int registrationIsActive, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String registrationRole, long registerationDate, @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters") String city,
                            @NotNull @NotBlank @NotEmpty @Size(max = 450, min = 1, message = "role should have at least 1 characters")
                            String area, int archive, int consider, float lng,
                            float lat) {
        this.registerationEmail = registerationEmail;
        this.registerationPassword = registerationPassword;
        this.registerationUsername = registerationUsername;
        this.registerationPhoneNumber = registerationPhoneNumber;
        this.registrationOrganizationName = registrationOrganizationName;
        this.registrationAddressDesc = registrationAddressDesc;
        this.registrationWebsiteUrl = registrationWebsiteUrl;
        this.registrationIsActive = registrationIsActive;
        this.registrationRole = registrationRole;
        this.registerationDate = registerationDate;
        this.city = city;
        this.area = area;
        this.archive = archive;
        this.consider = consider;
        this.lat = lat;
        this.lng = lng;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegisterationEmail() {
        return registerationEmail;
    }

    public void setRegisterationEmail(String registerationEmail) {
        this.registerationEmail = registerationEmail;
    }

    public String getRegisterationPassword() {
        return registerationPassword;
    }

    public void setRegisterationPassword(String registerationPassword) {
        this.registerationPassword = registerationPassword;
    }

    public String getRegisterationUsername() {
        return registerationUsername;
    }

    public void setRegisterationUsername(String registerationUsername) {
        this.registerationUsername = registerationUsername;
    }

    public String getRegisterationPhoneNumber() {
        return registerationPhoneNumber;
    }

    public void setRegisterationPhoneNumber(String registerationPhoneNumber) {
        this.registerationPhoneNumber = registerationPhoneNumber;
    }

    public String getRegistrationOrganizationName() {
        return registrationOrganizationName;
    }

    public void setRegistrationOrganizationName(String registrationOrganizationName) {
        this.registrationOrganizationName = registrationOrganizationName;
    }

    public String getRegistrationAddressDesc() {
        return registrationAddressDesc;
    }

    public void setRegistrationAddressDesc(String registrationAddressDesc) {
        this.registrationAddressDesc = registrationAddressDesc;
    }

    public String getRegistrationWebsiteUrl() {
        return registrationWebsiteUrl;
    }

    public void setRegistrationWebsiteUrl(String registrationWebsiteUrl) {
        this.registrationWebsiteUrl = registrationWebsiteUrl;
    }

    public int getRegistrationIsActive() {
        return registrationIsActive;
    }

    public void setRegistrationIsActive(int registrationIsActive) {
        this.registrationIsActive = registrationIsActive;
    }

    public String getRegistrationRole() {
        return registrationRole;
    }

    public void setRegistrationRole(String registrationRole) {
        this.registrationRole = registrationRole;
    }

    public long getRegisterationDate() {
        return registerationDate;
    }

    public void setRegisterationDate(long registerationDate) {
        this.registerationDate = registerationDate;
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

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public int getConsider() {
        return consider;
    }

    public void setConsider(int consider) {
        this.consider = consider;
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
