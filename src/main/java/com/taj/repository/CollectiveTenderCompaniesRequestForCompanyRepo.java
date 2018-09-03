package com.taj.repository;

import com.taj.model.CollectiveTenderCompaniesRequestForCompanyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 9/3/2018.
 */
@Repository
public class CollectiveTenderCompaniesRequestForCompanyRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CollectiveTenderCompaniesRequestForCompanyModel> getTenderCompanies(int id){
        String sql = "SELECT\n" +
                "\tresponse_id,\n" +
                "\tresponsed_cost,\n" +
                "\tcompany_id,\n" +
                "\tcompany_name, " +
                "company_desc, " +
                "\tcompany_logo_image, " +
                "response_date " +
                "FROM\n" +
                "\tefaz_company.takataf_company_request_tender AS req\n" +
                "\tLEFT JOIN efaz_company.efaz_company_profile as profile ON req.response_takataf_company_id = profile.company_id\n" +
                "\tWHERE response_takataf_request_id = ?;";
        return jdbcTemplate.query(sql, new Object[]{id},
                (resultSet, i) -> new CollectiveTenderCompaniesRequestForCompanyModel(resultSet.getInt(1), resultSet.getDouble(2),
                        resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getBytes(6), resultSet.getTimestamp(7).getTime()));
    }

    public int approveRequest(int response_takataf_company_id, int response_takataf_request_id){
           return jdbcTemplate.update("UPDATE efaz_company.takataf_company_request_tender SET is_aproved=1 WHERE response_takataf_company_id=?" +
                   " AND response_takataf_request_id=?", response_takataf_company_id, response_takataf_request_id);
    }
}
