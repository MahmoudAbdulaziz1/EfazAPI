package com.taj.repository;

import com.taj.model.CompanyRequestCollectiveTenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Created by User on 9/2/2018.
 */
@Repository
public class CompanyRequestCollectiveTenderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addRequest(int response_takataf_company_id, int response_takataf_request_id,
                                                          double responsed_cost, int is_aproved, long response_date, int responsed_from, int responsed_to){
        return jdbcTemplate.update("INSERT INTO efaz_company.takataf_company_request_tender VALUES (?,?,?,?,?,?,?,?)", null,
                response_takataf_company_id, response_takataf_request_id, responsed_cost, is_aproved, new Timestamp(response_date), responsed_from, responsed_to);
    }
}
