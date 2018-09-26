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

    public List<Map<String, Object>> getTenderRequestObject(int id) {
        String sql = "SELECT  " +
                "    tender_id, " +
                "    tender_title, " +
                "    tender_explain, " +
                "    tender_display_date, " +
                "    tender_expire_date, " +
                "    COUNT(DISTINCT request_id) AS response_count, " +
                "     id, " +
                "    category_name, " +
                "     count, " +
                "    IFNULL(school_id, 0) AS school_id, t_date," +
                "    IFNULL(school_name, 0) AS school_name, " +
                "    IFNULL(school_logo_image, 0) AS school_logo_image " +
                " FROM " +
                "    takatf_tender AS t " +
                "        LEFT JOIN " +
                "    efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id " +
                "        LEFT JOIN " +
                "    efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id AND tr.count!=0" +
                "        LEFT JOIN " +
                "    efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id " +
                "        LEFT JOIN " +
                "    efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id " +
                " WHERE " +
                "    t.tender_id = ? " +
                " GROUP BY tr.id, tender_id,tender_title,tender_explain,tender_display_date,tender_expire_date," +
                "id, category_name,school_id,t_date,school_name,school_logo_image";

        String sql2 = "SELECT\n" +
                "\ttender_id,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\tCOUNT( DISTINCT request_id ) AS response_count,\n" +
                "\tid,\n" +
                "\tcategory_name,\n" +
                "\tcount,\n" +
                "\tIFNULL( school_id, 0 ) AS school_id,\n" +
                "\tt_date,\n" +
                "\tIFNULL( school_name, 0 ) AS school_name,\n" +
                "\tIFNULL( school_logo_image, 0 ) AS school_logo_image \n" +
                "FROM\n" +
                "\ttakatf_tender AS t\n" +
                "\tLEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id\n" +
                "\tLEFT JOIN efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id \n" +
                "WHERE\n" +
                "\tt.tender_id = ? \n" +
                "GROUP BY\n" +
                "\ttr.id, tender_id,\n" +
                "                tender_title,                tender_explain,\n" +
                "                tender_display_date,\n" +
                "                \ttender_expire_date," +
                " " +
                "\tcategory_name,\n" +
                "\tcount,\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image;";
        String sql3 = "SELECT\n" +
                "\ttender_id,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\t( SELECT COUNT( DISTINCT request_school_id ) FROM takatf_request_tender ) AS response_count,\n" +
                "\tid,\n" +
                "\tcategory_name,\n" +
                "\tcount,\n" +
                "\tIFNULL( school_id, 0 ) AS school_id,\n" +
                "\t( SELECT DISTINCT t_date FROM takatf_request_tender WHERE request_tender_id = tender_id AND request_school_id = school_id ) AS t_date,\n" +
                "\tIFNULL( school_name, 0 ) AS school_name,\n" +
                "\tschool_logo_image \n" +
                "FROM\n" +
                "\ttakatf_tender AS t\n" +
                "\tLEFT JOIN efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id \n" +
                "WHERE\n" +
                "\tt.tender_id = ?;";


        return jdbcTemplate.queryForList(sql3, new Object[]{id});

    }


    public List<Map<String, Object>> getTenderRequestObjectWithCompanyDates(int id) {

        String sql3 = "SELECT\n" +
                "\ttender_id,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_company_display_date,\n" +
                "\ttender_company_expired_date,\n" +
                "\ttender_expire_date,\n" +
                "\t( SELECT COUNT( DISTINCT request_school_id ) FROM takatf_request_tender ) AS response_count,\n" +
                "\tid,\n" +
                "\tcategory_name,\n" +
                "\tcount,\n" +
                "\tIFNULL( school_id, 0 ) AS school_id,\n" +
                "\t( SELECT DISTINCT t_date FROM takatf_request_tender WHERE request_tender_id = tender_id AND request_school_id = school_id ) AS t_date,\n" +
                "\tIFNULL( school_name, 0 ) AS school_name,\n" +
                "\tschool_logo_image \n" +
                "FROM\n" +
                "\ttakatf_tender AS t\n" +
                "\tLEFT JOIN efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id \n" +
                "WHERE\n" +
                "\tt.tender_id = ?;";


        return jdbcTemplate.queryForList(sql3, new Object[]{id});

    }


}
