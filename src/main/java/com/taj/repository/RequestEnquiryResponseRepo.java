package com.taj.repository;

import com.taj.model.RequestEnquiryResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@Repository
public class RequestEnquiryResponseRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addNewResponse(int requestId, String message) {
        return jdbcTemplate.update("INSERT INTO request_enquiries_response VALUES (?,?,?);", null, requestId, message);
    }

    public List<RequestEnquiryResponseModel> getNewResponses() {
        return jdbcTemplate.query("SELECT * FROM request_enquiries_response;",
                ((resultSet, i) -> new RequestEnquiryResponseModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3))));
    }

    public List<RequestEnquiryResponseModel> getNewResponses(int schoolRequestId) {
        return jdbcTemplate.query("SELECT * FROM request_enquiries_response WHERE request_enquiry_id=?;", new Object[]{schoolRequestId},
                ((resultSet, i) -> new RequestEnquiryResponseModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3))));
    }


    public RequestEnquiryResponseModel getNewResponse(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM request_enquiries_response WHERE response_id=?;", new Object[]{id},
                ((resultSet, i) -> new RequestEnquiryResponseModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3))));
    }

    public int updateNewResponse(int id, int requestId, String message) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        jdbcTemplate.update("UPDATE request_enquiries_response SET request_enquiry_id=?, response_message=? WHERE response_id=?",
                requestId, message, id);
        return jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
    }

    public int deleteNewResponse(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        jdbcTemplate.update("DELETE FROM request_enquiries_response WHERE response_id=?", id);
        return jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
    }

}
