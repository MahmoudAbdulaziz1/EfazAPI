package com.taj.repository;

import com.taj.model.SchoolRequestCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@Repository
public class SchoolRequestCategoryRepo {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addSchoolRequestCategories(String categoryName) {
        return jdbcTemplate.update("INSERT INTO efaz_school_request_category VALUES (?,?)", null, categoryName);
    }

    public List<SchoolRequestCategoryModel> getSchoolRequestCategories() {
        return jdbcTemplate.query("SELECT * FROM efaz_school_request_category",
                ((resultSet, i) -> new SchoolRequestCategoryModel(resultSet.getInt(1), resultSet.getString(2))));
    }

    public SchoolRequestCategoryModel getSchoolRequestCategory(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_school_request_category WHERE request_category_id=?", new Object[]{id},
                ((resultSet, i) -> new SchoolRequestCategoryModel(resultSet.getInt(1), resultSet.getString(2))));
    }

    public int updateSchoolRequestCategory(int id, String name) {
        return jdbcTemplate.update("UPDATE efaz_school_request_category SET request_category_name=? WHERE request_category_id=?",
                name, id);
    }

    public int deleteSchoolRequestCategory(int id) {
        return jdbcTemplate.update("DELETE FROM efaz_school_request_category WHERE request_category_id=?", id);
    }


}
