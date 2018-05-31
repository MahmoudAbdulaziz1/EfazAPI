package com.taj.repository;

import com.taj.model.LoginModel;
import com.taj.model.RegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MahmoudAhmed on 5/30/2018.
 */
@Repository
public class LoginRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public int loginUser(String user_email, String user_password, int is_active, int login_type){

        RegistrationModel model = null;
        if (isExist(user_email)){

            model = jdbcTemplate.queryForObject("select * from efaz_registration WHERE registeration_email=?"
                    , new Object[]{user_email},
                    (resultSet, i) -> new RegistrationModel(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),
                            resultSet.getString(7),resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10)));
            if (bCryptPasswordEncoder.matches(user_password, model.getRegisteration_password())){
            //if (user_password.equals(model.getRegisteration_password())){
                if (is_active ==1){
                    String encodedPassword = bCryptPasswordEncoder.encode(user_password);
                    int log_id = jdbcTemplate.update("INSERT INTO efaz_login VALUES (?,?,?,?,?)", null, user_email,
                            encodedPassword, is_active, login_type);

                    return 1;
                }else {
                    return 0;
                }
            }else {
                return 0;
            }
        }else {
            return 0;
        }

    }

    public boolean isExist(String email){
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_registration WHERE registeration_email=?;",
                Integer.class, email);
        return cnt != null && cnt > 0;
    }

    public List<LoginModel> getLoggedUsers(){
        return jdbcTemplate.query("select * from efaz_login;" ,
                (resultSet, i) -> new LoginModel(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
    }

    public int deleteLoggedUser(int id){
        return jdbcTemplate.update("DELETE FROM efaz_login WHERE login_id=?", id);
    }

    public LoginModel getLoggedUser(int id){
        return jdbcTemplate.queryForObject("select * from efaz_login WHERE login_id=?;" ,new Object[]{id},
                (resultSet, i) -> new LoginModel(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
    }

}
