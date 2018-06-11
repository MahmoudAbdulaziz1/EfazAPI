package com.taj.repository;

import com.taj.model.LoginDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Taj 51 on 6/11/2018.
 */
@Repository
public class LoginDetailsRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addLoginDetails(int login_id, int is_school, String lgoin_time, String ip_address, int is_mobill){
        jdbcTemplate.update("INSERT INTO login_details VALUES (?,?,?,?,?,?)", null, login_id, is_school,
                lgoin_time, ip_address, is_mobill);

    }

    public List<LoginDetailsModel> getDetails(){
        return  jdbcTemplate.query("SELECT * FROM login_details;",
                (resultSet, i) ->new LoginDetailsModel(resultSet.getInt(1),resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getInt(6)));
    }

    public LoginDetailsModel getDetail(int id){
        return  jdbcTemplate.queryForObject("SELECT * FROM login_details WHERE details_id=?;", new Object[]{id},
                (resultSet, i) ->new LoginDetailsModel(resultSet.getInt(1),resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getInt(6)));
    }
}
