package com.taj.repository;

import com.taj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 7/8/2018.
 */
@Repository
public class TakatafTenderRequestRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public int add$Request(int request_school_id, int request_tender_id, int is_aproved, long date, List<Takataf_schoolApplayCollectiveTender> category) {
        int res = 0;
        if (!isExistApp(request_school_id, request_tender_id)) {
            jdbcTemplate.update("INSERT INTO takatf_request_tender VALUES (?,?,?,?,?)", null, request_school_id, request_tender_id, is_aproved
                    , new Timestamp(System.currentTimeMillis()));
            for (int i = 0; i < category.size(); i++) {
                int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                        Integer.class, "%" + category.get(i).getCat_name().trim() + "%");
                res = jdbcTemplate.update("INSERT INTO takataf_request_cat_count VALUES (?,?,?,?,?)", null, categorys, request_school_id,
                        request_tender_id, category.get(i).getCount());
            }

            return res;
        } else {
            for (int i = 0; i < category.size(); i++) {
                int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                        Integer.class, "%" + category.get(i).getCat_name().trim() + "%");
                res = jdbcTemplate.update("UPDATE takataf_request_cat_count SET count=? WHERE cat_id=? AND scool_id=? AND tend_id=?",category.get(i).getCount(),
                        categorys, request_school_id, request_tender_id);
            }

            return res;
        }

    }




    public int updateRequest(int request_school_id, int request_tender_id, List<Takataf_schoolApplayCollectiveTender> category) {
        int res = 0;
        if (isExistApp(request_school_id, request_tender_id)) {
            System.out.println(isExistApp(request_school_id, request_tender_id)+" ");
            for (int i = 0; i < category.size(); i++) {
                int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                        Integer.class, "%" + category.get(i).getCat_name().trim() + "%");
                res = jdbcTemplate.update("UPDATE takataf_request_cat_count SET count=? WHERE cat_id=? AND scool_id=? AND tend_id=?",category.get(i).getCount(),
                        categorys, request_school_id, request_tender_id);
            }

            return res;
        } else {
            System.out.println(isExistApp(request_school_id, request_tender_id)+" else");

            for (int i = 0; i < category.size(); i++) {
                int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                        Integer.class, "%" + category.get(i).getCat_name().trim() + "%");
                res = jdbcTemplate.update("INSERT INTO takataf_request_cat_count VALUES (?,?,?,?,?)", null, categorys, request_school_id,
                        request_tender_id, category.get(i).getCount());
            }

            return res;
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


    public List<Map<String, Object>> getAllRequestsWithNameByTender2(int tend_id) {
        String sql = "SELECT\n" +
                "\ttender_id,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\tcount( DISTINCT request_id ) AS response_count,\n" +
                "\tifnull( id, 0 ) AS id,\n" +
                "\tifnull( category_name, 0 ) AS category_name,\n" +
                "\tifnull( count, 0 ) AS count,\n" +
                "\tschool_id,\n" +
                "\tifnull( school_name, 0 ) AS school_name,\n" +
                "\tifnull( school_logo_image, 0 ) AS school_logo_image \n" +
                "FROM\n" +
                "\ttakatf_tender AS t\n" +
                "\tLEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id\n" +
                "\tLEFT JOIN efaz_company.takataf_request_cat_count AS tr ON t.tender_id = tr.tend_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile sp ON tr.scool_id = sp.school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_category AS ca ON tr.cat_id = category_id \n" +
                "WHERE\n" +
                "\tt.tender_id = ?\n" +
                "GROUP BY\n" +
                "\ttr.id,\n" +
                "\ttender_id,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\tschool_id;";

        return  jdbcTemplate.queryForList(sql, new Object[]{tend_id});
    }






    public List<TakatafSinfleSchoolRequestDTO>  getAllRequestsWithNameByTender(int tend_id) {
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

    public List<CategoryNameDto> categoryData(int tend_id){
        String sql = "SELECT\n" +
                "\tcategory_name \n" +
                "FROM\n" +
                "\ttkatf_tender_catgory_request AS t\n" +
                "\tLEFT JOIN efaz_company.efaz_company_category AS c ON t.t_category_id = c.category_id \n" +
                "WHERE\n" +
                "\tt.t_tender_id =?";

        return jdbcTemplate.query(sql, new Object[]{tend_id},
                ((resultSet, i) -> new CategoryNameDto(resultSet.getString(1))));
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

    public List<TakatafSingleSchoolRequestByIDDTO2> getAllRequestsWithNameByIdzs2(int id, int school_id) {
        String sql = "SELECT tender_id,\n" +
                "                tender_title,\n" +
                "                tender_explain,\n" +
                "                tender_display_date,\n" +
                "                tender_expire_date,\n" +
                "                count( DISTINCT request_id ) AS response_count,\n" +
                "                co.cat_id AS id,\n" +
                "                category_name, count\n" +
                "FROM efaz_company.takataf_request_cat_count as co\n" +
                "LEFT JOIN efaz_company_category as cat ON co.cat_id= cat.category_id\n" +
                "LEFT JOIN takatf_tender AS t ON co.tend_id= t.tender_id\n" +
                "LEFT JOIN efaz_company.takatf_request_tender AS req ON co.tend_id = req.request_tender_id\n" +
                "WHERE tend_id= ? AND scool_id=? \n" +
                "GROUP BY category_name, count,tender_id,\n" +
                "                tender_title,\n" +
                "                tender_explain,\n" +
                "                tender_display_date,\n" +
                "                tender_expire_date,\n" +
                "                co.cat_id;";

        String sql2 ="SELECT\n" +
                "\ttender_id,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\tcount( DISTINCT request_id ) AS response_count,\n" +
                "\tco.cat_id AS id,\n" +
                "\tcategory_name,\n" +
                "\tcount \n" +
                "FROM\n" +
                "\ttakatf_tender AS t\n" +
                "\tLEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id\n" +
                "\tLEFT JOIN efaz_company.takataf_request_cat_count AS co ON t.tender_id = co.tend_id AND co.scool_id=? \n" +
                "\tLEFT JOIN efaz_company_category AS cat ON co.cat_id = cat.category_id \n" +
                "WHERE\n" +
                "\ttender_id = ? \n" +
                "GROUP BY\n" +
                "\tid,\n" +
                "\ttender_id,\n" +
                "\ttender_title,\n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\tcategory_name,\n" +
                "\tcount;";


        return jdbcTemplate.query(sql2, new Object[]{ school_id, id,},
                (resultSet, i) -> new TakatafSingleSchoolRequestByIDDTO2(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getInt(6),
                        resultSet.getInt(7), resultSet.getString(8), resultSet.getInt(9)));

    }

    public List<CollectiveTenderBySchoolDto> getCollectiveTender(int categoryId) {
        String sql = "SELECT\n" +
                "                tender_id,\n" +
                "                tender_logo,\n" +
                "                tender_title,\n" +
                "                tender_explain,\n" +
                "                tender_display_date,\n" +
                "                tender_expire_date,\n" +
                "                tender_company_display_date,\n" +
                "                tender_company_expired_date,\n" +
                "                count( DISTINCT request_id ) AS response_count,\n" +
                "                count( DISTINCT seen_id ) AS view_count,\n" +
                "                category_name \n" +
                "                FROM\n" +
                "                takatf_tender AS t\n" +
                "                LEFT JOIN efaz_company.takatf_request_tender AS req ON t.tender_id = req.request_tender_id\n" +
                "                LEFT JOIN efaz_company.takatf_school_see_tender AS see ON t.tender_id = see.seen_tender_id\n" +
                "                LEFT JOIN efaz_company.tkatf_tender_catgory_request AS cat ON t.tender_id = cat.t_tender_id\n" +
                "                LEFT JOIN efaz_company.efaz_company_category AS n ON cat.t_category_id = n.category_id \n" +
                "                WHERE\n" +
                "                cat.t_category_id = ? AND (t.tender_expire_date < now() AND tender_company_expired_date>= NOW())\n" +
                "                GROUP BY\n" +
                "                \ttender_id;";

        return jdbcTemplate.query(sql, new Object[]{categoryId},
                (resultSet, i) -> new CollectiveTenderBySchoolDto(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getTimestamp(5).getTime(), resultSet.getTimestamp(6).getTime(),
                        resultSet.getTimestamp(7).getTime(), resultSet.getTimestamp(8).getTime(), resultSet.getInt(9),
                        resultSet.getInt(10), resultSet.getString(11)));

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
