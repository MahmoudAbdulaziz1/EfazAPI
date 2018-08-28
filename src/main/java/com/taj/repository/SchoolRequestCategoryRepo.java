package com.taj.repository;

import com.taj.model.SchoolRequestCategoryModel;
import com.taj.model.schoolCategoriesToWEBSITE;
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


    public List<schoolCategoriesToWEBSITE> getSchoolRequestCategoriesForWeb() {
        String sql = "SELECT " +
                "request_category_id, request_category_name, Count(request_category_id) as request_count, Count(Distinct school_id) as school_count, Count(of.response_id) as offer_count FROM efaz_school_request_category as sc " +
                "LEFT JOIN " +
                "    efaz_company.efaz_school_tender as t  ON sc.request_category_id = t.requests_category_id " +
                "LEFT JOIN " +
                "     efaz_company.efaz_company_response_school_request as of  ON t.request_id = of.responsed_request_id " +
                "" +
                "    Group by request_category_id;";
        return jdbcTemplate.query(sql,
                ((resultSet, i) -> new schoolCategoriesToWEBSITE(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5))));
    }


}
