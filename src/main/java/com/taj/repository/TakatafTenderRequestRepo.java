package com.taj.repository;

import com.taj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@Repository
public class TakatafTenderRequestRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public int add$Request(int request_school_id, int request_tender_id, int is_aproved, long date, List<Takataf_schoolApplayCollectiveTender> category) {
        int res = jdbcTemplate.update("INSERT INTO takatf_request_tender VALUES (?,?,?,?,?)", null, request_school_id, request_tender_id, is_aproved, new Timestamp(date));
        for (int i=0; i< category.size(); i++){
            int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                    Integer.class, "%" + category.get(i).getCat_name().trim() + "%");
            jdbcTemplate.update("INSERT INTO takataf_request_cat_count VALUES (?,?,?,?,?)", null, categorys, request_school_id,
                    request_tender_id, category.get(i).getCount());
        }

        return res;
    }

    public List<TakatafSinfleSchoolRequestDTO> getAllRequestsWithNAme(){
        String sql = "SELECT tender_id, tender_title, tender_explain, tender_display_date, tender_expire_date , " +
                "                                 count(distinct request_id) AS response_count, id, category_name, count ,school_name,school_logo_image" +
                "                                                FROM takatf_tender AS t  " +
                "                                                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id" +
                "                                                LEFT JOIN efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id" +
                "                                                LEFT JOIN efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id" +
                "                                                INNER JOIN efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id" +
                "                                                GROUP BY tr.id;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new TakatafSinfleSchoolRequestDTO(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getInt(6),
                        resultSet.getInt(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getBytes(11)));

    }



    public List<TakatafSinfleSchoolRequestDTO> getAllRequestsWithNameByTender(int tend_id){
        String sql = "SELECT tender_id, tender_title, tender_explain, tender_display_date, tender_expire_date , " +
                "                                                 count(distinct request_id) AS response_count, id, category_name, count ,school_name,school_logo_image" +
                "                                                                FROM takatf_tender AS t  " +
                "                                                              LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id" +
                " LEFT JOIN efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id" +
                " LEFT JOIN efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id" +
                "                                                                INNER JOIN efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id" +
                "                                                                where t.tender_id=? " +
                "                                                                GROUP BY tr.id;";

        String sql2 = "SELECT tender_id, tender_title, tender_explain, tender_display_date, tender_expire_date , " +
                "                                                                 count(distinct request_id) AS response_count," +
                "                                                                  (SELECT id from efaz_company.takataf_request_cat_count where tend_id=?) AS id," +
                "(SELECT category_name from efaz_company.takataf_request_cat_count INNER JOIN efaz_company.efaz_company_category" +
                "                AS c ON cat_id = c.category_id where tend_id=?) AS category_name," +
                "                (SELECT count from efaz_company.takataf_request_cat_count where tend_id=?) AS count," +
                "               " +
                "                (SELECT school_name from efaz_company.takataf_request_cat_count INNER JOIN efaz_company.efaz_school_profile" +
                "                AS p ON scool_id = p.school_id where tend_id=?) AS school_name," +
                "                (SELECT school_logo_image from efaz_company.takataf_request_cat_count INNER JOIN efaz_company.efaz_school_profile" +
                "                AS p ON scool_id = p.school_id where tend_id=?) AS school_logo_image" +
                "                " +
                " FROM takatf_tender AS t  " +
                "                                                                              LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id" +
                "                 " +
                "                                                                                where t.tender_id=? " +
                "                                                                                GROUP BY t.tender_id;";

        return jdbcTemplate.query(sql,new Object[]{tend_id},
                (resultSet, i) -> new TakatafSinfleSchoolRequestDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getInt(6),
                        resultSet.getInt(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getBytes(11)));

    }



//    public List<TakatafTenderRequestModel> getTenderRequests(){
//        return jdbcTemplate.query("SELECT * FROM takatf_request_tender;",
//                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getTimestamp(5).getTime())));
//    }
//
//    public TakatafTenderRequestModel getTenderRequest(int id){
//        return jdbcTemplate.queryForObject("SELECT * FROM takatf_request_tender WHERE request_id=?;", new Object[]{id},
//                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getTimestamp(5).getTime())));
//    }
//
//
//    public List<TakatafTenderRequestModel> getTenderRequestsBySchool(int schoolId){
//        return jdbcTemplate.query("SELECT * FROM takatf_request_tender WHERE request_school_id=?;", new Object[]{schoolId},
//                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getTimestamp(5).getTime())));
//    }
//
//    public List<TakatafTenderRequestModel> getTenderRequestsByTender(int tenderId){
//        return jdbcTemplate.query("SELECT * FROM takatf_request_tender WHERE request_tender_id=?;", new Object[]{tenderId},
//                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getTimestamp(5).getTime())));
//    }
//
//    public List<TakatafTenderRequestModel> getTenderRequestsByAprove(int aprove){
//        return jdbcTemplate.query("SELECT * FROM takatf_request_tender WHERE is_aproved=?;", new Object[]{aprove},
//                ((resultSet, i) -> new TakatafTenderRequestModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getTimestamp(5).getTime())));
//    }
//
//    public int acceptTenderRequest(int seen_id) {
//        return jdbcTemplate.update("UPDATE takatf_request_tender SET is_aproved=1 WHERE request_id=?", seen_id);
//    }
//    public int refuseTenderRequest(int seen_id) {
//        return jdbcTemplate.update("UPDATE takatf_request_tender SET is_aproved=0 WHERE request_id=?", seen_id);
//    }
//
//    public int updateTenderRequest(int seen_id, int seen_school_id, int seen_tender_id, int is_aproved, long date) {
//        return jdbcTemplate.update("UPDATE takatf_request_tender SET request_school_id=?, request_tender_id=?, is_aproved=?, t_date=? WHERE request_id=?", seen_school_id, seen_tender_id, is_aproved, new Timestamp(date), seen_id);
//    }
//
//    public int deleteTenderRequest(int seen_id) {
//        return jdbcTemplate.update("DELETE FROM takatf_request_tender WHERE request_id=?", seen_id);
//    }

}
