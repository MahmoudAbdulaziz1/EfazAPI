package com.taj.repository;

import com.taj.model.CompanyProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by MahmoudAhmed on 5/31/2018.
 */
@Repository
public class ProfileRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addProfile( String company_name, byte[] company_logo_image, String company_address,
                          String company_service_desc, String company_link_youtube, String company_website_url, float school_lng, float school_lat) {
        return jdbcTemplate.update("INSERT INTO efaz_company_profile VALUES (?,?,?,?,?,?,?,?,?)", null, company_name, company_logo_image,
                company_address, company_service_desc, company_link_youtube, company_website_url, school_lng, school_lat);
    }


    public List<CompanyProfileModel> getProfiles() {
        return jdbcTemplate.query("SELECT * FROM efaz_company_profile;",
                (resultSet, i) -> new CompanyProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9)));
    }


    public List<CompanyProfileModel> getProfile(int id) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_profile WHERE  company_id=?;",
                new Object[]{id}, (resultSet, i) -> new CompanyProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9)));
    }

    public int CheckProfile(int id) {
        int count = jdbcTemplate.queryForObject("SELECT Count(*) FROM efaz_company_profile WHERE  company_id=?;",
                Integer.class, id);
        return count;
    }


    public int updateProfile(int id, String company_name, byte[] company_logo_image, String company_address,
                             String company_service_desc, String company_link_youtube, String company_website_url, float school_lng, float school_lat) {

        return jdbcTemplate.update("update efaz_company_profile set company_name=?," +
                        "company_logo_image=?, company_address=?," +
                        "company_service_desc=?, company_link_youtube=?, company_website_url=?, company_lng=?, company_lat=?" +
                        " where company_id=?", company_name, company_logo_image, company_address, company_service_desc
                , company_link_youtube, company_website_url, school_lng, school_lat, id);

    }


}
