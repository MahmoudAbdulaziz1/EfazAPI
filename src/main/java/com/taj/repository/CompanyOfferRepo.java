package com.taj.repository;

import com.taj.model.CompanyAdminGetOffers;
import com.taj.model.CompanyOfferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by MahmoudAhmed on 6/4/2018.
 */
@Repository
public class CompanyOfferRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkIfExist(int offer_id){
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_company_offer WHERE offer_id=?;;",
                Integer.class, offer_id);
        return cnt != null && cnt > 0;
    }

    public int addCompanyOffer(int offer_logo, String offer_title, String offer_explaination, double offer_cost,
                               Timestamp offer_display_date, Timestamp offer_expired_date, Timestamp offer_deliver_date, int company_id, int offer_count) {

        return jdbcTemplate.update("INSERT INTO efaz_company_offer VALUES (?,?,?,?,?,?,?,?,?,?)", null, offer_logo, offer_title, offer_explaination,
                offer_cost, offer_display_date, offer_expired_date, offer_deliver_date, company_id, offer_count);
    }

    public List<CompanyOfferModel> getAllOffers() {
        List<CompanyOfferModel> list = jdbcTemplate.query("SELECT * FROM efaz_company_offer;",
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));

        //int images_id = list.get
        return list;
    }

    public CompanyOfferModel getCompanyOffer(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company_offer WHERE offer_id=?;", new Object[]{id},
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));
    }


    public List<CompanyOfferModel> getCompanyOffers(int id) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_offer WHERE company_id=?;", new Object[]{id},
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));
    }


    public int updateCompanyOffer(int offer_id, int offer_logo, String offer_title, String offer_explaination, double offer_cost,
                                  Timestamp offer_display_date, Timestamp offer_expired_date, Timestamp offer_deliver_date, int company_id, int offer_count) {
        return jdbcTemplate.update("update efaz_company_offer set offer_image_id=?," +
                        "offer_title=?, offer_explaination=?," + "offer_cost=?, offer_display_date=?, offer_expired_date=?," +
                        "offer_deliver_date=?, company_id=?, offer_count=?" + " where offer_id=?", offer_logo, offer_title,
                offer_explaination, offer_cost, offer_display_date, offer_expired_date, offer_deliver_date, company_id, offer_count, offer_id);
    }

    public int deleteCompanyOffer(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        int response = jdbcTemplate.update("DELETE FROM efaz_company_offer WHERE offer_id=?", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        return response;
    }


    public List<String> getProgressDate(int id) {
        CompanyOfferModel s = jdbcTemplate.queryForObject("SELECT * FROM efaz_company_offer WHERE offer_id=?;", new Object[]{id},
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));

        Timestamp display = s.getOffer_display_date();
        Timestamp expire = s.getOffer_expired_date();
        Timestamp current = new Timestamp(Calendar.getInstance().getTime().getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        //Date parsedDate2 = dateFormat.parse();
        long milliseconds1 = display.getTime();
        long milliseconds2 = current.getTime();
        long diff = milliseconds2 - milliseconds1;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long rem = diff % (24 * 60 * 60 * 1000);
        long diffHours = rem / (60 * 60 * 1000);
        long rem2 = rem % (60 * 60 * 1000);
        long diffMinutes = rem2 / (60 * 1000);

        List<String> returnList = new ArrayList<String>();
        returnList.add(String.valueOf(diffDays));
        returnList.add(String.valueOf(diffHours));
        returnList.add(String.valueOf(diffMinutes));

        returnList.add(String.valueOf(display));
        returnList.add(String.valueOf(expire));

        return returnList;

    }


    public List<CompanyAdminGetOffers> getAllOffersForDash(int company_id) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_offer where company_id=?;", new Object[]{company_id},
                (resultSet, i) -> new CompanyAdminGetOffers(resultSet.getInt(1), resultSet.getString(3)
                        , resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));
    }


    public int  addOfferEdeited(byte[] image_one, byte[] image_two, byte[] image_third, byte[] image_four, String offer_title, String offer_explaination, double offer_cost,
                                 Timestamp offer_display_date,  Timestamp offer_expired_date,  Timestamp offer_deliver_date, int company_id, int offer_count){

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement("INSERT INTO company_offer_images VALUES (?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, null);
                ps.setBytes(2, image_one);
                ps.setBytes(3, image_two);/////////
                ps.setBytes(4, image_third);
                ps.setBytes(5, image_four);
                return ps;
            }

        }, key);

        int images_id = key.getKey().intValue();

        if (images_id > 0){
            return jdbcTemplate.update("INSERT INTO efaz_company_offer VALUES (?,?,?,?,?,?,?,?,?,?)", null, images_id, offer_title, offer_explaination,
                    offer_cost, offer_display_date, offer_expired_date, offer_deliver_date, company_id, offer_count);
        }else {
            return 0;
        }

    }




}
