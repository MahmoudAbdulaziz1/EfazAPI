package com.taj.repository;

import com.taj.model.SchoolRequestNewDtoWitCompany;
import com.taj.model.TakatafTenderNewModel;
import com.taj.model.TakatafTenderWithCompanies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 8/19/2018.
 */
@Repository
public class TakatafTenderNewRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTender(byte[] tender_logo, String tender_title, String tender_explain, long tender_display_date, long tender_expire_date,
                         String tender_cat_id) {
        int category = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                Integer.class, "%" + tender_cat_id + "%");

        return jdbcTemplate.update("INSERT INTO takatf_tender VALUES (?,?,?,?,?,?,?,?,?)", null, tender_logo, tender_title, tender_explain,
                new Timestamp(tender_display_date), new Timestamp(tender_expire_date), 0, 1, category);
    }


    public List<TakatafTenderNewModel> getTenders() {
        String sql = "SELECT tender_id, tender_logo, tender_title, tender_explain, category_name, tender_display_date, tender_expire_date ," +
                " count(distinct request_id) AS response_count " +
                "                FROM takatf_tender AS t INNER JOIN efaz_company.efaz_company_category as cat ON t.tender_cat_id = cat.category_id " +
                "                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id GROUP BY tender_id; ";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> new TakatafTenderNewModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(), resultSet.getInt(8)));
    }

    public TakatafTenderNewModel getTender(int id) {

        String sql = "SELECT tender_id, tender_logo, tender_title, tender_explain, category_name, tender_display_date, tender_expire_date ," +
                " count(distinct request_id) AS response_count " +
                "                FROM takatf_tender AS t INNER JOIN efaz_company.efaz_company_category as cat ON t.tender_cat_id = cat.category_id " +
                "                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id WHERE tender_id=? GROUP BY tender_id; ";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> new TakatafTenderNewModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(), resultSet.getInt(8)));
    }

    public TakatafTenderWithCompanies getSingleTenderDetails(int id) {


//        String sql = "SELECT tender_id, tender_logo, tender_title, tender_explain, category_name, tender_display_date, tender_expire_date , " +
//                "count(distinct request_id) AS response_count,school_name, school_logo_image,school_service_desc " +
//                "                FROM takatf_tender AS t INNER JOIN efaz_company.efaz_company_category as cat ON t.tender_cat_id = cat.category_id" +
//                "                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id  " +
//                "                LEFT JOIN efaz_company.efaz_school_profile AS res ON req.request_school_id = res.school_id WHERE tender_id=? GROUP BY tender_id;";

        String sql = "SELECT tender_id, tender_logo, tender_title, tender_explain, category_name, tender_display_date, tender_expire_date , " +
                "count(distinct request_id) AS response_count,school_name, school_logo_image,school_service_desc" +
                "                FROM takatf_tender AS t INNER JOIN efaz_company.efaz_company_category as cat ON t.tender_cat_id = cat.category_id" +
                "                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id " +
                "                LEFT JOIN efaz_company.efaz_school_profile AS res ON req.request_school_id = res.school_id where t.tender_id=? GROUP BY tender_id;";


        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (resultSet, i) -> new TakatafTenderWithCompanies(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(), resultSet.getInt(8),
                        resultSet.getString(9), resultSet.getBytes(10), resultSet.getString(11)));


    }

    public int acceptOffer(int request_id){
        return jdbcTemplate.update("UPDATE efaz_company.takatf_request_tender SET is_aproved=1 WHERE request_id=?;", request_id);
    }
    public int cancelOffer(int request_id){
        return jdbcTemplate.update("UPDATE efaz_company.takatf_request_tender SET is_aproved=0 WHERE request_id=?;", request_id);
    }




    public int updateRequest(int tender_id, byte[] tender_logo, String tender_title, String tender_explain, long tender_display_date, long tender_expire_date,
                             String tender_cat_id) {



        int category = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                Integer.class, "%" + tender_cat_id + "%");

        return jdbcTemplate.update("update takatf_tender set tender_logo=?," + " tender_title=?, tender_explain=?," +
                        "tender_display_date=?," + " tender_display_date=?, tender_is_confirmed=?, tender_is_available=?," +
                        "tender_cat_id=? "+
                        " where tender_id=?;", tender_logo,  tender_title, tender_explain, new Timestamp(tender_display_date)
                , new Timestamp(tender_expire_date), 0, 1, category, tender_id);

    }

    public int deleteSchoolRequest(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        int res = jdbcTemplate.update("DELETE FROM takatf_tender WHERE tender_id=?", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        return res;
    }




    public boolean isExist(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM takatf_tender WHERE tender_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }


}
