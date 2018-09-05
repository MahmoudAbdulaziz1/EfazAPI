package com.taj.repository;

import com.taj.model.CompanyOfferModel;
import com.taj.model.CompanyResponseSchoolRequestModel;
import com.taj.model.GetSchoolsRequestOffersWitCoast;
import com.taj.model.SchoolRequestOfferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@Repository
public class SchoolRequestOfferRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public boolean checkIfExist(int offer_id){
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_school_request_offer WHERE request_id=?;;",
                Integer.class, offer_id);
        return cnt != null && cnt > 0;
    }


    public int addSchoolRequestOffer(int requsted_school_id, int requsted_offer_id, int is_accepted, int request_offer_count) {
        return jdbcTemplate.update("INSERT INTO efaz_school_request_offer VALUES (?,?,?,?,?)", null, requsted_school_id, requsted_offer_id, is_accepted, request_offer_count);
    }

    public List<SchoolRequestOfferModel> getSchoolRequestOffer() {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_offer;",
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5))));
    }

    public SchoolRequestOfferModel getSchoolRequestOffer(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_school_request_offer WHERE request_id=?;", new Object[]{id},
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5))));
    }


    public List<SchoolRequestOfferModel> getSchoolRequestOfferBySchool(int schoolId) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_offer WHERE requsted_school_id=?;", new Object[]{schoolId},
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5))));
    }

    public GetSchoolsRequestOffersWitCoast getSchoolRequestOfferByCompany(int offerId) {

        CompanyOfferModel model = jdbcTemplate.queryForObject("SELECT * FROM efaz_company_offer WHERE offer_id=?;", new Object[]{offerId},
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));
        double cost = model.getOffer_cost();
        String title = model.getOffer_title();
//        List<SchoolRequestOfferModel> allSchoolRequestOffer = jdbcTemplate.query("SELECT * FROM efaz_school_request_offer WHERE requsted_offer_id=?;",
//                new Object[]{offerId},
//                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
//                        resultSet.getInt(4), resultSet.getInt(5))));

        return new GetSchoolsRequestOffersWitCoast(cost, title, getSchoolRequestOfferByOffer(offerId));


    }

    public List<SchoolRequestOfferModel> getSchoolRequestOfferByOffer(int offerId) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_offer WHERE requsted_offer_id=?;", new Object[]{offerId},
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5))));
    }

    public List<SchoolRequestOfferModel> getSchoolRequestOfferByAccept(int acceptId) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_offer WHERE is_accepted=?;", new Object[]{acceptId},
                ((resultSet, i) -> new SchoolRequestOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5))));
    }

    public int acceptSchoolRequestOffer(int response_id) {
        int offer_id = jdbcTemplate.queryForObject("SELECT requsted_offer_id FROM efaz_company.efaz_school_request_offer WHERE request_id = ?;", new Object[]{response_id},
                Integer.class);
        jdbcTemplate.update("UPDATE efaz_company.efaz_company_offer SET offer_deliver_date=? WHERE offer_id=?", new Timestamp(System.currentTimeMillis()), offer_id);
        return jdbcTemplate.update("UPDATE efaz_school_request_offer SET is_accepted=1 WHERE request_id=?", response_id);
    }

    public int refuseSchoolRequestOffer(int response_id) {
        return jdbcTemplate.update("UPDATE efaz_school_request_offer SET is_accepted=0 WHERE request_id=?", response_id);
    }

    public int updateResponseSchoolRequest(int request_id, int requsted_school_id, int requsted_offer_id, int is_accepted, int request_offer_count) {

        return jdbcTemplate.update("UPDATE efaz_school_request_offer SET requsted_school_id=?, requsted_offer_id=?, is_accepted=? , request_offer_count=?" +
                        " WHERE request_id=?", requsted_school_id, requsted_offer_id, is_accepted, request_offer_count, request_id);
    }

    public int deleteResponseSchoolRequest(int seen_id) {
        return jdbcTemplate.update("DELETE FROM efaz_school_request_offer WHERE request_id=?", seen_id);
    }


}
