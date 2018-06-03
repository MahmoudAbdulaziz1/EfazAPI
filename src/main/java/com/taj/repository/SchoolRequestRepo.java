package com.taj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

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


}
