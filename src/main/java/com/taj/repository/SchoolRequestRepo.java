package com.taj.repository;

import com.taj.model.SchoolProfileModel;
import com.taj.model.SchoolRequestsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
@Repository
public class SchoolRequestRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addRequest(int request_id, byte[] request_logo, String request_title, String request_explaination,
            String request_category, Timestamp request_display_date, Timestamp request_expired_date, Timestamp request_deliver_date){
        return  jdbcTemplate.update("INSERT INTO efaz_school_request VALUES (?,?,?,?,?,?,?,?)", request_id, request_logo,
                request_title,request_explaination, request_category, request_display_date, request_expired_date, request_deliver_date);
    }

    public List<SchoolRequestsModel> getRequests(){
        return jdbcTemplate.query("SELECT * FROM efaz_school_request;",
                (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1),resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getTimestamp(6), resultSet.getTimestamp(7), resultSet.getTimestamp(8)));
    }

    public SchoolRequestsModel getRequest(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_school_request WHERE  request_id=?;",
                new Object[]{id},(resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1),resultSet.getBytes(2),
                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                resultSet.getTimestamp(6), resultSet.getTimestamp(7), resultSet.getTimestamp(8)));
    }


    public int updateRequest(int id, byte[] request_logo, String request_title, String request_explaination,
                             String request_category, Timestamp request_display_date, Timestamp request_expired_date, Timestamp request_deliver_date){

        return jdbcTemplate.update("update efaz_school_request set request_logo=?,"+"request_title=?," +
                        "request_explaination=?, request_category=?," +
                        "request_display_date=?, request_expired_date=?, request_deliver_date=?"+
                        " where request_id=?", request_logo,request_title, request_explaination, request_category, request_display_date
                , request_expired_date, request_deliver_date, id);

    }

    public int deleteSchoolRequest(int id){
        return jdbcTemplate.update("DELETE FROM efaz_school_request WHERE request_id=?", id);
    }


}
