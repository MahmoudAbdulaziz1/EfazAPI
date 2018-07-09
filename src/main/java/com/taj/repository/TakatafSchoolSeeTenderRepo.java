package com.taj.repository;

import com.taj.model.TakatafSchoolSeeTenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@Repository
public class TakatafSchoolSeeTenderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public int addSeen(int seen_id, int seen_tender_id, int seen_school_id) {
        return jdbcTemplate.update("INSERT INTO takatf_school_see_tender VALUES (?,?,?)", seen_id, seen_tender_id, seen_school_id);
    }

    public List<TakatafSchoolSeeTenderModel> getTendersSeen() {
        return jdbcTemplate.query("SELECT * FROM takatf_school_see_tender;",
                ((resultSet, i) -> new TakatafSchoolSeeTenderModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public TakatafSchoolSeeTenderModel getTenderSeen(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM takatf_school_see_tender WHERE seen_id=?;", new Object[]{id},
                ((resultSet, i) -> new TakatafSchoolSeeTenderModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }


    public List<TakatafSchoolSeeTenderModel> getTendersSeenBySchool(int schoolId) {
        return jdbcTemplate.query("SELECT * FROM takatf_school_see_tender WHERE seen_school_id=?;", new Object[]{schoolId},
                ((resultSet, i) -> new TakatafSchoolSeeTenderModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public List<TakatafSchoolSeeTenderModel> getTendersSeenByTender(int tenderId) {
        return jdbcTemplate.query("SELECT * FROM takatf_school_see_tender WHERE seen_tender_id=?;", new Object[]{tenderId},
                ((resultSet, i) -> new TakatafSchoolSeeTenderModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))));
    }

    public int updateTendersSeen(int seen_id, int seen_tender_id, int seen_school_id) {
        return jdbcTemplate.update("UPDATE takatf_school_see_tender SET seen_tender_id=?, seen_school_id=? WHERE seen_id=?", seen_tender_id, seen_school_id, seen_id);
    }

    public int deleteTendersSeen(int seen_id) {
        return jdbcTemplate.update("DELETE FROM takatf_school_see_tender WHERE seen_id=?", seen_id);
    }
}
