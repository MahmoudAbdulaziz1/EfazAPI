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


    public int addSeen(int seen_id, int seen_offer_id, int seen_offer_school_id) {
        return jdbcTemplate.update("INSERT INTO efaz_school_see_offer VALUES (?,?,?)", seen_id, seen_offer_id, seen_offer_school_id);
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