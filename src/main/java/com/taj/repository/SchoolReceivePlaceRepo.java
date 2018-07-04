package com.taj.repository;

import com.taj.model.SchoolReceivePlaceModel;
import com.taj.model.SchoolRequestCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/4/2018.
 */
@Repository
public class SchoolReceivePlaceRepo {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addSchoolPlace(String placeName) {
        return jdbcTemplate.update("INSERT INTO efaz_school_request_recive_places VALUES (?,?)", null, placeName);
    }

    public List<SchoolReceivePlaceModel> getSchoolPlaces() {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_recive_places",
                ((resultSet, i) -> new SchoolReceivePlaceModel(resultSet.getInt(1), resultSet.getString(2))));
    }

    public SchoolReceivePlaceModel getPlace(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_school_request_recive_places WHERE place_id=?", new Object[]{id},
                ((resultSet, i) -> new SchoolReceivePlaceModel(resultSet.getInt(1), resultSet.getString(2))));
    }

    public int updateSchoolPlace(int id, String name) {
        return jdbcTemplate.update("UPDATE efaz_school_request_recive_places SET place_name=? WHERE place_id=?",
                name, id);
    }

    public int deleteSchoolPlace(int id) {
        return jdbcTemplate.update("DELETE FROM efaz_school_request_recive_places WHERE place_id=?", id);
    }

}
