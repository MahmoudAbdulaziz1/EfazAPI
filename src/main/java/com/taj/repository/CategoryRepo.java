package com.taj.repository;

import com.taj.model.CategoryModel;
import com.taj.model.CompanyProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Taj 51 on 6/10/2018.
 */
@Repository
public class CategoryRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CategoryModel> getCategories(){
        return jdbcTemplate.query("SELECT * FROM efaz_company_category;",
                (resultSet, i) -> new CategoryModel(resultSet.getInt(1),resultSet.getString(2)));
    }


    public CategoryModel getCategory(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company_category WHERE  category_id=?;",
                new Object[]{id},(resultSet, i) -> new CategoryModel(resultSet.getInt(1),resultSet.getString(2)));
    }
}
