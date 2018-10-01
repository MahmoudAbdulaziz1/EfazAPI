package com.taj.repository;

import com.taj.model.GetSingleSchoolRequestByCategory;
import com.taj.model.SchoolRequestDto;
import com.taj.model.SchoolRequestHistoryDto;
import com.taj.model.SchoolRequestsModel;
import com.taj.model.new_school_history.SchoolRequestHistoryDtoDTO;
import com.taj.model.new_school_history.SchoolRequestHistoryDtoDTO2;
import com.taj.model.school_request_image_web.schoolRequestWithImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
@Repository
public class SchoolRequestRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addRequest(byte[] request_details_file, int images_id, String request_title, String request_explaination,
                          long request_display_date, long request_expired_date, long request_deliver_date,
                          long request_payment_date, int request_is_available, int request_is_conformied, int school_id,
                          int request_category_id, int receive_palce_id, int extended_payment, int request_count) {
        return jdbcTemplate.update("INSERT INTO efaz_school_tender VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", null, request_details_file, images_id, request_title,
                request_explaination, new Timestamp(request_display_date), new Timestamp(request_expired_date), new Timestamp(request_deliver_date), request_is_available, request_is_conformied,
                new Timestamp(request_payment_date), school_id, request_category_id, receive_palce_id, extended_payment, request_count);
    }

    public List<SchoolRequestsModel> getRequests() {
        return jdbcTemplate.query("SELECT * FROM efaz_school_tender;",
                (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(),
                        resultSet.getTimestamp(8).getTime(),
                        resultSet.getInt(9), resultSet.getInt(10), resultSet.getTimestamp(11).getTime(), resultSet.getInt(12), resultSet.getInt(13),
                        resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));
    }

    public SchoolRequestsModel getRequestByID(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_school_tender WHERE  request_id=?;",
                new Object[]{id}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(), resultSet.getTimestamp(8).getTime(),
                        resultSet.getInt(9), resultSet.getInt(10), resultSet.getTimestamp(11).getTime(), resultSet.getInt(12), resultSet.getInt(13),
                        resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));
    }

    public List<SchoolRequestsModel> getRequestsByID(int id) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  school_id=?;",
                new Object[]{id}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(), resultSet.getTimestamp(8).getTime(),
                        resultSet.getInt(9), resultSet.getInt(10), resultSet.getTimestamp(11).getTime(), resultSet.getInt(12),
                        resultSet.getInt(13), resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));
    }


    public int updateRequest(int request_id, byte[] request_details_file, int images_id, String request_title, String request_explaination,
                             long request_display_date, long request_expired_date, long request_deliver_date,
                             long request_payment_date, int request_is_available, int request_is_conformied, int school_id,
                             int request_category_id, int receive_palce_id, int extended_payment, int request_count) {

        return jdbcTemplate.update("update efaz_school_tender set request_details_file=?," + " images_id=?, request_title=?," +
                        "request_explaination=?," + " request_display_date=?, request_expired_date=?, request_deliver_date=?," +
                        "request_payment_date=?, request_is_available=?, request_is_conformied=?, school_id=?, request_category_id=?," +
                        " receive_palce_id=?, extended_payment=?, request_count=? " +
                        " where request_id=?", request_details_file, images_id, request_title, request_explaination, new Timestamp(request_display_date)
                , new Timestamp(request_expired_date), new Timestamp(request_deliver_date), new Timestamp(request_payment_date), request_is_available, request_is_conformied, school_id, request_category_id,
                receive_palce_id, extended_payment, request_count, request_id);

    }

    public int deleteSchoolRequest(int id) {
        return jdbcTemplate.update("DELETE FROM efaz_school_tender WHERE request_id=?", id);
    }

    public List<SchoolRequestsModel> filterByIsAvailable(int isAvailable) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  request_is_available=?;",
                new Object[]{isAvailable}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6), resultSet.getLong(7), resultSet.getLong(8),
                        resultSet.getInt(9), resultSet.getInt(10), resultSet.getLong(11), resultSet.getInt(12), resultSet.getInt(13),
                        resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));
    }


    public List<SchoolRequestsModel> filterByIsConfirmed(int isConfirmed) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  request_is_conformied=?;",
                new Object[]{isConfirmed}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6), resultSet.getLong(7), resultSet.getLong(8),
                        resultSet.getInt(9), resultSet.getInt(10), resultSet.getLong(11), resultSet.getInt(12), resultSet.getInt(13),
                        resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));
    }


    public List<SchoolRequestsModel> filterByTitle(String title) {
        String sql = "SELECT * FROM efaz_school_tender WHERE  request_title LIKE ?;";
        return jdbcTemplate.query(sql, new String[]{"%" + title + "%"}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
                resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6), resultSet.getLong(7), resultSet.getLong(8),
                resultSet.getInt(9), resultSet.getInt(10), resultSet.getLong(11), resultSet.getInt(12), resultSet.getInt(13),
                resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));
    }

    public List<SchoolRequestsModel> filterByExplain(String explain) {

        String sql = "SELECT * FROM efaz_school_tender WHERE  request_explaination LIKE ?;";
        return jdbcTemplate.query(sql, new String[]{"%" + explain + "%"}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
                resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6), resultSet.getLong(7), resultSet.getLong(8),
                resultSet.getInt(9), resultSet.getInt(10), resultSet.getLong(11), resultSet.getInt(12), resultSet.getInt(13),
                resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));
    }

    public List<GetSingleSchoolRequestByCategory> filterByCategory(int cat) {
//        int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
//                Integer.class, "%" + cat + "%");


//        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  requests_category_id=?;",
//                new Object[]{cat}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
//                        resultSet.getString(4), resultSet.getString(5), resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(),
//                        resultSet.getTimestamp(8).getTime(),resultSet.getInt(9), resultSet.getInt(10),
//                        resultSet.getTimestamp(11).getTime(), resultSet.getInt(12), resultSet.getInt(13),
//                        resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));


        String sql1 = "SELECT tender.request_id,  request_title, request_explaination, request_display_date,\n" +
                "                 request_expired_date, ifnull(request_is_available,0)AS request_is_available, ifnull(request_is_conformied,0)AS request_is_conformied,\n" +
                "                 tender.school_id, ifnull(school_name,0)AS school_name, ifnull(school_logo_image,0) AS school_logo_image ,\n" +
                "                 requests_category_id, request_category_name, ifnull(extended_payment,0) AS extended_payment," +
                " count(response_id) AS request_count " +
                "                 , count(seen_id)AS views_count\n" +
                "                 FROM efaz_company.efaz_school_tender AS tender\n" +
                "                 LEFT JOIN efaz_company.efaz_school_profile AS profile ON tender.school_id = profile.school_id\n" +
                "                 Left JOIN efaz_company.efaz_school_request_category AS cat ON tender.requests_category_id = cat.request_category_id\n" +
                "                 Left Join efaz_company.efaz_company_see_request AS reqst ON  tender.request_id = reqst.request_id " +
                " LEFT JOIN efaz_company.efaz_company_response_school_request as req ON tender.request_id=req.responsed_request_id " +
                "                 Where tender.requests_category_id=?\n" +
                "                 Group By tender.request_id,  request_title, request_explaination, request_display_date,\n" +
                "                 request_expired_date, request_is_available, request_is_conformied,tender.school_id, school_name, school_logo_image,\n" +
                "                 requests_category_id, request_category_name,extended_payment, request_count;";


        String sql = "SELECT request_id,  request_title, request_explaination, request_display_date,\n" +
                " request_expired_date, ifnull(request_is_available,0)AS request_is_available, ifnull(request_is_conformied,0)AS request_is_conformied,\n" +
                " tender.school_id, ifnull(school_name,0)AS school_name, ifnull(school_logo_image,0) AS school_logo_image , " +
                " requests_category_id, request_category_name, ifnull(extended_payment,0) AS extended_payment, ifnull(request_count, 0)AS request_count \n" +
                " FROM efaz_company.efaz_school_tender AS tender\n" +
                " LEFT JOIN efaz_company.efaz_school_profile AS profile ON tender.school_id = profile.school_id\n" +
                " Left JOIN efaz_company.efaz_school_request_category AS cat ON tender.requests_category_id = cat.request_category_id" +
                " Where tender.requests_category_id=?;";
        return jdbcTemplate.query(sql1,
                new Object[]{cat}, (resultSet, i) -> new GetSingleSchoolRequestByCategory(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(),
                        resultSet.getInt(6), resultSet.getInt(7),
                        resultSet.getInt(8), resultSet.getString(9), resultSet.getBytes(10), resultSet.getInt(11),
                        resultSet.getString(12), resultSet.getInt(13), resultSet.getInt(14), resultSet.getInt(15)));
    }


    public List<schoolRequestWithImageDto> filterByCategoryWithImage(int cat) {
//        int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
//                Integer.class, "%" + cat + "%");


//        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  requests_category_id=?;",
//                new Object[]{cat}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
//                        resultSet.getString(4), resultSet.getString(5), resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(),
//                        resultSet.getTimestamp(8).getTime(),resultSet.getInt(9), resultSet.getInt(10),
//                        resultSet.getTimestamp(11).getTime(), resultSet.getInt(12), resultSet.getInt(13),
//                        resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));


        String sql1 = "SELECT\n" +
                "\ttender.request_id,\n" +
                "\trequest_title,\n" +
                "\trequest_explaination,\n" +
                "\trequest_display_date,\n" +
                "\trequest_expired_date,\n" +
                "\tifnull( request_is_available, 0 ) AS request_is_available,\n" +
                "\tifnull( request_is_conformied, 0 ) AS request_is_conformied,\n" +
                "\ttender.school_id,\n" +
                "\tifnull( school_name, 0 ) AS school_name,\n" +
                "\tifnull( school_logo_image, 0 ) AS school_logo_image,\n" +
                "\trequests_category_id,\n" +
                "\trequest_category_name,\n" +
                "\tifnull( extended_payment, 0 ) AS extended_payment,\n" +
                "\tcount( response_id ) AS request_count,\n" +
                "\tcount( seen_id ) AS views_count,\n" +
                "\timage_one \n" +
                "FROM\n" +
                "\tefaz_company.efaz_school_tender AS tender\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile AS PROFILE ON tender.school_id = PROFILE.school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_request_category AS cat ON tender.requests_category_id = cat.request_category_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_see_request AS reqst ON tender.request_id = reqst.request_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_response_school_request AS req ON tender.request_id = req.responsed_request_id \n" +
                "\tLEFT JOIN efaz_company.school_requst_images AS img ON tender.images_id = img.image_id\n" +
                "WHERE\n" +
                "\ttender.requests_category_id = ? \n" +
                "GROUP BY\n" +
                "\ttender.request_id,\n" +
                "\trequest_title,\n" +
                "\trequest_explaination,\n" +
                "\trequest_display_date,\n" +
                "\trequest_expired_date,\n" +
                "\trequest_is_available,\n" +
                "\trequest_is_conformied,\n" +
                "\ttender.school_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image,\n" +
                "\trequests_category_id,\n" +
                "\trequest_category_name,\n" +
                "\textended_payment,\n" +
                "\trequest_count,\n" +
                "\timage_one;";


        String sql = "SELECT request_id,  request_title, request_explaination, request_display_date,\n" +
                " request_expired_date, ifnull(request_is_available,0)AS request_is_available, ifnull(request_is_conformied,0)AS request_is_conformied,\n" +
                " tender.school_id, ifnull(school_name,0)AS school_name, ifnull(school_logo_image,0) AS school_logo_image , " +
                " requests_category_id, request_category_name, ifnull(extended_payment,0) AS extended_payment, ifnull(request_count, 0)AS request_count \n" +
                " FROM efaz_company.efaz_school_tender AS tender\n" +
                " LEFT JOIN efaz_company.efaz_school_profile AS profile ON tender.school_id = profile.school_id\n" +
                " Left JOIN efaz_company.efaz_school_request_category AS cat ON tender.requests_category_id = cat.request_category_id" +
                " Where tender.requests_category_id=?;";
        return jdbcTemplate.query(sql1,
                new Object[]{cat}, (resultSet, i) -> new schoolRequestWithImageDto(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(),
                        resultSet.getInt(6), resultSet.getInt(7),
                        resultSet.getInt(8), resultSet.getString(9), resultSet.getBytes(10), resultSet.getInt(11),
                        resultSet.getString(12), resultSet.getInt(13), resultSet.getInt(14), resultSet.getInt(15), resultSet.getBytes(16)));
    }


    public List<SchoolRequestsModel> filterByReceivePlace(int placeId) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  receive_palce_id=?;",
                new Object[]{placeId}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6), resultSet.getLong(7), resultSet.getLong(8),
                        resultSet.getInt(9), resultSet.getInt(10), resultSet.getLong(11), resultSet.getInt(12), resultSet.getInt(13),
                        resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));
    }


    public List<SchoolRequestDto> getRequestsBySchoolId(int id) {
        String sql = "SELECT request_id, request_title, request_count, " +
                "request_display_date FROM efaz_company.efaz_school_tender WHERE school_id=? AND request_is_available=1;";
        return jdbcTemplate.query(sql,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestDto(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getTimestamp(4).getTime()));
    }


    public List<SchoolRequestHistoryDto> getHistoryRequestsBySchoolId(int id) {

        String sql2 = "SELECT request_id, request_title, count( s.responsed_request_id) AS request_count, " +
                "request_display_date, response_date, responsed_cost, responsed_company_id, response_id \n" +
                "                FROM efaz_company.efaz_school_tender AS t \n" +
                "                LEFT JOIN efaz_company.efaz_company_response_school_request AS s ON t.request_id=s.responsed_request_id \n" +
                "                WHERE school_id=? AND s.is_aproved=1 \n" +
                "                Group BY s.responsed_request_id, request_id, request_title,request_display_date, response_date, " +
                " responsed_cost,responsed_company_id, response_id;";
        return jdbcTemplate.query(sql2,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestHistoryDto(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getDouble(6),
                        resultSet.getInt(7), resultSet.getInt(8)));
    }


    public List<SchoolRequestHistoryDtoDTO> getHistoryRequestsBySchoolId2(int id) {

        String sql2 = "SELECT\n" +
                "\trequest_id,\n" +
                "\trequest_title,\n" +
                "\tcount( s.responsed_request_id ) AS request_count,\n" +
                "\trequest_display_date,\n" +
                "\tresponse_date,\n" +
                "\tresponsed_cost,\n" +
                "\tresponsed_company_id,\n" +
                "\tresponse_id,\n" +
                "\tresponse_desc \n" +
                "FROM\n" +
                "\tefaz_company.efaz_school_tender AS t\n" +
                "\tLEFT JOIN efaz_company.efaz_company_response_school_request AS s ON t.request_id = s.responsed_request_id \n" +
                "WHERE\n" +
                "\tschool_id = ? \n" +
                "\tAND s.is_aproved = 1 \n" +
                "GROUP BY\n" +
                "\ts.responsed_request_id,\n" +
                "\trequest_id,\n" +
                "\trequest_title,\n" +
                "\trequest_display_date,\n" +
                "\tresponse_date,\n" +
                "\tresponsed_cost,\n" +
                "\tresponsed_company_id,\n" +
                "\tresponse_id;";
        return jdbcTemplate.query(sql2,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestHistoryDtoDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getDouble(6),
                        resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9)));
    }





    public List<SchoolRequestHistoryDtoDTO2> getHistoryRequestsBySchoolId3(int id) {

        String sql2 = "SELECT\n" +
                "\trequest_id,\n" +
                "\trequest_title,\n" +
                "\tcount( s.responsed_request_id ) AS request_count,\n" +
                "\trequest_display_date,\n" +
                "\tresponse_date,\n" +
                "\tresponsed_cost,\n" +
                "\tresponse_id,\n" +
                "\tresponse_desc,\n" +
                "\tcompany_id,\n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\tcompany_desc \n" +
                "FROM\n" +
                "\tefaz_company.efaz_school_tender AS t\n" +
                "\tLEFT JOIN efaz_company.efaz_company_response_school_request AS s ON t.request_id = s.responsed_request_id\n" +
                "\tLEFT JOIN efaz_company_profile AS pro ON pro.company_id = s.responsed_company_id \n" +
                "WHERE\n" +
                "\tschool_id = ? \n" +
                "\tAND s.is_aproved = 1 \n" +
                "GROUP BY\n" +
                "\ts.responsed_request_id,\n" +
                "\trequest_id,\n" +
                "\trequest_title,\n" +
                "\trequest_display_date,\n" +
                "\tresponse_date,\n" +
                "\tresponsed_cost,\n" +
                "\tresponsed_company_id,\n" +
                "\tresponse_id,\n" +
                "\tresponse_desc,\n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\tcompany_desc ;";
        return jdbcTemplate.query(sql2,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestHistoryDtoDTO2(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getDouble(6),
                        resultSet.getInt(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getBytes(11), resultSet.getString(12)));
    }





    public List<SchoolRequestHistoryDto> getOrderRequestsBySchoolId(int id) {
        String sql = "SELECT request_id, request_title, count( s.responsed_request_id) AS request_count, request_display_date, response_date, responsed_cost, responsed_company_id, response_id  " +
                "FROM efaz_company.efaz_school_tender AS t " +
                "LEFT JOIN efaz_company.efaz_company_response_school_request AS s ON t.request_id=s.responsed_request_id " +
                "WHERE school_id=? AND s.is_aproved=1 " +
                "Group BY s.responsed_request_id, request_id, request_title,request_display_date, response_date, responsed_cost,responsed_company_id, response_id; ";

        String sql2 = "SELECT request_id, request_title, count( s.responsed_request_id) AS request_count, " +
                "request_display_date, response_date, responsed_cost, responsed_company_id, response_id \n" +
                "                FROM efaz_company.efaz_school_tender AS t \n" +
                "                LEFT JOIN efaz_company.efaz_company_response_school_request AS s ON t.request_id=s.responsed_request_id \n" +
                "                WHERE school_id=? AND s.is_aproved=0 \n" +
                "                Group BY s.responsed_request_id, request_id, request_title,request_display_date, response_date, " +
                " responsed_cost,responsed_company_id, response_id;";
        return jdbcTemplate.query(sql2,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestHistoryDto(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime(), resultSet.getDouble(6),
                        resultSet.getInt(7), resultSet.getInt(8)));
    }


}
