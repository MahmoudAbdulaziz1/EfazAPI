package com.taj.repository;

import com.taj.model.CompanyResponseSchoolRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@Repository
public class CompanyResponseSchoolRequestRepo {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean checkIfExist(int responsed_company_id, int responsed_request_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_company.efaz_company_response_school_request WHERE responsed_company_id=? AND responsed_request_id=?;",
                Integer.class, responsed_company_id, responsed_request_id);
        return cnt != null && cnt > 0;
    }

    public boolean checkIfExistByDate(int responsed_company_id, int responsed_request_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_company.efaz_company_response_school_request WHERE responsed_company_id=?" +
                        " AND responsed_request_id=? AND response_date< NOW()+ INTERVAL 3 DAY;",
                Integer.class, responsed_company_id, responsed_request_id);
        return cnt != null && cnt > 0;
    }

    public int addResponseSchoolRequest( int responsed_company_id, int responsed_request_id, int responsed_from,
                                         int responsed_to, double responsed_cost, int is_aproved, String resonse_desc) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (checkIfExist(responsed_company_id, responsed_request_id)){
            if (checkIfExistByDate(responsed_company_id, responsed_request_id)){
                int updateResponse = jdbcTemplate.update("UPDATE efaz_company.efaz_company_response_school_request set responsed_cost=?, response_desc=? " +
                        " WHERE responsed_company_id=? AND responsed_request_id=?; ", responsed_cost, resonse_desc, responsed_company_id, responsed_request_id);
                if (updateResponse ==1){
                    return 100;
                }else {
                    return 0;
                }
            }else {
                return -100;
            }

        }else {
            int insertResponse = jdbcTemplate.update("INSERT INTO efaz_company_response_school_request VALUES (?,?,?,?,?,?,?,?,?)", null, responsed_company_id, responsed_request_id,
                    responsed_cost, responsed_to, responsed_from, 0, timestamp, resonse_desc);
            if (insertResponse == 1){
                return 200;
            }else {
                return 0;
            }
        }

    }

    public List<CompanyResponseSchoolRequestModel> getResponseSchoolRequest() {
        return jdbcTemplate.query("SELECT * FROM efaz_company_response_school_request;",
                ((resultSet, i) -> new CompanyResponseSchoolRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7), resultSet.getTimestamp(8).getTime(),resultSet.getString(9))));
    }

    public CompanyResponseSchoolRequestModel getResponseSchoolRequest(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company_response_school_request WHERE response_id=?;", new Object[]{id},
                ((resultSet, i) -> new CompanyResponseSchoolRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7), resultSet.getTimestamp(8).getTime(),resultSet.getString(9))));
    }


    public List<CompanyResponseSchoolRequestModel> getResponseSchoolRequestByCompany(int companyId) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_response_school_request WHERE responsed_company_id=?;", new Object[]{companyId},
                ((resultSet, i) -> new CompanyResponseSchoolRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7), resultSet.getTimestamp(8).getTime(),resultSet.getString(9))));
    }

    public List<CompanyResponseSchoolRequestModel> getResponseSchoolRequestByRequest(int requestId) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_response_school_request WHERE responsed_request_id=?;", new Object[]{requestId},
                ((resultSet, i) -> new CompanyResponseSchoolRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7), resultSet.getTimestamp(8).getTime(),resultSet.getString(9))));
    }

    public List<CompanyResponseSchoolRequestModel> getResponseSchoolRequestByAccept(int acceptId) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_response_school_request WHERE is_aproved=?;", new Object[]{acceptId},
                ((resultSet, i) -> new CompanyResponseSchoolRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7), resultSet.getTimestamp(8).getTime(),resultSet.getString(9))));
    }

    public int acceptResponseSchoolRequest(int response_id) {
        jdbcTemplate.update("UPDATE efaz_company_response_school_request SET is_aproved=1 WHERE response_id=?", response_id);
        return jdbcTemplate.update("UPDATE efaz_company_response_school_request SET is_aproved=1 WHERE response_id=?", response_id);
    }

    public int refuseResponseSchoolRequest(int response_id) {
        return jdbcTemplate.update("UPDATE efaz_company_response_school_request SET is_aproved=0 WHERE response_id=?", response_id);
    }

    public int updateResponseSchoolRequest(int response_id, int responsed_company_id, int responsed_request_id, int responsed_from, int responsed_to, double responsed_cost, int is_aproved, String response_desc) {
        return jdbcTemplate.update("UPDATE efaz_company_response_school_request SET responsed_company_id=?, responsed_request_id=?,responsed_from=?, responsed_to=?," +
                        "responsed_cost=?, is_aproved=?, response_desc=? WHERE response_id=?", responsed_company_id, responsed_request_id,
                responsed_from, responsed_to, responsed_cost, is_aproved, response_desc, response_id);
    }

    public int deleteResponseSchoolRequest(int seen_id) {
        return jdbcTemplate.update("DELETE FROM efaz_company_response_school_request WHERE response_id=?", seen_id);
    }
}
