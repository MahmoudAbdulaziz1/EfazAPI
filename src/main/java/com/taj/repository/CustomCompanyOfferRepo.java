package com.taj.repository;

import com.taj.model.CompanyOfferModel;
import com.taj.model.CustomCompanyOfferModel;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by User on 8/5/2018.
 */
@Repository
public class CustomCompanyOfferRepo {

    @Autowired
    AddOfferImageRepo imgeRepo;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkIfExist(int offer_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_company_offer WHERE offer_id=?;",
                Integer.class, offer_id);
        return cnt != null && cnt > 0;
    }

    public int addOfferEdeited(byte[] image_one, byte[] image_two, byte[] image_third, byte[] image_four, String offer_title, String offer_explaination, double offer_cost,
                               long offer_display_date, long offer_expired_date, long offer_deliver_date, int company_id, int offer_count) {

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

        if (images_id > 0) {
            return jdbcTemplate.update("INSERT INTO efaz_company_offer VALUES (?,?,?,?,?,?,?,?,?,?)", null, images_id, offer_title, offer_explaination,
                    offer_cost, new Timestamp(offer_display_date), new Timestamp(offer_expired_date), new Timestamp(offer_deliver_date), company_id, offer_count);
        } else {
            return 0;
        }

    }

    public List<CustomCompanyOfferModel> getAllOffers() {
        System.out.println(new Date());
        String sql = "SELECT offer_id, image_one, image_two,image_three, image_four, offer_title, offer_explaination, offer_cost, offer_display_date, offer_expired_date, offer_deliver_date, " +
                "offer_company_id, offer_count FROM efaz_company.efaz_company_offer AS offer INNER JOIN efaz_company.company_offer_images AS image " +
                "ON offer.offer_image_id = image.images_id;";
        List<CustomCompanyOfferModel> list2 = jdbcTemplate.query(sql, (resultSet, i) -> new CustomCompanyOfferModel(resultSet.getInt(1),
                resultSet.getBytes(2), resultSet.getBytes(3), resultSet.getBytes(4), resultSet.getBytes(5)
                       , resultSet.getString(6), resultSet.getString(7), resultSet.getDouble(8), resultSet.getTimestamp(9).getTime(), resultSet.getTimestamp(10).getTime(),
                         resultSet.getTimestamp(11).getTime(), resultSet.getInt(12), resultSet.getInt(13)) );

//        List<CompanyOfferModel> list = jdbcTemplate.query("SELECT * FROM efaz_company_offer;",
//                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
//                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
//                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));
//        List<CustomCompanyOfferModel> list2 = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//
//            int offer_id = list.get(i).getOffer_id();
//            byte[] image_one = imgeRepo.getCompanyOfferOneImage(list.get(i).getOffer_images_id());
//            byte[] image_two = imgeRepo.getCompanyOfferTwoImage(list.get(i).getOffer_images_id());
//            byte[] image_three = imgeRepo.getCompanyOfferThreeImage(list.get(i).getOffer_images_id());
//            byte[] image_four = imgeRepo.getCompanyOfferFourImage(list.get(i).getOffer_images_id());
//            String offer_title = list.get(i).getOffer_title();
//            String offer_explain = list.get(i).getOffer_explaination();
//            double offer_cost = list.get(i).getOffer_cost();
//            long display = list.get(i).getOffer_display_date().getTime();
//            long expired = list.get(i).getOffer_expired_date().getTime();
//            long deliver = list.get(i).getOffer_deliver_date().getTime();
//            int company_id = list.get(i).getCompany_id();
//            int count = list.get(i).getOffer_count();
//            CustomCompanyOfferModel test = new CustomCompanyOfferModel(offer_id, image_one, image_two, image_three, image_four, offer_title, offer_explain,
//                    offer_cost, display, expired, deliver, company_id, count);
//            list2.add(i, test);
//        }
        System.out.println(new Date());
        //int images_id = list.get
        return list2;
    }


    public CustomCompanyOfferModel getCompanyOffer(int id) {
        CompanyOfferModel data = jdbcTemplate.queryForObject("SELECT * FROM efaz_company_offer WHERE offer_id=?;", new Object[]{id},
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));

        int offer_id = data.getOffer_id();
        byte[] image_one = imgeRepo.getCompanyOfferOneImage(data.getOffer_images_id());
        byte[] image_two = imgeRepo.getCompanyOfferTwoImage(data.getOffer_images_id());
        byte[] image_three = imgeRepo.getCompanyOfferThreeImage(data.getOffer_images_id());
        byte[] image_four = imgeRepo.getCompanyOfferFourImage(data.getOffer_images_id());
        String offer_title = data.getOffer_title();
        String offer_explain = data.getOffer_explaination();
        double offer_cost = data.getOffer_cost();
        long display = data.getOffer_display_date().getTime();
        long expired = data.getOffer_expired_date().getTime();
        long deliver = data.getOffer_deliver_date().getTime();
        int company_id = data.getCompany_id();
        int count = data.getOffer_count();

        CustomCompanyOfferModel test = new CustomCompanyOfferModel(offer_id, image_one, image_two, image_three, image_four, offer_title, offer_explain,
                offer_cost, display, expired, deliver, company_id, count);

        return test;


    }

    public List<CustomCompanyOfferModel> getCompanyOffers(int id) {


        List<CompanyOfferModel> list = jdbcTemplate.query("SELECT * FROM efaz_company_offer WHERE offer_company_id=?;", new Object[]{id},
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));
        List<CustomCompanyOfferModel> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            int offer_id = list.get(i).getOffer_id();
            byte[] image_one = imgeRepo.getCompanyOfferOneImage(list.get(i).getOffer_images_id());
            byte[] image_two = imgeRepo.getCompanyOfferTwoImage(list.get(i).getOffer_images_id());
            byte[] image_three = imgeRepo.getCompanyOfferThreeImage(list.get(i).getOffer_images_id());
            byte[] image_four = imgeRepo.getCompanyOfferFourImage(list.get(i).getOffer_images_id());
            String offer_title = list.get(i).getOffer_title();
            String offer_explain = list.get(i).getOffer_explaination();
            double offer_cost = list.get(i).getOffer_cost();
            long display = list.get(i).getOffer_display_date().getTime();
            long expired = list.get(i).getOffer_expired_date().getTime();
            long deliver = list.get(i).getOffer_deliver_date().getTime();
            int company_id = list.get(i).getCompany_id();
            int count = list.get(i).getOffer_count();
            CustomCompanyOfferModel test = new CustomCompanyOfferModel(offer_id, image_one, image_two, image_three, image_four, offer_title, offer_explain,
                    offer_cost, display, expired, deliver, company_id, count);
            list2.add(i, test);
        }

        //int images_id = list.get
        return list2;

    }

    public int updateCompanyOfferWithImages(int offer_id, byte[] image_one, byte[] image_two, byte[] image_third, byte[] image_four, String offer_title, String offer_explaination, double offer_cost,
                                            long offer_display_date, long offer_expired_date, long offer_deliver_date, int company_id, int offer_count) {

        int image_id = jdbcTemplate.queryForObject("SELECT offer_image_id FROM efaz_company_offer WHERE offer_id=?", Integer.class, offer_id);
        int res = jdbcTemplate.update("update company_offer_images set image_one=?," +
                "image_two=?, image_three=?," + "image_four=?" + " where images_id=?", image_one, image_two, image_third, image_four, image_id);


        if (res == 1) {
            return jdbcTemplate.update("update efaz_company_offer set offer_image_id=?," +
                            "offer_title=?, offer_explaination=?," + "offer_cost=?, offer_display_date=?, offer_expired_date=?," +
                            "offer_deliver_date=?, offer_company_id=?, offer_count=?" + " where offer_id=?", image_id, offer_title,
                    offer_explaination, offer_cost, new Timestamp(offer_display_date) , new Timestamp(offer_expired_date), new Timestamp(offer_deliver_date), company_id, offer_count, offer_id);
        } else {
            return 0;
        }

    }


    public int deleteCompanyOffer(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        int image_id = jdbcTemplate.queryForObject("SELECT offer_image_id FROM efaz_company_offer WHERE offer_id=?", Integer.class, id);
        jdbcTemplate.update("DELETE FROM company_offer_images WHERE images_id=?", image_id);
        int response = jdbcTemplate.update("DELETE FROM efaz_company_offer WHERE offer_id=?", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        return response;
    }


    public int getCompanyOfferCount(int id) {

        return jdbcTemplate.queryForObject("SELECT count(*) FROM efaz_company_offer WHERE offer_company_id=?;",Integer.class, id);

    }


}
