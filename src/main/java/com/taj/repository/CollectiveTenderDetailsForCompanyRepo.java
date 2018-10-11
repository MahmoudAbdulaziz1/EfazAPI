package com.taj.repository;

import com.taj.model.CollectiveTenderDetailsForCompanyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 9/3/2018.
 */
@Repository
public class CollectiveTenderDetailsForCompanyRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CollectiveTenderDetailsForCompanyModel> getTenderDetails(int id) {
        String sql = "SELECT\n" +
                " tender_id, tender_logo, \n" +
                " tender_title,\n" +
                " tender_explain,\n" +
                " tender_company_display_date,\n" +
                " tender_company_expired_date,\n" +
                " COUNT( DISTINCT request_id ) AS response_count,\n" +
                " tr.cat_id,\n" +
                " IFNULL( category_name, 0 ) AS category_name,\n" +
                " SUM( DISTINCT tr2.count ) AS sum \n" +
                " FROM\n" +
                " takatf_tender AS t\n" +
                " LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id\n" +
                " LEFT JOIN efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id\n" +
                " LEFT JOIN efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id\n" +
                " LEFT JOIN efaz_company.takataf_request_cat_count AS tr2 ON tr.cat_id = tr2.cat_id \n" +
                " AND tr2.tend_id = ? \n" +
                " WHERE\n" +
                " t.tender_id = ? \n" +
                " GROUP BY\n" +
                " tr.cat_id," +
                " tender_id,\n" +
                " tender_title,\n" +
                " tender_explain,\n" +
                " tender_company_display_date,\n" +
                " tender_company_expired_date," +
                "category_name;";

        return jdbcTemplate.query(sql, new Object[]{id, id},
                (resultSet, i) -> new CollectiveTenderDetailsForCompanyModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getTimestamp(5).getTime(), resultSet.getTimestamp(6).getTime(),
                        resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getInt(10)));
    }
}
