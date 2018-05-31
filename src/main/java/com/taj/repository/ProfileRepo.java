package com.taj.repository;

import com.taj.model.LoginModel;
import com.taj.model.ProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by MahmoudAhmed on 5/31/2018.
 */
@Repository
public class ProfileRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addProfile(int company_id, String company_name, byte[] company_logo_image, String company_address,
                          String company_service_desc, String company_link_youtube, String company_website_url){
        return jdbcTemplate.update("INSERT INTO efaz_company_profile VALUES (?,?,?,?,?,?,?)", company_id, company_name, company_logo_image,
                company_address, company_service_desc, company_link_youtube, company_website_url);
    }


    public List<ProfileModel> getProfiles(){
        return jdbcTemplate.query("SELECT * FROM efaz_company_profile;",
                (resultSet, i) -> new ProfileModel(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7)));
    }


    public ProfileModel getProfile(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company_profile WHERE  company_id=?;",
                new Object[]{id},(resultSet, i) -> new ProfileModel(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7)));
    }


    public int updateProfile(int id, String company_name, byte[] company_logo_image, String company_address,
                              String company_service_desc, String company_link_youtube, String company_website_url){

        return jdbcTemplate.update("update efaz_company_profile set company_name=?," +
                        "company_logo_image=?, company_address=?," +
                        "company_service_desc=?, company_link_youtube=?, company_website_url=?"+
                        " where company_id=?", company_name, company_logo_image, company_address, company_service_desc
                , company_link_youtube, company_website_url, id);

    }




}
