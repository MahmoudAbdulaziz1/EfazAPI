package com.taj.repository;

import com.taj.model.RegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by MahmoudAhmed on 5/30/2018.
 */
@Repository
public class RegistrationRepo {


    @Autowired
    JdbcTemplate jdbcTemplate;


    public void addUser(String email, String password, String userName, String phoneNumber, String companyName
                      , String address , String website, int isSchool, int isActive){

        jdbcTemplate.update("INSERT INTO efaz_registration VALUES (?,?,?,?,?,?,?,?,?,?)", null, email, password, userName, phoneNumber
                , companyName, address, website, isSchool, isActive);
    }


    public List<RegistrationModel> getUsers(){
        return jdbcTemplate.query("select * from efaz_registration;" ,
                (resultSet, i) -> new RegistrationModel(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10)));
    }

    public RegistrationModel getUser(int id){
        return jdbcTemplate.queryForObject("SELECT * From efaz_registration WHERE registration_id=?", new Object[]{id},
                ((resultSet, i) -> new RegistrationModel(resultSet.getInt(1),resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10))));
    }

    public int updateUser(int id, String email, String password, String userName, String phoneNumber, String companyName
            , String address , String website, int isSchool, int isActive){
        return jdbcTemplate.update("update efaz_registration set registeration_email=?," +
                "registeration_password=?, registeration_username=?, registeration_phone_number=?," +
                "registration_organization_name=?, registration_address_desc=?, registration_website_url=?," +
                "registration_is_school=?, registration_isActive=?" +
                " where registration_id=?", email, password, userName, phoneNumber
                , companyName, address, website, isSchool, isActive, id);
    }

    public int deleteUser(int id){
        return jdbcTemplate.update("DELETE FROM efaz_registration WHERE registration_id=?", id);
    }

}
