package com.taj.repository;

import com.taj.model.SchoolProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
@Repository
public class SchoolProfileRepo {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addSchoolProfile(int school_id, String school_name, byte[] school_logo_image, String school_address,
                                String school_service_desc, String school_link_youtube, String school_website_url) {
        return jdbcTemplate.update("INSERT INTO efaz_school_profile VALUES (?,?,?,?,?,?,?)", school_id, school_name,
                school_logo_image, school_address, school_service_desc, school_link_youtube, school_website_url);
    }


    public List<SchoolProfileModel> getSchoolSProfiles() {
        return jdbcTemplate.query("SELECT * FROM efaz_school_profile;",
                (resultSet, i) -> new SchoolProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7)));
    }

    public SchoolProfileModel getSchoolProfile(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_school_profile WHERE  school_id=?;",
                new Object[]{id}, (resultSet, i) -> new SchoolProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7)));
    }

    public int updateProfile(int id, String school_name, byte[] school_logo_image, String school_address,
                             String school_service_desc, String school_link_youtube, String school_website_url) {

        return jdbcTemplate.update("update efaz_school_profile set school_name=?," +
                        "school_logo_image=?, school_address=?," +
                        "school_service_desc=?, school_link_youtube=?, school_website_url=?" +
                        " where school_id=?", school_name, school_logo_image, school_address, school_service_desc
                , school_link_youtube, school_website_url, id);

    }
    public int checkSchoolProfile(int id) {
        int count = jdbcTemplate.queryForObject("SELECT Count(*) FROM efaz_school_profile WHERE  school_id=?;",
                Integer.class, id);
        return count;
    }

    public int deleteSchoolProfile(int id){
        return jdbcTemplate.update("DELETE FROM efaz_school_profile WHERE school_id=?", id);
    }

}
