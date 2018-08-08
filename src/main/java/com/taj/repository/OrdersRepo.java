package com.taj.repository;

import com.taj.model.CompanyBackOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by User on 8/6/2018.
 */
@Repository
public class OrdersRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ResponseEntity<List<CompanyBackOrderModel>> getOrdersForCompany(int companyId){

        String sql = "SELECT company_id, request_offer_count, offer_title, offer_cost " +
                "FROM ((efaz_company.efaz_company_offer AS offer INNER JOIN efaz_company.efaz_school_request_offer AS company " +
                "ON company.requsted_offer_id = offer.offer_id) " +
                "INNER JOIN efaz_company.efaz_company_profile AS profiles ON offer.offer_company_id = profiles.company_id) WHERE offer.offer_company_id=?;";
        List<CompanyBackOrderModel> res = jdbcTemplate.query(sql, new Object[]{companyId},
                (resultSet, i) -> new CompanyBackOrderModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4)));

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
