package com.taj.repository;

import com.taj.model.SchoolRequestDto;
import com.taj.model.SchoolRequestHistoryDto;
import com.taj.model.SchoolRequestsModel;
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

    public List<SchoolRequestsModel> filterByCategory(int cat) {
        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  request_category_id=?;",
                new Object[]{cat}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6), resultSet.getLong(7), resultSet.getLong(8),
                        resultSet.getInt(9), resultSet.getInt(10), resultSet.getLong(11), resultSet.getInt(12), resultSet.getInt(13),
                        resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)));
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
        String sql = "SELECT request_id, request_title, request_count, request_display_date, request_deliver_date FROM efaz_company.efaz_school_tender WHERE school_id=? AND request_is_available=1;";
        return jdbcTemplate.query(sql,
                new Object[]{id}, (resultSet, i) -> new SchoolRequestHistoryDto(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getTimestamp(4).getTime(), resultSet.getTimestamp(5).getTime()));
    }


}
