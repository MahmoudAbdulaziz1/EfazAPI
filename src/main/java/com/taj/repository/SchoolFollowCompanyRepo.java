package com.taj.repository;

import com.taj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/19/2018.
 */
@Repository
public class SchoolFollowCompanyRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean isExist(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_organization_following WHERE follow_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }


    public boolean isRecordExist(int o_id, int f_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_organization_following WHERE organization_id=? AND follower_id=?;",
                Integer.class, o_id, f_id);
        return cnt != null && cnt > 0;
    }

    public boolean isExistFollwing(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_login WHERE login_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }

    public boolean isExistFollwer(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_login WHERE login_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }

    public int addFollower(int organization_id, int follower_id) {
        return jdbcTemplate.update("INSERT INTO efaz_organization_following VALUES (?,?,?)", null, organization_id, follower_id);
    }

    public List<SchoolFollowCompany> getAllFollowers() {
        return jdbcTemplate.query("SELECT * FROM efaz_organization_following;",
                ((resultSet, i) -> new SchoolFollowCompany(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public SchoolFollowCompany getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_organization_following WHERE follow_id=?;", new Object[]{id},
                ((resultSet, i) -> new SchoolFollowCompany(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }


    public List<SchoolFollowCompany> getAllSchoolFollowing(int follower_id) {
        return jdbcTemplate.query("SELECT * FROM efaz_organization_following WHERE follower_id=?;", new Object[]{follower_id},
                ((resultSet, i) -> new SchoolFollowCompany(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    @Autowired
    SchoolProfileRepo repo;

    public List<SchoolProfileModel> getCompanyAllFollowers(int organization_id) {
        List<SchoolFollowCompany> list = jdbcTemplate.query("SELECT * FROM efaz_organization_following WHERE organization_id=?;", new Object[]{organization_id},
                ((resultSet, i) -> new SchoolFollowCompany(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
        List<SchoolProfileModel> school_data = new ArrayList<>();
        for (SchoolFollowCompany model : list) {
            int id = model.getFollower_id();
            school_data.add(repo.getSchoolProfile(id));
        }

        return school_data;
    }


    public List<SchoolProfileModel> getCompanyAllFollowersNew(int organization_id) {
        String sql = "SELECT school_id, school_name, school_logo_image, school_address, school_service_desc, school_link_youtube, school_website_url," +
                " school_lng, school_lat, school_cover_image, school_phone_number FROM (efaz_company.efaz_school_profile AS profile INNER JOIN " +
                " efaz_company.efaz_organization_following AS follow ON profile.school_id = follow.follower_id) Where follow.organization_id = ?;";

        return jdbcTemplate.query(sql, new Object[]{organization_id}, (resultSet, i) -> new SchoolProfileModel(resultSet.getInt(1), resultSet.getString(2),
                resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9),resultSet.getBytes(10), resultSet.getString(11)));
    }



//, company_address, company_category_id, company_link_youtube, company_website_url, company_lng, company_lat, company_cover_image, " +
    //"company_phone_number

    public List<CompanyFollowSch0oolDto> getSchoolAllFollowersNew(int organization_id) {

        String sql = "SELECT  company_name, company_logo_image FROM (efaz_company.efaz_company_profile AS profile INNER JOIN " +
                " efaz_company.efaz_organization_following AS follow ON profile.company_id = follow.follower_id) Where follow.organization_id = ?;";

        return jdbcTemplate.query(sql, new Object[]{organization_id}, (resultSet, i) -> new CompanyFollowSch0oolDto(resultSet.getString(1), resultSet.getBytes(2)));

    }




    public int getFollowersCount(int organization_id) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM efaz_organization_following WHERE organization_id=?;",
                Integer.class, organization_id);

    }


    public int updateSchoolFollowCompany(int follow_id, int organization_id, int follower_id) {
        return jdbcTemplate.update("UPDATE efaz_organization_following SET follower_id=?, organization_id=? WHERE follow_id=?", follower_id, organization_id, follow_id);
    }

    public int deleteSchoolFollowCompany(int follow_id) {
        return jdbcTemplate.update("DELETE FROM efaz_organization_following WHERE follow_id=?", follow_id);
    }


    public int getId(int id1, int id2){
        return jdbcTemplate.queryForObject("SELECT follow_id FROM efaz_organization_following WHERE organization_id=? AND follower_id=?;",
                Integer.class, id1, id2);

    }

    public  List<FollowSchoolProfilesDto> getSchoolsWithFollow(int companyId){
        String sql = "SELECT school_id, school_name, school_logo_image, IF (profile.school_id = follow.organization_id AND follow.follower_id=?, true, false) " +
                "AS is_follow FROM efaz_company.efaz_school_profile AS profile Left JOIN efaz_company.efaz_organization_following " +
                "AS follow ON profile.school_id = follow.organization_id AND follow.follower_id=?;";


        List<FollowSchoolProfilesDto> list = jdbcTemplate.query(sql, new Object[]{companyId, companyId},
                ((resultSet, i) -> new FollowSchoolProfilesDto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4))));

        return list;

    }


}
