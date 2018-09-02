package com.taj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 9/2/2018.
 */
@Repository
public class CategoryCountDemoREpo {


    @Autowired
    JdbcTemplate jdbcTemplate;


    public int deleteCat(int id){
        return jdbcTemplate.update("DELETE FROM efaz_company.takataf_request_cat_count where id=?;", id);
    }


}
