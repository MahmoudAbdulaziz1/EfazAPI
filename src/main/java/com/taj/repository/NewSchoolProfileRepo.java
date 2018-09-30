package com.taj.repository;

import com.taj.model.NewCustomSchoolProfileModel;
import com.taj.model.NewSchoolProfileModel;
import com.taj.model.NewSchoolProfileModel2;
import com.taj.model.new_school_profile_map.NewCustomSchoolProfileModelDTO;
import com.taj.model.new_school_profile_map.NewSchoolProfileModel2DTO;
import com.taj.model.new_school_profile_map.NewSchoolProfileModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 9/18/2018.
 */
@Repository
public class NewSchoolProfileRepo {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isExist(int school_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_school_profile WHERE school_id=?;",
                Integer.class, school_id);
        return cnt != null && cnt > 0;
    }

    public boolean isExistInLogin(int school_id, String login_role) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_login WHERE login_id=? AND login_role=?;",
                Integer.class, school_id, login_role);
        return cnt != null && cnt > 0;
    }

    public int checkSchoolProfile(int id) {
        int count = jdbcTemplate.queryForObject("SELECT Count(*) FROM efaz_school_profile WHERE  school_id=?;",
                Integer.class, id);
        return count;
    }


    public int addSchoolProfile(int school_id, String school_name, byte[] school_logo_image, String school_address,
                                String school_service_desc, String school_link_youtube, String school_website_url, float school_lng,
                                float school_lat, byte[] school_cover_image, String school_phone_number) {
        return jdbcTemplate.update("INSERT INTO efaz_school_profile VALUES (?,?,?,?,?,?,?,?,?,?,?)", school_id, school_name,
                school_logo_image, school_address, school_service_desc, school_link_youtube, school_website_url, school_lng, school_lat, school_cover_image, school_phone_number);
    }

    public List<NewSchoolProfileModel2> getSchoolSProfiles() {
        String sql = "SELECT\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\tschool_address,\n" +
                "\tschool_service_desc,\n" +
                "\tschool_link_youtube,\n" +
                "\tschool_website_url,\n" +
                "\tschool_lng,\n" +
                "\tschool_lat,\n" +
                "\tschool_cover_image,\n" +
                "\tschool_phone_number,\n" +
                "\tcity,\n" +
                "\tarea \n" +
                "FROM\n" +
                "\tefaz_school_profile AS pro\n" +
                "\tLEFT JOIN efaz_company.efaz_login AS login ON pro.school_id = login.login_id;";


        String sql2 = "SELECT\n" +
                "\tpro.school_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\tschool_address,\n" +
                "\tschool_service_desc,\n" +
                "\tschool_link_youtube,\n" +
                "\tschool_website_url,\n" +
                "\tschool_lng,\n" +
                "\tschool_lat,\n" +
                "\tschool_cover_image,\n" +
                "\tschool_phone_number,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tCOUNT( request_id ) AS request_count \n" +
                "FROM\n" +
                "\tefaz_school_profile AS pro\n" +
                "\tLEFT JOIN efaz_company.efaz_login AS login ON pro.school_id = login.login_id\n" +
                "\tLEFT JOIN efaz_school_tender AS tend ON pro.school_id = tend.school_id \n" +
                "GROUP BY\n" +
                "\tpro.school_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\tschool_address,\n" +
                "\tschool_service_desc,\n" +
                "\tschool_link_youtube,\n" +
                "\tschool_website_url,\n" +
                "\tschool_lng,\n" +
                "\tschool_lat,\n" +
                "\tschool_cover_image,\n" +
                "\tschool_phone_number,\n" +
                "\tcity,\n" +
                "\tarea;";

        return jdbcTemplate.query(sql2,
                (resultSet, i) -> new NewSchoolProfileModel2(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9),
                        resultSet.getBytes(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getInt(14)));
    }


    public NewSchoolProfileModel getSchoolProfile(int id) {

        String sql = "SELECT\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\tschool_address,\n" +
                "\tschool_service_desc,\n" +
                "\tschool_link_youtube,\n" +
                "\tschool_website_url,\n" +
                "\tschool_lng,\n" +
                "\tschool_lat,\n" +
                "\tschool_cover_image,\n" +
                "\tschool_phone_number,\n" +
                "\tcity,\n" +
                "\tarea \n" +
                "FROM\n" +
                "\tefaz_school_profile AS pro\n" +
                "\tLEFT JOIN efaz_company.efaz_login AS login ON pro.school_id = login.login_id" +
                " WHERE   school_id=?;";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (resultSet, i) -> new NewSchoolProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9),
                        resultSet.getBytes(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13)));
    }


    public int updateProfile(int id, String school_name, byte[] school_logo_image, String school_address,
                             String school_service_desc, String school_link_youtube, String school_website_url, float school_lng, float school_lat,
                             byte[] school_cover_image, String school_phone_number, String city, String area) {

        jdbcTemplate.update("update efaz_company.efaz_login set city=?," +
                "area=?  " +
                " where login_id=?;", city, area, id);

        return jdbcTemplate.update("update efaz_school_profile set school_name=?," +
                        "school_logo_image=?, school_address=?," +
                        "school_service_desc=?, school_link_youtube=?, school_website_url=?, school_lng=?, school_lat=?, school_cover_image=?, school_phone_number=? " +
                        " where school_id=?", school_name, school_logo_image, school_address, school_service_desc
                , school_link_youtube, school_website_url, school_lng, school_lat, school_cover_image, school_phone_number, id);

    }

    public int deleteSchoolProfile(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        int response = jdbcTemplate.update("DELETE FROM efaz_school_profile WHERE school_id=?", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        return response;
    }

    public NewCustomSchoolProfileModel getSchoolProfileForAdmin(int id) {

        String sql = "SELECT\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\tschool_address,\n" +
                "\tschool_service_desc,\n" +
                "\tschool_link_youtube,\n" +
                "\tschool_website_url,\n" +
                "\tschool_lng,\n" +
                "\tschool_lat,\n" +
                "\tschool_cover_image,\n" +
                "\tschool_phone_number,\n" +
                "\tcity,\n" +
                "\tarea \n" +
                "FROM\n" +
                "\tefaz_school_profile AS pro\n" +
                "\tLEFT JOIN efaz_company.efaz_login AS login ON pro.school_id = login.login_id" +
                " WHERE   school_id=?;";

        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (resultSet, i) -> new NewCustomSchoolProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getBytes(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13)));
    }


    public int updateProfileForAdmin(int id, String school_name, byte[] school_logo_image, String school_address, String school_service_desc,
                                     String school_link_youtube, String school_website_url,
                                     byte[] school_cover_image, String school_phone_number, String city, String area) {

        float lat = jdbcTemplate.queryForObject("SELECT school_lat FROM efaz_school_profile WHERE  school_id=?;",
                Float.class, id);
        float lng = jdbcTemplate.queryForObject("SELECT school_lng FROM efaz_school_profile WHERE  school_id=?;",
                Float.class, id);
        jdbcTemplate.update("update efaz_company.efaz_login set city=?," +
                "area=?  " +
                " where login_id=?;", city, area, id);

        return jdbcTemplate.update("update efaz_school_profile set school_name=?," +
                        "school_logo_image=?, school_address=?," +
                        "school_service_desc=?, school_link_youtube=?, school_website_url=?, school_lng=?, school_lat=?, school_cover_image=?, school_phone_number=? " +
                        " where school_id=?", school_name, school_logo_image, school_address, school_service_desc
                , school_link_youtube, school_website_url, lng, lat, school_cover_image, school_phone_number, id);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<NewSchoolProfileModel2DTO> getSchoolSProfiles2() {

        String sql2 = "SELECT\n" +
                "\tpro.school_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\tschool_address,\n" +
                "\tschool_service_desc,\n" +
                "\tschool_link_youtube,\n" +
                "\tschool_website_url,\n" +
                "\tschool_lng,\n" +
                "\tschool_lat,\n" +
                "\tschool_cover_image,\n" +
                "\tschool_phone_number,\n" +
                "\tcity,\n" +
                "\tarea, lng, lat,\n" +
                "\tCOUNT( request_id ) AS request_count \n" +
                "FROM\n" +
                "\tefaz_school_profile AS pro\n" +
                "\tLEFT JOIN efaz_company.efaz_login AS login ON pro.school_id = login.login_id\n" +
                "\tLEFT JOIN efaz_school_tender AS tend ON pro.school_id = tend.school_id \n" +
                "GROUP BY\n" +
                "\tpro.school_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\tschool_address,\n" +
                "\tschool_service_desc,\n" +
                "\tschool_link_youtube,\n" +
                "\tschool_website_url,\n" +
                "\tschool_lng,\n" +
                "\tschool_lat,\n" +
                "\tschool_cover_image,\n" +
                "\tschool_phone_number,\n" +
                "\tcity,\n" +
                "\tarea,lng,lat;";

        return jdbcTemplate.query(sql2,
                (resultSet, i) -> new NewSchoolProfileModel2DTO(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9),
                        resultSet.getBytes(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getFloat(15),
                        resultSet.getFloat(16), resultSet.getInt(14)));
    }

    public NewSchoolProfileModelDTO getSchoolProfile2(int id) {

        String sql = "SELECT\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\tschool_address,\n" +
                "\tschool_service_desc,\n" +
                "\tschool_link_youtube,\n" +
                "\tschool_website_url,\n" +
                "\tschool_lng,\n" +
                "\tschool_lat,\n" +
                "\tschool_cover_image,\n" +
                "\tschool_phone_number,\n" +
                "\tcity,\n" +
                "\tarea, lng, lat \n" +
                "FROM\n" +
                "\tefaz_school_profile AS pro\n" +
                "\tLEFT JOIN efaz_company.efaz_login AS login ON pro.school_id = login.login_id" +
                " WHERE   school_id=?;";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (resultSet, i) -> new NewSchoolProfileModelDTO(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9),
                        resultSet.getBytes(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13),
                        resultSet.getFloat(14), resultSet.getFloat(15)));
    }

    public int updateProfile2(int id, String school_name, byte[] school_logo_image, String school_address,
                             String school_service_desc, String school_link_youtube, String school_website_url, float school_lng, float school_lat,
                             byte[] school_cover_image, String school_phone_number, String city, String area, float lng, float lat) {

        jdbcTemplate.update("update efaz_company.efaz_login set city=?," +
                "area=?, lng=?, lat=?  " +
                " where login_id=?;", city, area, lng, lat, id);

        return jdbcTemplate.update("update efaz_school_profile set school_name=?," +
                        "school_logo_image=?, school_address=?," +
                        "school_service_desc=?, school_link_youtube=?, school_website_url=?, school_lng=?, school_lat=?, school_cover_image=?, school_phone_number=? " +
                        " where school_id=?", school_name, school_logo_image, school_address, school_service_desc
                , school_link_youtube, school_website_url, school_lng, school_lat, school_cover_image, school_phone_number, id);

    }

    public NewCustomSchoolProfileModelDTO getSchoolProfileForAdmin2(int id) {

        String sql = "SELECT\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\tschool_address,\n" +
                "\tschool_service_desc,\n" +
                "\tschool_link_youtube,\n" +
                "\tschool_website_url,\n" +
                "\tschool_lng,\n" +
                "\tschool_lat,\n" +
                "\tschool_cover_image,\n" +
                "\tschool_phone_number,\n" +
                "\tcity,\n" +
                "\tarea, lng ,lat \n" +
                "FROM\n" +
                "\tefaz_school_profile AS pro\n" +
                "\tLEFT JOIN efaz_company.efaz_login AS login ON pro.school_id = login.login_id" +
                " WHERE   school_id=?;";

        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (resultSet, i) -> new NewCustomSchoolProfileModelDTO(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getBytes(10), resultSet.getString(11), resultSet.getString(12),
                        resultSet.getString(13), resultSet.getFloat(14), resultSet.getFloat(15)));
    }


    public int updateProfileForAdmin2(int id, String school_name, byte[] school_logo_image, String school_address, String school_service_desc,
                                     String school_link_youtube, String school_website_url,
                                     byte[] school_cover_image, String school_phone_number, String city, String area, float lng, float lat) {

        float schoolLat = jdbcTemplate.queryForObject("SELECT school_lat FROM efaz_school_profile WHERE  school_id=?;",
                Float.class, id);
        float schoolLng = jdbcTemplate.queryForObject("SELECT school_lng FROM efaz_school_profile WHERE  school_id=?;",
                Float.class, id);
        jdbcTemplate.update("update efaz_company.efaz_login set city=?," +
                "area=?, lng=?, lat=? " +
                " where login_id=?;", city, area, lng, lat, id);

        return jdbcTemplate.update("update efaz_school_profile set school_name=?," +
                        "school_logo_image=?, school_address=?," +
                        "school_service_desc=?, school_link_youtube=?, school_website_url=?, school_lng=?, school_lat=?, school_cover_image=?, school_phone_number=? " +
                        " where school_id=?", school_name, school_logo_image, school_address, school_service_desc
                , school_link_youtube, school_website_url, lng, lat, school_cover_image, school_phone_number, id);

    }

}
