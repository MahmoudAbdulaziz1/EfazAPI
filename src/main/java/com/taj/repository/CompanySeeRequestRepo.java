package com.taj.repository;

import com.taj.model.CompanySeeRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@Repository
public class CompanySeeRequestRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addCompanySeeRequest(int requestId, int companyId) {
        return jdbcTemplate.update("INSERT INTO efaz_company_see_request VALUES (?,?,?);", null, requestId, companyId);
    }

    public List<CompanySeeRequestModel> getCompanySeeRequests() {
        return jdbcTemplate.query("SELECT * FROM efaz_company_see_request;",
                ((resultSet, i) -> new CompanySeeRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public List<CompanySeeRequestModel> getCompanySeeRequestsByCompanyId(int companyId) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_see_request WHERE request_company_id=?;", new Object[]{companyId},
                ((resultSet, i) -> new CompanySeeRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public List<CompanySeeRequestModel> getCompanySeeRequestsByRequestId(int requestId) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_see_request WHERE request_id=?;", new Object[]{requestId},
                ((resultSet, i) -> new CompanySeeRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public List<CompanySeeRequestModel> getCompanySeeRequestsByRequestIdANDCompanyId(int requestId, int companyId) {
        return jdbcTemplate.query("SELECT * FROM efaz_company_see_request WHERE request_id=? AND request_company_id=?;", new Object[]{requestId, companyId},
                ((resultSet, i) -> new CompanySeeRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public CompanySeeRequestModel getCompanySeeRequest(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company_see_request WHERE seen_id=?;", new Object[]{id},
                ((resultSet, i) -> new CompanySeeRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public int updateCompanySeeRequest(int id, int companyId, int requestId) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        jdbcTemplate.update("UPDATE efaz_company_see_request SET request_company_id=?, request_id=? WHERE seen_id=?",
                companyId, requestId, id);
        return jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
    }

    public int deleteCompanySeeRequest(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        int res = jdbcTemplate.update("DELETE FROM efaz_company_see_request WHERE seen_id=?", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        return res;
    }

    public boolean isExist(int companyId) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.efaz_company_see_request WHERE request_company_id=? ;",
                Integer.class, companyId);
        return cnt != null && cnt > 0;
    }

    public boolean isExistCompany(int companyId) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.efaz_company_profile WHERE company_id=? ;",
                Integer.class, companyId);
        return cnt != null && cnt > 0;
    }
    public boolean isExistRequest(int requestId) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.efaz_school_tender WHERE request_id=? ;",
                Integer.class, requestId);
        return cnt != null && cnt > 0;
    }


}
