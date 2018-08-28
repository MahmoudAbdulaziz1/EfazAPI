package com.taj.repository;

import com.taj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
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
                         long tender_company_display_date, long tender_company_expired_date, List<TakatfTenderCategoryPOJO> cats) {


        int a = jdbcTemplate.update("INSERT INTO takatf_tender VALUES (?,?,?,?,?,?,?,?,?,?)", null, tender_logo, tender_title, tender_explain,
                new Timestamp(tender_display_date), new Timestamp(tender_expire_date), 0, 1, new Timestamp(tender_company_display_date),
                new Timestamp(tender_company_expired_date));
        for (int i=0 ; i< cats.size(); i++){
            int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                    Integer.class, "%" + cats.get(i).getCategory_name().trim() + "%");

            jdbcTemplate.update("INSERT INTO efaz_company.tkatf_tender_catgory_request VALUES  (?,?,?)", null, 3, categorys);
        }


        return  a;
    }


    public List<TakatafTenderPOJO> getTenders() {
        String sql = "SELECT tender_id, tender_logo, tender_title, tender_explain, tender_display_date, tender_expire_date , " +
                "                 tender_company_display_date, tender_company_expired_date," +
                "                 count(distinct request_id) AS response_count " +
                "                                FROM takatf_tender AS t  " +
                "                                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id GROUP BY tender_id;";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> new TakatafTenderPOJO(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getTimestamp(5).getTime(), resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(),
                        resultSet.getTimestamp(8).getTime(), resultSet.getInt(9)));
    }

    public TakatafTenderPOJO getTender(int id) {

        String sql = "SELECT tender_id, tender_logo, tender_title, tender_explain, tender_display_date, tender_expire_date , " +
                " tender_company_display_date, tender_company_expired_date, " +
                " count(distinct request_id) AS response_count " +
                "                FROM takatf_tender AS t  " +
                "                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id WHERE tender_id=? GROUP BY tender_id; ";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> new TakatafTenderPOJO(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getTimestamp(5).getTime(), resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(),
                        resultSet.getTimestamp(8).getTime(), resultSet.getInt(9)));
    }

    public List<TakatafMyTenderPageDTO> getAdminTenders() {
        String sql = "SELECT " +
                " tender_id, tender_title, tender_explain, tender_display_date, tender_expire_date , " +
                " count(DISTINCT ten.t_category_id) AS cat_count," +
                " count(distinct request_id) AS response_count " +
                "FROM " +
                " (takatf_tender AS t  " +
                "LEFT JOIN " +
                " efaz_company.tkatf_tender_catgory_request AS ten ON t.tender_id = ten.t_tender_id " +
                "LEFT JOIN " +
                " efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id) " +
                " GROUP BY " +
                " t.tender_id;";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> new com.taj.model.TakatafMyTenderPageDTO(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(),
                        resultSet.getInt(6), resultSet.getInt(7)));
    }

//    public TakatafTenderWithCompanies getSingleTenderDetails(int id) {
//
//
//        String sql = "SELECT tender_id, tender_logo, tender_title, tender_explain, category_name, tender_display_date, tender_expire_date ," +
//                " tender_company_display_date, tender_company_expired_date, " +
//                "count(distinct request_id) AS response_count,school_name, school_logo_image,school_service_desc" +
//                "                FROM takatf_tender AS t INNER JOIN efaz_company.efaz_company_category as cat ON t.tender_cat_id = cat.category_id" +
//                "                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id " +
//                "                LEFT JOIN efaz_company.efaz_school_profile AS res ON req.request_school_id = res.school_id where t.tender_id=? GROUP BY tender_id;";
//
//
//        return jdbcTemplate.queryForObject(sql,
//                new Object[]{id}, (resultSet, i) -> new TakatafTenderWithCompanies(resultSet.getInt(1), resultSet.getBytes(2),
//                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
//                        resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(),
//                        resultSet.getTimestamp(8).getTime(), resultSet.getTimestamp(9).getTime(),resultSet.getInt(10),
//                        resultSet.getString(11), resultSet.getBytes(12), resultSet.getString(13)));
//
//
//    }
//
//    public int acceptOffer(int request_id){
//        return jdbcTemplate.update("UPDATE efaz_company.takatf_request_tender SET is_aproved=1 WHERE request_id=?;", request_id);
//    }
//    public int cancelOffer(int request_id){
//        return jdbcTemplate.update("UPDATE efaz_company.takatf_request_tender SET is_aproved=0 WHERE request_id=?;", request_id);
//    }
//
//
//
//
//    public int updateRequest(int tender_id, byte[] tender_logo, String tender_title, String tender_explain, long tender_display_date, long tender_expire_date,
//                             long tender_company_display_date, long tender_company_expired_date, String tender_cat_id) {
//
//
//
//        int category = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
//                Integer.class, "%" + tender_cat_id + "%");
//
//        return jdbcTemplate.update("update takatf_tender set tender_logo=?," + " tender_title=?, tender_explain=?," +
//                        "tender_display_date=?," + " tender_display_date=?, tender_is_confirmed=?, tender_is_available=?, " +
//                        "tender_company_display_date=?, tender_company_expired_date=?, " +
//                        "tender_cat_id=? "+
//                        " where tender_id=?;", tender_logo,  tender_title, tender_explain, new Timestamp(tender_display_date)
//                , new Timestamp(tender_expire_date), 0, 1, new Timestamp(tender_company_display_date), new Timestamp(tender_company_expired_date),category, tender_id);
//
//    }
//
//    public int deleteSchoolRequest(int id) {
//        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
//        int res = jdbcTemplate.update("DELETE FROM takatf_tender WHERE tender_id=?", id);
//        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
//        return res;
//    }
//
//
//

    public boolean isExist(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM takatf_tender WHERE tender_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }


}
