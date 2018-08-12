package com.taj.repository;

import com.taj.model.ComapnyDashBoradProfileModel;
import com.taj.model.CompantProfileDto;
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

    public boolean isExist(int company_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company_profile WHERE company_id=?;",
                Integer.class, company_id);
        return cnt != null && cnt > 0;
    }

    public int addProfile(int company_id, String company_name, byte[] company_logo_image, String company_address,
                          String company_service_desc, String company_link_youtube, String company_website_url, float school_lng,
                          float school_lat, byte[] company_cover_image, String company_phone_number) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");

        int category = jdbcTemplate.queryForObject("SELECT category_id FROM efaz_company_category WHERE  category_name=?;",
                Integer.class, company_service_desc);
        int id = jdbcTemplate.update("INSERT INTO efaz_company_profile VALUES (?,?,?,?,?,?,?,?,?,?,?)", company_id, company_name, company_logo_image,
                company_address, category, company_link_youtube, company_website_url, school_lng, school_lat, company_cover_image, company_phone_number);

        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");

        return id;
    }


    public List<CompantProfileDto> getProfiles() {

        String sql = "SELECT company_id, company_name, company_logo_image, company_address, company_category_id, company_link_youtube, company_website_url, company_lng, company_lat, company_cover_image, " +
                "company_phone_number, count(follow_id) AS follower_count, count(offer_id) AS order_count FROM ((efaz_company.efaz_company_profile AS profile LEFT JOIN " +
                " efaz_company.efaz_organization_following AS follow ON profile.company_id = follow.follower_id) LEFT JOIN efaz_company_offer AS offer ON profile.company_id = offer.offer_company_id) GROUP BY profile.company_id;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new CompantProfileDto(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9),
                        resultSet.getBytes(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getInt(13)));
    }


    public CompanyProfileModel getProfile(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company_profile WHERE  company_id=?;",
                new Object[]{id}, (resultSet, i) -> new CompanyProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9), resultSet.getBytes(10), resultSet.getString(11)));
    }

    public List<CompanyProfileModel> getProfileByCategory(int id) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_profile WHERE  company_category_id=?;",
                new Object[]{id}, (resultSet, i) -> new CompanyProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9), resultSet.getBytes(10), resultSet.getString(11)));
    }

    public int CheckProfile(int id) {
        int count = jdbcTemplate.queryForObject("SELECT Count(*) FROM efaz_company_profile WHERE  company_id=?;",
                Integer.class, id);
        return count;
    }


    public int updateProfile(int id, String company_name, byte[] company_logo_image, String company_address,
                             String company_service_desc, String company_link_youtube, String company_website_url,
                             float school_lng, float school_lat, byte[] company_cover_image, String company_phone_number) {

        int category = jdbcTemplate.queryForObject("SELECT company_category_id FROM efaz_company_profile WHERE  company_id=?;",
                Integer.class, id);
        return jdbcTemplate.update("update efaz_company_profile set company_name=?," +
                        "company_logo_image=?, company_address=?," +
                        "company_category_id=?, company_link_youtube=?, company_website_url=?, company_lng=?, company_lat=?, company_cover_image=?, company_phone_number=? " +
                        " where company_id=?", company_name, company_logo_image, company_address, category
                , company_link_youtube, company_website_url, school_lng, school_lat, company_cover_image, company_phone_number, id);

    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public List<ComapnyDashBoradProfileModel> getProfilesForDashCompany() {
        return jdbcTemplate.query("SELECT * FROM efaz_company_profile;",
                (resultSet, i) -> new ComapnyDashBoradProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getBytes(10), resultSet.getString(11)));
    }


//    public int CheckProfile(int id) {
//        int count = jdbcTemplate.queryForObject("SELECT Count(*) FROM efaz_company_profile WHERE  company_id=?;",
//                Integer.class, id);
//        return count;
//    }
//
//
    public int updateProfileForAdmin(int id, String company_name, byte[] company_logo_image, String company_address,
                              String company_link_youtube, String company_website_url,
                             byte[] company_cover_image, String company_phone_number) {

        float lat = jdbcTemplate.queryForObject("SELECT company_lat FROM efaz_company_profile WHERE  company_id=?;",
                Float.class, id);
        float lng = jdbcTemplate.queryForObject("SELECT company_lng FROM efaz_company_profile WHERE  company_id=?;",
                Float.class, id);
        int category = jdbcTemplate.queryForObject("SELECT company_category_id FROM efaz_company_profile WHERE  company_id=?;",
                Integer.class, id);

        return jdbcTemplate.update("update efaz_company_profile set company_name=?," +
                        "company_logo_image=?, company_address=?," +
                        "company_category_id=?, company_link_youtube=?, company_website_url=?, company_lng=?, company_lat=?, company_cover_image=?, company_phone_number=? " +
                        " where company_id=?", company_name, company_logo_image, company_address, category
                , company_link_youtube, company_website_url, lng, lat, company_cover_image, company_phone_number, id);

    }















}
