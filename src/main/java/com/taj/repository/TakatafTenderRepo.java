package com.taj.repository;

import com.taj.model.TakatafTenderModel;
import com.taj.model.Takataf_schoolApplayCollectiveTender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@Repository
public class TakatafTenderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTender(byte[] tender_logo, String tender_title, String tender_explain, Timestamp tender_display_date, Timestamp tender_expire_date,
                         Timestamp tender_deliver_date, int tender_company_id, int tender_is_confirmed, int tender_is_available, int tender_f_id, int tender_s_id,
                         int tender_t_id, int tender_cat_id) {
        return jdbcTemplate.update("INSERT INTO takatf_tender VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)", null, tender_logo, tender_title, tender_explain,
                tender_display_date, tender_expire_date, tender_deliver_date, tender_company_id, tender_is_confirmed, tender_is_available, tender_f_id, tender_s_id,
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

    public List<TakatafTenderModel> getTenderByTitle(String title) {
        return jdbcTemplate.query("SELECT * FROM takatf_tender WHERE tender_title LIKE ?;", new String[]{"%" + title + "%"},
                (resultSet, i) -> new TakatafTenderModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11),
                        resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
    }

    public List<TakatafTenderModel> getTenderByExplain(String explain) {
        return jdbcTemplate.query("SELECT * FROM takatf_tender WHERE tender_explain LIKE ?;", new String[]{"%" + explain + "%"},
                (resultSet, i) -> new TakatafTenderModel(resultSet.getInt(1), resultSet.getBytes(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11),
                        resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14)));
    }

    public int updateTender(int tender_id, byte[] tender_logo,
                            String tender_title,
                             String tender_explain,
                            long tender_display_date, long tender_expire_date, int tender_is_confirmed, int tender_is_available,
                             long tender_company_display_date,  long tender_company_expired_date,
                            List<Takataf_schoolApplayCollectiveTender> category) {
        int ten =  jdbcTemplate.update("UPDATE efaz_company.takatf_tender SET tender_logo=?, tender_title=?, tender_explain=?," +
                        " tender_display_date=?, tender_expire_date=?, tender_is_confirmed=?, tender_is_available=?, tender_company_expired_date=?," +
                        " tender_company_display_date=? ", tender_logo, tender_title, tender_explain,
                tender_display_date, tender_expire_date, tender_is_confirmed, tender_is_available, tender_company_display_date,tender_company_expired_date , tender_id);
        int del = jdbcTemplate.update("Delete from efaz_company.tkatf_tender_catgory_request where t_tender_id = ?;", tender_id);

        for (int i = 0; i < category.size(); i++) {
            int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                    Integer.class, "%" + category.get(i).getCat_name().trim() + "%");

            jdbcTemplate.update("INSERT INTO efaz_company.tkatf_tender_catgory_request VALUES  (?,?,?)", null, tender_id, categorys);
        }

        return ten;

    }


    public int deleteTender(int id) {
        return jdbcTemplate.update("DELETE FROM takatf_tender WHERE tender_id=?", id);
    }


    public int deleteTenderRequest(int id) {
        return jdbcTemplate.update("DELETE FROM efaz_company.tkatf_tender_catgory_request WHERE id=?", id);
    }


}
