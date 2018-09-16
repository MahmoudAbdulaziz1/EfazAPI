package com.taj.repository;

import com.taj.model.CityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 9/16/2018.
 */
@Repository
public class CityRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addCity(String cityName, int cityAreaId) {
        return jdbcTemplate.update("INSERT INTO efaz_company.city VALUES (?,?,?);", null, cityName, cityAreaId);
    }

    public List<CityModel> getCities() {
        return jdbcTemplate.query("SELECT * FROM efaz_company.city;",
                (resultSet, i) -> new CityModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
    }

    public CityModel getCityById(int cityId) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company.city WHERE city_id=?;", new Object[]{cityId},
                (resultSet, i) -> new CityModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
    }
    public int updateCity(int cityId, String cityName, int city_area_id){
        return jdbcTemplate.update("UPDATE efaz_company.city SET city_name=? , set city_area_id=? WHERE city_id=?;", cityName, city_area_id, cityId);
    }
    public int deleteCity(int cityId){
        return jdbcTemplate.update("DELETE FROM efaz_company.city WHERE city_id=?", cityId);
    }

    public boolean checkIfExist(int cityId) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_company.city WHERE city_id=?;",
                Integer.class, cityId);
        return cnt != null && cnt > 0;
    }


}
