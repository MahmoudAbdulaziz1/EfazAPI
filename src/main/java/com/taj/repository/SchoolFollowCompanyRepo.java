package com.taj.repository;

import com.taj.model.SchoolFollowCompany;
import com.taj.model.SchoolSeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public List<SchoolFollowCompany> getCompanyAllFollowers(int organization_id) {
        return jdbcTemplate.query("SELECT * FROM efaz_organization_following WHERE organization_id=?;", new Object[]{organization_id},
                ((resultSet, i) -> new SchoolFollowCompany(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public int updateSchoolFollowCompany(int follow_id, int organization_id, int follower_id) {
        return jdbcTemplate.update("UPDATE efaz_organization_following SET follower_id=?, organization_id=? WHERE follow_id=?", follower_id, organization_id, follow_id);
    }

    public int deleteSchoolFollowCompany(int follow_id) {
        return jdbcTemplate.update("DELETE FROM efaz_organization_following WHERE follow_id=?", follow_id);
    }
}
