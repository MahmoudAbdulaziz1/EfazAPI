package com.taj.repository;

import com.taj.model.retail_collective.RetailGetAllModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 10/2/2018.
 */
@Repository
public class RetailRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public int retailAddRequest(int retail,
                                int retail_school_id,
                                int retail_tender_id) {
        return jdbcTemplate.update("INSERT INTO efaz_company.takataf_retail_school_request VALUES(?,?,?,?);",
                null, retail, retail_school_id, retail_tender_id);
    }


    public int confirmRetailRequest(
            int retail_school_id,
            int retail_tender_id) {
        int res = jdbcTemplate.update("DELETE FROM  efaz_company.takatf_request_tender WHERE  request_school_id=? AND request_tender_id=?;",
                retail_school_id, retail_tender_id);
        if (res == 1) {
            jdbcTemplate.update("DELETE FROM  efaz_company.takataf_request_cat_count WHERE  scool_id=? AND tend_id=?;",
                    retail_school_id, retail_tender_id);

            return jdbcTemplate.update("DELETE FROM  efaz_company.takataf_retail_school_request WHERE  retail_school_id=? AND retail_tender_id=?;",
                    retail_school_id, retail_tender_id);

        } else {
            return 0;
        }

    }

    public int removeRetailRequest(
            int retail_school_id,
            int retail_tender_id) {

        return jdbcTemplate.update("DELETE FROM  efaz_company.takataf_retail_school_request WHERE  retail_school_id=? AND retail_tender_id=?;",
                retail_school_id, retail_tender_id);

    }


    public List<RetailGetAllModel> getRetailTenders() {
        String sql = "SELECT\n" +
                "\ttender_id,\n" +
                "\ttender_logo,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\tIFNULL( tender_company_display_date, '1970-01-01 02:00:00' ) AS tender_company_display_date,\n" +
                "\tIFNULL( tender_company_expired_date, '1970-01-01 02:00:00' ) AS tender_company_expired_date,\n" +
                "\tcount(DISTINCT req.request_id ) AS response_count,\n" +
                "count( DISTINCT ten.t_category_id ) AS cat_count, retail_school_id AS school_id \t\n" +
                "FROM\n" +
                "\tefaz_company.takataf_retail_school_request AS retail\n" +
                "\tLEFT JOIN efaz_company.takatf_tender AS tender ON retail.retail_tender_id = tender.tender_id\n" +
                "\tLEFT JOIN efaz_company.takatf_request_tender AS req ON req.request_tender_id = tender.tender_id \n" +
                "\tLEFT JOIN efaz_company.tkatf_tender_catgory_request AS ten ON tender_id = ten.t_tender_id\n" +
                "\t\n" +
                "GROUP BY\n" +
                "\ttender_id,\n" +
                "\ttender_logo,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\ttender_company_display_date,\n" +
                "\ttender_company_expired_date, retail_school_id;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new RetailGetAllModel(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getTimestamp(6).getTime()
                        , resultSet.getTimestamp(7).getTime(),
                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10)));
    }


    public List<Map<String, Object>> getTenderRequestObjectWithCompanyDates(int school_id, int tender_id) {
        String sql = "SELECT\n" +
                "\ttender_id,\n" +
                "\ttender_logo,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\tIFNULL( tender_company_display_date,  STR_TO_DATE('1970-01-01 02:00:00','%Y-%m-%d %H:%i:%s') ) AS tender_company_display_date,\n" +
                "\tIFNULL( tender_company_expired_date,  STR_TO_DATE('1970-01-01 02:00:00','%Y-%m-%d %H:%i:%s') ) AS tender_company_expired_date,\n" +
                "\tcount( DISTINCT req.request_school_id ) AS response_count,\n" +
                "\tcategory_id,\n" +
                "\tcategory_name,\n" +
                "\tcount,\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image \n" +
                "FROM\n" +
                "\tefaz_company.takataf_retail_school_request AS retail\n" +
                "\tLEFT JOIN efaz_company.takatf_tender AS tender ON retail.retail_tender_id = tender.tender_id\n" +
                "\tLEFT JOIN efaz_company.takatf_request_tender AS req ON req.request_tender_id = tender.tender_id\n" +
                "\tLEFT JOIN efaz_company.takataf_request_cat_count AS tr ON tender_id = tr.tend_id \n" +
                "\tAND tr.scool_id = retail.retail_school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id\n" +
                "\tLEFT JOIN efaz_company.tkatf_tender_catgory_request AS ten ON tender_id = ten.t_tender_id\n" +
                "\tINNER JOIN efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id \n" +
                "WHERE\n" +
                "\tretail_school_id = ? \n" +
                "\tAND retail_tender_id = ? \n" +
                "GROUP BY\n" +
                "\ttender_id,\n" +
                "\ttender_logo,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\ttender_company_display_date,\n" +
                "\ttender_company_expired_date,\n" +
                "\tcategory_id,\n" +
                "\tcategory_name,\n" +
                "\tcount,\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image;";
        return jdbcTemplate.queryForList(sql, new Object[]{school_id, tender_id});

    }


}
