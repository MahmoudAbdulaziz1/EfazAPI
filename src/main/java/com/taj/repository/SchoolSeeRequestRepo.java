package com.taj.repository;

import com.taj.model.SchoolSeeRequest;
import com.taj.model.TakatafSchoolSeeTenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
@Repository
public class SchoolSeeRequestRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;



    public boolean isExist(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_school_see_offer WHERE seen_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }

    public boolean isExistOffer(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company_offer WHERE offer_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }

    public boolean isExistOrganization(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_school_profile WHERE school_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }

    public boolean isExistOrganizationAndOffer(int offer_id, int school_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.efaz_school_see_offer WHERE seen_offer_school_id=? AND seen_offer_id=?;",
                Integer.class, school_id, offer_id);
        return cnt != null && cnt > 0;
    }


    public int addSeen(int seen_offer_id, int seen_offer_school_id) {
        return jdbcTemplate.update("INSERT INTO efaz_school_see_offer VALUES (?,?,?)", null, seen_offer_id, seen_offer_school_id);
    }

    public List<SchoolSeeRequest> getOffersSeen() {
        return jdbcTemplate.query("SELECT * FROM efaz_school_see_offer;",
                ((resultSet, i) -> new SchoolSeeRequest(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public SchoolSeeRequest getRequestSeen(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_school_see_offer WHERE seen_id=?;", new Object[]{id},
                ((resultSet, i) -> new SchoolSeeRequest(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }


    public List<SchoolSeeRequest> getOffersSeenBySchool(int schoolId) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_see_offer WHERE seen_offer_school_id=?;", new Object[]{schoolId},
                ((resultSet, i) -> new SchoolSeeRequest(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public List<SchoolSeeRequest> getOffersSeenByOffer(int offerId) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_see_offer WHERE seen_offer_id=?;", new Object[]{offerId},
                ((resultSet, i) -> new SchoolSeeRequest(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public int updateOffersSeen(int seen_id, int seen_offer_id, int seen_school_id) {
        return jdbcTemplate.update("UPDATE efaz_school_see_offer SET seen_offer_id=?, seen_offer_school_id=? WHERE seen_id=?", seen_offer_id, seen_school_id, seen_id);
    }

    public int deleteOffersSeen(int seen_id) {
        return jdbcTemplate.update("DELETE FROM efaz_school_see_offer WHERE seen_id=?", seen_id);
    }

}
