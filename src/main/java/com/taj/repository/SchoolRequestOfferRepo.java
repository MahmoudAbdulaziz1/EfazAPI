package com.taj.repository;

import com.taj.model.CompanyResponseSchoolRequestModel;
import com.taj.model.SchoolRequestOfferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@Repository
public class SchoolRequestOfferRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addSchoolRequestOffer(int requsted_school_id, int requsted_offer_id, int is_accepted) {
        return jdbcTemplate.update("INSERT INTO efaz_school_request_offer VALUES (?,?,?,?)", null, requsted_school_id, requsted_offer_id, is_accepted);
    }

    public List<SchoolRequestOfferModel> getSchoolRequestOffer() {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_offer;",
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4))));
    }

    public SchoolRequestOfferModel getSchoolRequestOffer(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_school_request_offer WHERE request_id=?;", new Object[]{id},
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4))));
    }


    public List<SchoolRequestOfferModel> getSchoolRequestOfferBySchool(int schoolId) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_offer WHERE requsted_school_id=?;", new Object[]{schoolId},
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4))));
    }

    public List<SchoolRequestOfferModel> getSchoolRequestOfferByOffer(int offerId) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_offer WHERE requsted_offer_id=?;", new Object[]{offerId},
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4))));
    }

    public List<SchoolRequestOfferModel> getSchoolRequestOfferByAccept(int acceptId) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_offer WHERE is_accepted=?;", new Object[]{acceptId},
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4))));
    }

    public int acceptSchoolRequestOffer(int response_id) {
        return jdbcTemplate.update("UPDATE efaz_school_request_offer SET is_accepted=1 WHERE request_id=?", response_id);
    }

    public int refuseSchoolRequestOffer(int response_id) {
        return jdbcTemplate.update("UPDATE efaz_school_request_offer SET is_accepted=0 WHERE request_id=?", response_id);
    }

    public int updateResponseSchoolRequest(int request_id, int requsted_school_id, int requsted_offer_id, int is_accepted) {
        return jdbcTemplate.update("UPDATE efaz_school_request_offer SET requsted_school_id=?, requsted_offer_id=?, is_accepted=?" +
                        " WHERE request_id=?", requsted_school_id, requsted_offer_id, is_accepted, request_id);
    }

    public int deleteResponseSchoolRequest(int seen_id) {
        return jdbcTemplate.update("DELETE FROM efaz_school_request_offer WHERE request_id=?", seen_id);
    }


}
