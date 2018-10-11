package com.taj.repository;

import com.taj.model.CompanyRequestCollectiveTenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 9/2/2018.
 */
@Repository
public class CompanyRequestCollectiveTenderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addRequest(int response_takataf_company_id, int response_takataf_request_id,
                          double responsed_cost, int responsed_from, int responsed_to, String response_desc) {
        if (!isExist(response_takataf_company_id, response_takataf_request_id)) {
            return jdbcTemplate.update("INSERT INTO efaz_company.takataf_company_request_tender VALUES (?,?,?,?,?,?,?,?,?,?)", null,
                    response_takataf_company_id, response_takataf_request_id, responsed_cost, 0, new Timestamp(System.currentTimeMillis()),
                    responsed_from, responsed_to, response_desc, new Timestamp(System.currentTimeMillis()));
        } else {
            return jdbcTemplate.update("UPDATE efaz_company.takataf_company_request_tender SET responsed_cost=?, response_desc=? , updated_date=? " +
                            " WHERE response_takataf_company_id=? AND response_takataf_request_id=?;", responsed_cost, response_desc,
                    new Timestamp(System.currentTimeMillis()), response_takataf_company_id, response_takataf_request_id);
        }
    }

    public List<CompanyRequestCollectiveTenderModel> getAll() {
        return jdbcTemplate.query("SELECT * FROM efaz_company.takataf_company_request_tender;",
                (resultSet, i) -> new CompanyRequestCollectiveTenderModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getDouble(4), resultSet.getInt(5), resultSet.getLong(6), resultSet.getInt(7), resultSet.getInt(8),
                        resultSet.getString(9), resultSet.getTimestamp(10).getTime()));
    }

    public boolean isExist(int response_takataf_company_id, int response_takataf_request_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.takataf_company_request_tender WHERE response_takataf_company_id=? AND response_takataf_request_id=?;",
                Integer.class, response_takataf_company_id, response_takataf_request_id);
        return cnt != null && cnt > 0;
    }
}
