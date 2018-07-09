package com.taj.entity;

import javax.persistence.*;

/**
 * Created by User on 7/9/2018.
 */
@Entity(name = "efaz_login_details")
@Table
public class LoginDetailsEntity {
    @Id
    @GeneratedValue
    private int details_id;
    @Column
    private int login_id;
    @Column
    private int is_school;
    @Column
    private String lgoin_time;
    @Column
    private String ip_address;
    @Column
    private int is_mobill;

    public LoginDetailsEntity(int details_id, int login_id, int is_school, String lgoin_time, String ip_address, int is_mobill) {
        this.details_id = details_id;
        this.login_id = login_id;
        this.is_school = is_school;
        this.lgoin_time = lgoin_time;
        this.ip_address = ip_address;
        this.is_mobill = is_mobill;
    }


    public LoginDetailsEntity(int login_id, int is_school, String lgoin_time, String ip_address, int is_mobill) {
        this.login_id = login_id;
        this.is_school = is_school;
        this.lgoin_time = lgoin_time;
        this.ip_address = ip_address;
        this.is_mobill = is_mobill;
    }

    public LoginDetailsEntity() {
    }

    public int getDetails_id() {
        return details_id;
    }

    public void setDetails_id(int details_id) {
        this.details_id = details_id;
    }

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    public int getIs_school() {
        return is_school;
    }

    public void setIs_school(int is_school) {
        this.is_school = is_school;
    }

    public String getLgoin_time() {
        return lgoin_time;
    }

    public void setLgoin_time(String lgoin_time) {
        this.lgoin_time = lgoin_time;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public int getIs_mobill() {
        return is_mobill;
    }

    public void setIs_mobill(int is_mobill) {
        this.is_mobill = is_mobill;
    }
}
