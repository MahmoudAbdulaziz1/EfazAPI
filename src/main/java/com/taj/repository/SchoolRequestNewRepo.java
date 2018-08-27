package com.taj.repository;

import com.taj.model.SchoolRequestNewDto;
import com.taj.model.SchoolRequestNewDtoWitCompany;
import com.taj.model.SchoolRequestsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 8/15/2018.
 */
@Repository
public class SchoolRequestNewRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addRequest(String request_title, String request_explaination,
                          long request_display_date, long request_expired_date, int school_id,
                          String request_category_id) {

//        int category = jdbcTemplate.queryForObject("SELECT request_category_id  FROM efaz_company.efaz_school_request_category WHERE  request_category_name=?;",
//                Integer.class, request_category_id);

        int category = jdbcTemplate.queryForObject("SELECT request_category_id  FROM efaz_company.efaz_school_request_category WHERE  request_category_name LIKE ?;",
                Integer.class, "%" + request_category_id + "%");

        return jdbcTemplate.update("INSERT INTO efaz_school_tender VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", null, null, null, request_title,
                request_explaination, new Timestamp(request_display_date), new Timestamp(request_expired_date), null, null, null,
                null, school_id, category, null, null, null);
    }

    public List<SchoolRequestsDTO> getRequests() {

        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, school_id," +
                "    request_category_name" +
                " FROM efaz_school_tender AS tender INNER JOIN" +
                "                        efaz_company.efaz_school_request_category AS cat" +
                "                         ON" +
                "                         tender.request_category_id = cat.request_category_id;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new SchoolRequestsDTO(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7)));
    }


    public List<SchoolRequestNewDto> getRequestsAll() {

        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, school_id," +
                "    request_category_name, " +
                "    count(distinct responsed_request_id) AS response_count" +
                " FROM efaz_school_tender AS tender INNER JOIN" +
                "                        efaz_company.efaz_school_request_category AS cat" +
                "                         ON" +
                "                         tender.request_category_id = cat.request_category_id" +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS req" +
                "                         ON tender.request_id = req.responsed_request_id" +
                "                         GROUP BY request_id;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new SchoolRequestNewDto(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8)));
    }


    public SchoolRequestNewDto getRequestByID(int id) {


        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, school_id," +
                "    request_category_name, " +
                "    count(distinct responsed_request_id) AS response_count" +
                " FROM efaz_school_tender AS tender INNER JOIN" +
                "                        efaz_company.efaz_school_request_category AS cat" +
                "                         ON" +
                "                         tender.request_category_id = cat.request_category_id" +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS req" +
                "                         ON tender.request_id = req.responsed_request_id" +
                "                        WHERE  request_id=? " +
                " GROUP BY request_id;";


//        String sql = "SELECT " +
//                "request_id, request_title, request_explaination, request_display_date, " +
//                "    request_expired_date, school_id," +
//                "    request_category_name" +
//                " FROM efaz_school_tender AS tender INNER JOIN" +
//                "                        efaz_company.efaz_school_request_category AS cat" +
//                "                         ON" +
//                "                         tender.request_category_id = cat.request_category_id"+
//                " WHERE  request_id=?;";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestNewDto(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8)));
    }


    public List<SchoolRequestNewDto> getRequestsBySchoolID(int id) {


        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, school_id," +
                "    request_category_name, " +
                "    count( responsed_request_id) AS response_count" +
                " FROM efaz_school_tender AS tender INNER JOIN" +
                "                        efaz_company.efaz_school_request_category AS cat" +
                "                         ON" +
                "                         tender.request_category_id = cat.request_category_id" +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS req" +
                "                         ON tender.request_id = req.responsed_request_id" +
                "                       WHERE  school_id=?  " +
                "  GROUP BY request_id;";


        return jdbcTemplate.query(sql,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestNewDto(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8)));
    }

    public List<SchoolRequestNewDto> getRequestsByCategoryID(String id) {


        int category = jdbcTemplate.queryForObject("SELECT request_category_id  FROM efaz_company.efaz_school_request_category WHERE  request_category_name LIKE ?;",
                Integer.class, "%" + id + "%");


        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, school_id," +
                "    request_category_name, " +
                "    count( responsed_request_id) AS response_count" +
                " FROM efaz_school_tender AS tender INNER JOIN" +
                "                        efaz_company.efaz_school_request_category AS cat" +
                "                         ON" +
                "                         tender.request_category_id = cat.request_category_id" +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS req" +
                "                         ON tender.request_id = req.responsed_request_id" +
                "                      WHERE  tender.request_category_id =?   " +
                "  GROUP BY request_id;";


//        String sql = "SELECT " +
//                "request_id, request_title, request_explaination, request_display_date, " +
//                "    request_expired_date, school_id," +
//                "    request_category_name" +
//                " FROM efaz_school_tender AS tender INNER JOIN" +
//                "                        efaz_company.efaz_school_request_category AS cat" +
//                "                         ON" +
//                "                         tender.request_category_id = cat.request_category_id"+
//                " WHERE  tender.request_category_id = ?;";

        return jdbcTemplate.query(sql,
                new Object[]{category}, (resultSet, i) -> new SchoolRequestNewDto(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()
                        , resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8)));
    }


    public int updateRequest(int request_id, String request_title, String request_explaination,
                             long request_display_date, long request_expired_date, int school_id,
                             String request_category_id) {

        int category = jdbcTemplate.queryForObject("SELECT request_category_id  FROM efaz_company.efaz_school_request_category WHERE  request_category_name LIKE ?;",
                Integer.class, "%" + request_category_id + "%");

        return jdbcTemplate.update("update efaz_school_tender set request_details_file=?," + " images_id=?, request_title=?," +
                        "request_explaination=?," + " request_display_date=?, request_expired_date=?, request_deliver_date=?," +
                        "request_payment_date=?, request_is_available=?, request_is_conformied=?, school_id=?, request_category_id=?," +
                        " receive_palce_id=?, extended_payment=?, request_count=? " +
                        " where request_id=?;", null, null, request_title, request_explaination, new Timestamp(request_display_date)
                , new Timestamp(request_expired_date), null, null, null, null, school_id, category,
                null, null, null, request_id);

    }

    public int deleteSchoolRequest(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        int res = jdbcTemplate.update("DELETE FROM efaz_school_tender WHERE request_id=?", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        return res;
    }


    public boolean isExist(int id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_school_tender WHERE request_id=?;",
                Integer.class, id);
        return cnt != null && cnt > 0;
    }


    public List<SchoolRequestNewDtoWitCompany> getSingleTenderDetails(int request_id) {


//
        String sql = "SELECT " +
                "request_id, request_title, request_explaination, request_display_date, " +
                "    request_expired_date, " +
                "    count( req.responsed_request_id) AS response_count, res.response_date, res.responsed_cost, company_name, company_logo_image, category_name " +
                " FROM (efaz_school_tender AS tender " +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS req " +
                "                         ON tender.request_id = req.responsed_request_id)" +
                "                         LEFT JOIN efaz_company.efaz_company_response_school_request AS res " +
                "                         ON tender.request_id = res.responsed_request_id " +
                "                         LEFT JOIN efaz_company.efaz_company_profile AS com " +
                "                         ON res.responsed_company_id = com.company_id" +
                "                         INNER JOIN efaz_company.efaz_company_category AS cat " +
                "                         ON com.company_category_id = cat.category_id " +
                "where request_id =?" +
                "                         GROUP BY company_name;";

        String sql1 ="SELECT " +
                "                request_id, request_title, request_explaination, request_display_date, " +
                "                    request_expired_date, res.response_date, res.responsed_cost, company_name, company_logo_image, category_name" +
                "                 FROM efaz_school_tender AS tender " +
                "  LEFT JOIN efaz_company.efaz_company_response_school_request AS res " +
                "     ON tender.request_id = res.responsed_request_id" +
                "                  LEFT JOIN efaz_company.efaz_company_profile AS com " +
                "                                        ON res.responsed_company_id = com.company_id" +
                " INNER JOIN efaz_company.efaz_company_category AS cat " +
                " ON com.company_category_id = cat.category_id " +
                "  where request_id =?;" ;



        return jdbcTemplate.query(sql1,
                new Object[]{request_id}, (resultSet, i) -> new SchoolRequestNewDtoWitCompany(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(),
                        resultSet.getTimestamp(6).getTime(), resultSet.getDouble(7),
                        resultSet.getString(8), resultSet.getBytes(9), resultSet.getString(10)));


    }

    public int acceptOffer(int request_id){
        return jdbcTemplate.update("UPDATE efaz_company.efaz_school_tender SET request_is_conformied=1 WHERE request_id=?;", request_id);
    }
    public int cancelOffer(int request_id){
        return jdbcTemplate.update("UPDATE efaz_company.efaz_school_tender SET request_is_conformied=0 WHERE request_id=?;", request_id);
    }


}
