package com.taj.repository;

import com.taj.model.TakatafSinfleSchoolRequestDTO;
import com.taj.model.TakatafSingleSchoolRequestByIDDTO;
import com.taj.model.Takataf_schoolApplayCollectiveTender;
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
        if (!isExistApp(request_school_id, request_tender_id)){
            int res = jdbcTemplate.update("INSERT INTO takatf_request_tender VALUES (?,?,?,?,?)", null, request_school_id, request_tender_id, is_aproved, new Timestamp(date));
            for (int i = 0; i < category.size(); i++) {
                int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                        Integer.class, "%" + category.get(i).getCat_name().trim() + "%");
                jdbcTemplate.update("INSERT INTO takataf_request_cat_count VALUES (?,?,?,?,?)", null, categorys, request_school_id,
                        request_tender_id, category.get(i).getCount());
            }

            return res;
        }else{
            return -1;
        }

    }

    public boolean isExistApp(int school_id, int tender_id) {

        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.takatf_request_tender WHERE request_school_id=? AND request_tender_id=?;",
                Integer.class, school_id, tender_id);
        return cnt != null && cnt > 0;


    }

    public List<TakatafSinfleSchoolRequestDTO> getAllRequestsWithNAme() {
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


    public List<TakatafSinfleSchoolRequestDTO> getAllRequestsWithNameByTender(int tend_id) {
        String sql = " SELECT " +
                " tender_id, tender_title, tender_explain, tender_display_date, tender_expire_date ,  " +
                " count(distinct request_id) AS response_count, ifnull(id,0) AS id, ifnull(category_name,0) AS  category_name " +
                "            , ifnull(count,0) AS count, ifnull(school_name,0) AS school_name ,ifnull(school_logo_image,0) AS school_logo_image " +
                " FROM " +
                " takatf_tender AS t   " +
                " LEFT JOIN  " +
                " efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id " +
                " LEFT JOIN  " +
                " efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id " +
                " LEFT JOIN  " +
                " efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id " +
                " LEFT JOIN " +
                " efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id " +
                " where " +
                " t.tender_id=? " +
                " GROUP BY " +
                " tr.id;";

        String sql2 = "SELECT\n" +
                "\ttender_id,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\tcount( DISTINCT request_id ) AS response_count,\n" +
                "\tifnull( id, 0 ) AS id,\n" +
                "\tifnull( category_name, 0 ) AS category_name,\n" +
                "\tifnull( count, 0 ) AS count,\n" +
                "\tifnull( school_name, 0 ) AS school_name,\n" +
                "\tifnull( school_logo_image, 0 ) AS school_logo_image \n" +
                "FROM\n" +
                "\ttakatf_tender AS t\n" +
                "\tLEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id\n" +
                "\tLEFT JOIN efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id \n" +
                "WHERE\n" +
                "\tt.tender_id =? \n" +
                "GROUP BY\n" +
                "\ttr.id, tender_id,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date;";

        return jdbcTemplate.query(sql2, new Object[]{tend_id},
                (resultSet, i) -> new TakatafSinfleSchoolRequestDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getInt(6),
                        resultSet.getInt(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getBytes(11)));

    }


    public List<TakatafSingleSchoolRequestByIDDTO> getAllRequestsWithNameByIdzs(int id) {
        String sql = "SELECT tender_id, tender_title, tender_explain, tender_display_date, tender_expire_date , " +
                "                                                                count(distinct request_id) AS response_count, ifnull(id,0) as id, ifnull(category_name, 0)as category_name" +
                "                                                                                FROM takatf_tender AS t  " +
                "                                                                                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id" +
                "                                                                                LEFT JOIN efaz_company.tkatf_tender_catgory_request AS tr ON t.tender_id = tr.t_tender_id" +
                "                                                                                LEFT JOIN efaz_company.efaz_company_category AS ca ON tr.t_category_id = category_id" +
                "                                                                      where tender_id =?  GROUP BY tr.id;";


        return jdbcTemplate.query(sql, new Object[]{id},
                (resultSet, i) -> new TakatafSingleSchoolRequestByIDDTO(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getInt(6),
                        resultSet.getInt(7), resultSet.getString(8)));

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
