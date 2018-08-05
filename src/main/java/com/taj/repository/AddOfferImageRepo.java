package com.taj.repository;

import com.taj.model.AddOfferImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by User on 7/30/2018.
 */
@Repository
public class AddOfferImageRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkIfExist(int images_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  company_offer_images WHERE images_id=?;",
                Integer.class, images_id);
        return cnt != null && cnt > 0;
    }

//    public boolean checkIfOfferExist(int offer_id){
//        Integer cnt = jdbcTemplate.queryForObject(
//                "SELECT count(*) FROM  company_offer_images WHERE offer_id=?;",
//                Integer.class, offer_id);
//        return cnt != null && cnt > 0;
//    }


    public int addOfferImage(byte[] image_one, byte[] image_two, byte[] image_three, byte[] image_four) {


        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement("INSERT INTO company_offer_images VALUES (?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, null);
                ps.setBytes(2, image_one);
                ps.setBytes(3, image_two);/////////
                ps.setBytes(4, image_three);
                ps.setBytes(5, image_four);
                return ps;
            }

        }, key);

        return key.getKey().intValue();


        //return jdbcTemplate.update("INSERT INTO company_offer_images VALUES (?,?,?,?,?,?)", null, image_one, image_two, image_three, image_four, offer_id);
    }

    public List<AddOfferImage> getOfferImages() {
        return jdbcTemplate.query("SELECT * FROM company_offer_images;",
                (resultSet, i) -> new AddOfferImage(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3)
                        , resultSet.getBytes(4), resultSet.getBytes(5)));
    }

    public List<AddOfferImage> getCompanyOfferImages(int offer_id) {
        return jdbcTemplate.query("SELECT * FROM company_offer_images WHERE offer_id=?;", new Object[]{offer_id},
                (resultSet, i) -> new AddOfferImage(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3)
                        , resultSet.getBytes(4), resultSet.getBytes(5)));
    }


    public AddOfferImage getCompanyOfferImage(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM company_offer_images WHERE images_id=?;", new Object[]{id},
                (resultSet, i) -> new AddOfferImage(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3)
                        , resultSet.getBytes(4), resultSet.getBytes(5)));
    }

    public byte[] getCompanyOfferOneImage(int id) {
        return jdbcTemplate.queryForObject("SELECT image_one FROM company_offer_images WHERE images_id=?;", byte[].class, id);
    }

    public byte[] getCompanyOfferTwoImage(int id) {
        return jdbcTemplate.queryForObject("SELECT image_two FROM company_offer_images WHERE images_id=?;", byte[].class, id);
    }

    public byte[] getCompanyOfferThreeImage(int id) {
        return jdbcTemplate.queryForObject("SELECT image_three FROM company_offer_images WHERE images_id=?;", byte[].class, id);
    }

    public byte[] getCompanyOfferFourImage(int id) {
        return jdbcTemplate.queryForObject("SELECT image_four FROM company_offer_images WHERE images_id=?;", byte[].class, id);
    }


    public int updateCompanyOfferImages(int images_id, byte[] image_one, byte[] image_two, byte[] image_third, byte[] image_four) {
        return jdbcTemplate.update("update company_offer_images set image_one=?," +
                        "image_two=?, image_three=?," + "image_four=? " + " where images_id=?",
                image_one, image_two, image_third, image_four, images_id);
    }

    public int deleteCompanyOffer(int id) {
        return jdbcTemplate.update("DELETE FROM company_offer_images WHERE images_id=?", id);
    }

}
