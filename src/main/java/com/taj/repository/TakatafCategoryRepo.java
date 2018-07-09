package com.taj.repository;

import com.taj.model.TakatafCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@Repository
public class TakatafCategoryRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTkatafCategories(String categoryName) {
        return jdbcTemplate.update("INSERT INTO takatf_category VALUES (?,?)", null, categoryName);
    }

    public List<TakatafCategoryModel> getTakatafCategories() {
        return jdbcTemplate.query("SELECT * FROM takatf_category",
                ((resultSet, i) -> new TakatafCategoryModel(resultSet.getInt(1), resultSet.getString(2))));
    }

    public TakatafCategoryModel getTakatafCategory(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM takatf_category WHERE cat_id=?", new Object[]{id},
                ((resultSet, i) -> new TakatafCategoryModel(resultSet.getInt(1), resultSet.getString(2))));
    }

    public int updateTakatafCategory(int id, String name) {
        return jdbcTemplate.update("UPDATE takatf_category SET cat_name=? WHERE cat_id=?",
                name, id);
    }

    public int deleteTakatafCategory(int id) {
        return jdbcTemplate.update("DELETE FROM takatf_category WHERE cat_id=?", id);
    }
}
