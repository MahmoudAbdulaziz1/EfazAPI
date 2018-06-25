package com.taj.repository;

import com.taj.model.CompanyOfferModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by MahmoudAhmed on 6/4/2018.
 */
@Repository
public class CompanyOfferRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addCompanyOffer(byte[] offer_logo, String offer_title, String offer_explaination, double offer_cost,
                               Timestamp offer_display_date, Timestamp offer_expired_date, Timestamp offer_deliver_date, int company_id){
        return jdbcTemplate.update("INSERT INTO efaz_company_offer VALUES (?,?,?,?,?,?,?,?,?)", null, offer_logo, offer_title, offer_explaination,
                offer_cost, offer_display_date, offer_expired_date, offer_deliver_date, company_id);
    }

    public List<CompanyOfferModel> getAllOffers(){
        return jdbcTemplate.query("SELECT * FROM efaz_company_offer;",
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1),resultSet.getBytes(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6),resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9)));
    }

    public CompanyOfferModel getCompanyOffer(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company_offer WHERE offer_id=?;", new Object[]{id},
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1),resultSet.getBytes(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6),resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9)));
    }


    public List<CompanyOfferModel> getCompanyOffers(int id){
        return jdbcTemplate.query("SELECT * FROM efaz_company_offer WHERE company_id=?;", new Object[]{id},
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1),resultSet.getBytes(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6),resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9)));
    }




    public int updateCompanyOffer(int offer_id, byte[] offer_logo, String offer_title, String offer_explaination, double offer_cost,
                                  Timestamp offer_display_date, Timestamp offer_expired_date, Timestamp offer_deliver_date, int company_id){
        return jdbcTemplate.update("update efaz_company_offer set offer_logo=?," +
                        "offer_title=?, offer_explaination=?," + "offer_cost=?, offer_display_date=?, offer_expired_date=?,"+
                        "offer_deliver_date=?, company_id=?"+ " where offer_id=?", offer_logo,offer_title,
                        offer_explaination, offer_cost, offer_display_date, offer_expired_date, offer_deliver_date, company_id, offer_id);
    }

    public int deleteCompanyOffer(int id){
        return jdbcTemplate.update("DELETE FROM efaz_company_offer WHERE offer_id=?", id);
    }

}
