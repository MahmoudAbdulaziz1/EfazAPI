package com.taj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by User on 9/25/2018.
 */
@Repository
public class AddSchoolRequestImagesRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

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


}
