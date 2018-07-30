package com.taj.repository;

import com.taj.model.AddOfferImage;
import com.taj.model.CompanyOfferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 7/30/2018.
 */
@Repository
public class AddOfferImageRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkIfExist(int images_id){
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  company_offer_images WHERE images_id=?;",
                Integer.class, images_id);
        return cnt != null && cnt > 0;
    }

    public boolean checkIfOfferExist(int offer_id){
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  company_offer_images WHERE offer_id=?;",
                Integer.class, offer_id);
        return cnt != null && cnt > 0;
    }



    public int addOfferImage(byte[] image_one, byte[] image_two, byte[] image_three, byte[] image_four, int offer_id) {
        return jdbcTemplate.update("INSERT INTO company_offer_images VALUES (?,?,?,?,?,?)", null, image_one, image_two, image_three, image_four, offer_id);
    }

    public List<AddOfferImage> getOfferImages() {
        return jdbcTemplate.query("SELECT * FROM company_offer_images;",
                (resultSet, i) -> new AddOfferImage(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3)
                        , resultSet.getBytes(4), resultSet.getBytes(5), resultSet.getInt(6)));
    }

    public List<AddOfferImage> getCompanyOfferImages(int offer_id) {
        return jdbcTemplate.query("SELECT * FROM company_offer_images WHERE offer_id=?;", new Object[]{offer_id},
                (resultSet, i) -> new AddOfferImage(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3)
                        , resultSet.getBytes(4), resultSet.getBytes(5), resultSet.getInt(6)));
    }

    public AddOfferImage getCompanyOfferImage(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM company_offer_images WHERE images_id=?;", new Object[]{id},
                (resultSet, i) -> new AddOfferImage(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3)
                        , resultSet.getBytes(4), resultSet.getBytes(5), resultSet.getInt(6)));
    }

    public int updateCompanyOfferImages(int images_id, byte[] image_one, byte[] image_two, byte[] image_third, byte[] image_four, int offer_id) {
        return jdbcTemplate.update("update company_offer_images set image_one=?," +
                        "image_two=?, image_three=?," + "image_four=?, offer_id=? " + " where images_id=?",
                image_one, image_two, image_third, image_four, offer_id, images_id);
    }

    public int deleteCompanyOffer(int id) {
        return jdbcTemplate.update("DELETE FROM company_offer_images WHERE images_id=?", id);
    }

}
