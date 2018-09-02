package com.taj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 8/30/2018.
 */
@Repository
public class TenderRequestRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> getTenderRequestObject(int id) {
        String sql = "SELECT  " +
                "    tender_id, " +
                "    tender_title, " +
                "    tender_explain, " +
                "    tender_display_date, " +
                "    tender_expire_date, " +
                "    COUNT(DISTINCT request_id) AS response_count, " +
                "    IFNULL(id, 0) AS id, " +
                "    IFNULL(category_name, 0) AS category_name, " +
                "    IFNULL(count, 0) AS count, " +
                "    IFNULL(school_id, 0) AS school_id, t_date," +
                "    IFNULL(school_name, 0) AS school_name, " +
                "    IFNULL(school_logo_image, 0) AS school_logo_image " +
                " FROM " +
                "    takatf_tender AS t " +
                "        LEFT JOIN " +
                "    efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id " +
                "        LEFT JOIN " +
                "    efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id " +
                "        LEFT JOIN " +
                "    efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id " +
                "        LEFT JOIN " +
                "    efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id " +
                " WHERE " +
                "    t.tender_id = ? " +
                " GROUP BY tr.id, tender_id,tender_title,tender_explain,tender_display_date,tender_expire_date," +
                "id, category_name,school_id,t_date,school_name,school_logo_image";


        return  jdbcTemplate.queryForList(sql, new Object[]{id});

    }
}
