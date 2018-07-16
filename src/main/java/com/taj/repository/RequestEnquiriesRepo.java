package com.taj.repository;

import com.taj.model.RequestEnquiriesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@Repository
public class RequestEnquiriesRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addNewEnquiry(int requestId, String message) {
        return jdbcTemplate.update("INSERT INTO request_enquiries VALUES (?,?,?);", null, requestId, message);
    }

    public List<RequestEnquiriesModel> getNewEnquiries() {
        return jdbcTemplate.query("SELECT * FROM request_enquiries;",
                ((resultSet, i) -> new RequestEnquiriesModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3))));
    }

    public List<RequestEnquiriesModel> getNewEnquiries(int schoolRequestId) {
        return jdbcTemplate.query("SELECT * FROM request_enquiries WHERE school_request_id=?;", new Object[]{schoolRequestId},
                ((resultSet, i) -> new RequestEnquiriesModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3))));
    }


    public RequestEnquiriesModel getNewEnquiry(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM request_enquiries WHERE enquiry_id=?;", new Object[]{id},
                ((resultSet, i) -> new RequestEnquiriesModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3))));
    }

    public int updateNewEnquiry(int id, int requestId, String message) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        jdbcTemplate.update("UPDATE request_enquiries SET school_request_id=?, enquiry_message=? WHERE enquiry_id=?",
                requestId, message, id);
        return jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
    }

    public int deleteNewEnquiry(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        jdbcTemplate.update("DELETE FROM request_enquiries WHERE enquiry_id=?", id);
        return jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
    }


}
