package com.taj.repository;

import com.taj.model.TakatafSchoolSeeTenderModel;
import com.taj.model.TakatafTenderRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@Repository
public class TakatafTenderRequestRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public int add$Request(int request_school_id, int request_tender_id, int is_aproved) {
        return jdbcTemplate.update("INSERT INTO takatf_request_tender VALUES (?,?,?,?)", null, request_school_id, request_tender_id, is_aproved);
    }

    public List<TakatafTenderRequestModel> getTenderRequests(){
        return jdbcTemplate.query("SELECT * FROM takatf_request_tender;",
                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4))));
    }

    public TakatafTenderRequestModel getTenderRequest(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM takatf_request_tender WHERE request_id=?;", new Object[]{id},
                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4))));
    }


    public List<TakatafTenderRequestModel> getTenderRequestsBySchool(int schoolId){
        return jdbcTemplate.query("SELECT * FROM takatf_request_tender WHERE request_school_id=?;", new Object[]{schoolId},
                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4))));
    }

    public List<TakatafTenderRequestModel> getTenderRequestsByTender(int tenderId){
        return jdbcTemplate.query("SELECT * FROM takatf_request_tender WHERE request_tender_id=?;", new Object[]{tenderId},
                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4))));
    }

    public List<TakatafTenderRequestModel> getTenderRequestsByAprove(int aprove){
        return jdbcTemplate.query("SELECT * FROM takatf_request_tender WHERE is_aproved=?;", new Object[]{aprove},
                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4))));
    }

    public int acceptTenderRequest(int seen_id) {
        return jdbcTemplate.update("UPDATE takatf_request_tender SET is_aproved=1 WHERE request_id=?", seen_id);
    }
    public int refuseTenderRequest(int seen_id) {
        return jdbcTemplate.update("UPDATE takatf_request_tender SET is_aproved=0 WHERE request_id=?", seen_id);
    }

    public int updateTenderRequest(int seen_id, int seen_school_id, int seen_tender_id, int is_aproved) {
        return jdbcTemplate.update("UPDATE takatf_request_tender SET request_school_id=?, request_tender_id=?, is_aproved=? WHERE request_id=?", seen_school_id, seen_tender_id, is_aproved, seen_id);
    }

    public int deleteTenderRequest(int seen_id) {
        return jdbcTemplate.update("DELETE FROM takatf_request_tender WHERE request_id=?", seen_id);
    }

}
