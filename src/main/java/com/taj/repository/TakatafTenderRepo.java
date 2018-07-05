package com.taj.repository;

import com.taj.model.SchoolRequestsModel;
import com.taj.model.TakatafTenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@Repository
public class TakatafTenderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTender(int tender_id, byte[] tender_logo, String tender_title, String tender_explain, Timestamp tender_display_date, Timestamp tender_expire_date,
                         Timestamp tender_deliver_date, int tender_company_id, int tender_is_confirmed, int tender_is_available, int tender_f_id, int tender_s_id,
                         int tender_t_id, int tender_cat_id) {
        return jdbcTemplate.update("INSERT INTO takatf_tender VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)", tender_id, tender_logo, tender_title, tender_explain,
                tender_display_date, tender_expire_date, tender_deliver_date,  tender_company_id, tender_is_confirmed, tender_is_available, tender_f_id, tender_s_id,
                 tender_t_id, tender_cat_id);
    }

    public List<TakatafTenderModel> getTenders() {
        return jdbcTemplate.query("SELECT * FROM takatf_tender;",
                (resultSet, i) -> new TakatafTenderModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11),
                        resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
    }
    public TakatafTenderModel getTender(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM takatf_tender  WHERE tender_id=?;", new Object[]{id},
                (resultSet, i) -> new TakatafTenderModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11),
                        resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
    }

    public List<TakatafTenderModel> getTenderByCompany(int id) {
        return jdbcTemplate.query("SELECT * FROM takatf_tender  WHERE tender_company_id=?;", new Object[]{id},
                (resultSet, i) -> new TakatafTenderModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11),
                        resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
    }

    public List<TakatafTenderModel> getTenderByCategory(int id) {
        return jdbcTemplate.query("SELECT * FROM takatf_tender  WHERE tender_cat_id=?;", new Object[]{id},
                (resultSet, i) -> new TakatafTenderModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11),
                        resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
    }
    public List<TakatafTenderModel> getTenderByIsAvailable(int id) {
        return jdbcTemplate.query("SELECT * FROM takatf_tender  WHERE tender_is_available=?;", new Object[]{id},
                (resultSet, i) -> new TakatafTenderModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11),
                        resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
    }

    public List<TakatafTenderModel> getTenderByIsConfirm(int id) {
        return jdbcTemplate.query("SELECT * FROM takatf_tender  WHERE tender_is_confirmed=?;", new Object[]{id},
                (resultSet, i) -> new TakatafTenderModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11),
                        resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
    }


//    public SchoolRequestsModel getRequestByID(int id) {
//        return jdbcTemplate.queryForObject("SELECT * FROM efaz_school_tender WHERE  request_id=?;",
//                new Object[]{id}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2),
//                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
//                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getTimestamp(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
//    }
//
//    public List<SchoolRequestsModel> getRequestsByID(int id) {
//        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  school_id=?;",
//                new Object[]{id}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2),
//                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
//                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getTimestamp(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
//    }
//
//
//    public int updateRequest(int request_id, byte[] request_details_file, String request_title, String request_explaination,
//                             Timestamp request_display_date, Timestamp request_expired_date, Timestamp request_deliver_date,
//                             Timestamp request_payment_date, int request_is_available, int request_is_conformied, int school_id,
//                             int request_category_id, int receive_palce_id, int extended_payment) {
//
//        return jdbcTemplate.update("update efaz_school_tender set request_details_file=?," + "request_title=?," +
//                        "request_explaination=?," + " request_display_date=?, request_expired_date=?, request_deliver_date=?," +
//                        "request_payment_date=?, request_is_available=?, request_is_conformied=?, school_id=?, request_category_id=?, receive_palce_id=?, extended_payment=?" +
//                        " where request_id=?", request_details_file, request_title, request_explaination, request_display_date
//                , request_expired_date, request_deliver_date, request_payment_date, request_is_available, request_is_conformied, school_id, request_category_id,
//                receive_palce_id, extended_payment, request_id);
//
//    }
//
//    public int deleteSchoolRequest(int id) {
//        return jdbcTemplate.update("DELETE FROM efaz_school_tender WHERE request_id=?", id);
//    }
//
//    public List<SchoolRequestsModel> filterByIsAvailable(int isAvailable) {
//        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  request_is_available=?;",
//                new Object[]{isAvailable}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2),
//                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
//                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getTimestamp(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
//    }
//
//
//    public List<SchoolRequestsModel> filterByIsConfirmed(int isConfirmed) {
//        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  request_is_conformied=?;",
//                new Object[]{isConfirmed}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2),
//                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
//                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getTimestamp(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
//    }
//
//
//    public List<SchoolRequestsModel> filterByTitle(String title) {
//        String sql = "SELECT * FROM efaz_school_tender WHERE  request_title LIKE ?;";
//        return jdbcTemplate.query(sql, new String[] { "%" + title + "%" },(resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2),
//                resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
//                resultSet.getInt(8), resultSet.getInt(9), resultSet.getTimestamp(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
//    }
//
//    public List<SchoolRequestsModel> filterByExplain(String explain) {
//        String sql = "SELECT * FROM efaz_school_tender WHERE  request_explaination LIKE ?;";
//        return jdbcTemplate.query(sql, new String[] { "%" + explain + "%" },(resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2),
//                resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
//                resultSet.getInt(8), resultSet.getInt(9), resultSet.getTimestamp(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
//    }
//
//
////    public List<SchoolRequestsModel> filterByExplain(String explain) {
////        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  request_explaination LIKE ?;",
////                new Object[]{explain}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2),
////                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
////                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getTimestamp(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
////    }
//
//    public List<SchoolRequestsModel> filterByCategory(int cat) {
//        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  request_category_id=?;",
//                new Object[]{cat}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2),
//                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
//                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getTimestamp(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
//    }
//
//    public List<SchoolRequestsModel> filterByReceivePlace(int placeId) {
//        return jdbcTemplate.query("SELECT * FROM efaz_school_tender WHERE  receive_palce_id=?;",
//                new Object[]{placeId}, (resultSet, i) -> new SchoolRequestsModel(resultSet.getInt(1), resultSet.getBytes(2),
//                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
//                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getTimestamp(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
//    }


}
