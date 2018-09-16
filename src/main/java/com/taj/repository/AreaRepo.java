package com.taj.repository;

import com.taj.model.AreaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 9/16/2018.
 */
@Repository
public class AreaRepo {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addArea(String areaName) {
        return jdbcTemplate.update("INSERT INTO efaz_company.area VALUES (?,?);", null, areaName);
    }

    public List<AreaModel> getAreas() {
        return jdbcTemplate.query("SELECT * FROM efaz_company.area;",
                (resultSet, i) -> new AreaModel(resultSet.getInt(1), resultSet.getString(2)));
    }

    public AreaModel getareaById(int areaId) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company.area WHERE area_id=?;", new Object[]{areaId},
                (resultSet, i) -> new AreaModel(resultSet.getInt(1), resultSet.getString(2)));
    }

    public int updateArea(int areaId, String areaName) {
        return jdbcTemplate.update("UPDATE efaz_company.area SET area_name=? WHERE area_id=?;", areaName, areaId);
    }

    public int deleteArea(int areaId) {
        return jdbcTemplate.update("DELETE FROM efaz_company.area WHERE area_id=?", areaId);
    }

    public boolean checkIfExist(int areaId) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_company.area WHERE area_id=?;",
                Integer.class, areaId);
        return cnt != null && cnt > 0;
    }
}
