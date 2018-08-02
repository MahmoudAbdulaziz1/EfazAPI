package com.taj.repository;

import com.taj.model.AddOfferImage;
import com.taj.model.SchoolRequstImagesModel;
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
 * Created by User on 8/2/2018.
 */
@Repository
public class SchoolRequstImagesRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkIfExist(int images_id){
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  school_requst_images WHERE image_id=?;",
                Integer.class, images_id);
        return cnt != null && cnt > 0;
    }

//    public boolean checkIfOfferExist(int offer_id){
//        Integer cnt = jdbcTemplate.queryForObject(
//                "SELECT count(*) FROM  company_offer_images WHERE offer_id=?;",
//                Integer.class, offer_id);
//        return cnt != null && cnt > 0;
//    }



    public int addRequestImage(byte[] image_one, byte[] image_two, byte[] image_three, byte[] image_four) {



        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement("INSERT INTO school_requst_images VALUES (?,?,?,?,?)",
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

    public List<SchoolRequstImagesModel> getRequestImages() {
        return jdbcTemplate.query("SELECT * FROM school_requst_images;",
                (resultSet, i) -> new SchoolRequstImagesModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3)
                        , resultSet.getBytes(4), resultSet.getBytes(5)));
    }

    public List<SchoolRequstImagesModel> getShcoolRequestImages(int request_id) {
        return jdbcTemplate.query("SELECT * FROM company_offer_images WHERE offer_id=?;", new Object[]{request_id},
                (resultSet, i) -> new SchoolRequstImagesModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3)
                        , resultSet.getBytes(4), resultSet.getBytes(5)));
    }

    public SchoolRequstImagesModel getSchoolRequestImagesById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM school_requst_images WHERE image_id=?;", new Object[]{id},
                (resultSet, i) -> new SchoolRequstImagesModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3)
                        , resultSet.getBytes(4), resultSet.getBytes(5)));
    }

    public int updateSchoolRequestImages(int image_id, byte[] image_one, byte[] image_two, byte[] image_three, byte[] image_four) {
        return jdbcTemplate.update("update school_requst_images set image_one=?," +
                        "image_two=?, images_three=?," + "image_four=? " + " where image_id=?",
                image_one, image_two, image_three, image_four, image_id);
    }

    public int deleteSchoolRequest(int id) {
        return jdbcTemplate.update("DELETE FROM school_requst_images WHERE image_id=?", id);
    }

}
