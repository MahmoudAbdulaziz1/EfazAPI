package com.taj.repository;

import com.taj.model.LoginModel;
import com.taj.model.RegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

    public LoginModel loginUser(String user_email, String user_password, int is_active, int login_type) {


        RegistrationModel model = null;
        if (isExist(user_email)) {

            model = jdbcTemplate.queryForObject("select * from efaz_registration WHERE registeration_email=?"
                    , new Object[]{user_email},
                    (resultSet, i) -> new RegistrationModel(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                            resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10)));
            if (bCryptPasswordEncoder.matches(user_password, model.getRegisteration_password())) {
                //if (user_password.equals(model.getRegisteration_password())){
                if (is_active == 1) {
                    String encodedPassword = bCryptPasswordEncoder.encode(user_password);
                    KeyHolder key = new GeneratedKeyHolder();
                    jdbcTemplate.update(new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            final PreparedStatement ps = connection.prepareStatement("INSERT INTO efaz_login VALUES (?,?,?,?,?)",
                                    Statement.RETURN_GENERATED_KEYS);
                            ps.setString(1, null);
                            ps.setString(2, user_email);
                            ps.setString(3, encodedPassword);/////////
                            ps.setInt(4, is_active);
                            ps.setInt(5, login_type);
                            return ps;
                        }

                    }, key);
                    //int log_id = jdbcTemplate.update("INSERT INTO efaz_login VALUES (?,?,?,?,?)", null, user_email,
                    //        encodedPassword, is_active, login_type);
                    return getLoggedUser(key.getKey().intValue());
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public boolean isExist(String email) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_registration WHERE registeration_email=?;",
                Integer.class, email);
        return cnt != null && cnt > 0;
    }

    public boolean isLogged(String user_email, String user_passwords, int login_type) {
        //String encodedPassword = bCryptPasswordEncoder.encode(user_password);

        LoginModel model = null;
        if (isExist(user_email)) {

            model = jdbcTemplate.queryForObject("select * from efaz_login WHERE user_email=?"
                    , new Object[]{user_email},
                    (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
            if (bCryptPasswordEncoder.matches(user_passwords, model.getUser_password())) {

                Integer cnt = jdbcTemplate.queryForObject(
                        "SELECT count(*) FROM efaz_login WHERE user_email=? AND login_type=?;",//"//AND user_password = ?  ;
                        Integer.class, user_email, login_type);//, bCryptPasswordEncoder.encode(user_passwords.trim()));
                return cnt != null && cnt > 0;

            } else {
                return false;
            }
        } else {
            return false;
        }

    }


    public LoginModel getLoggedId(String user_email, String user_passwords, int login_type) {
        LoginModel model = null;
        if (isExist(user_email)) {

            model = jdbcTemplate.queryForObject("select * from efaz_login WHERE user_email=?"
                    , new Object[]{user_email},
                    (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
            if (bCryptPasswordEncoder.matches(user_passwords, model.getUser_password())) {


                return jdbcTemplate.queryForObject("select * from efaz_login WHERE user_email=? AND login_type=?;", new Object[]{user_email, login_type},
                        (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
//                Integer cnt = jdbcTemplate.queryForObject(
//                        "SELECT login_id FROM efaz_login WHERE user_email=? AND login_type=?;",//"//AND user_password = ?  ;
//                        Integer.class, user_email, login_type);//, bCryptPasswordEncoder.encode(user_passwords.trim()));
//                return cnt ;

            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<LoginModel> getLoggedUsers() {
        return jdbcTemplate.query("select * from efaz_login;",
                (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
    }

//    public int deleteLoggedUser(int id){
//        return jdbcTemplate.update("DELETE FROM efaz_login WHERE login_id=?", id);
//    }

    public LoginModel getLoggedUser(int id) {
        return jdbcTemplate.queryForObject("select * from efaz_login WHERE login_id=?;", new Object[]{id},
                (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
    }


    public int updatePassword(int login_id, String user_email, String user_password) {
        String encodedPassword = bCryptPasswordEncoder.encode(user_password);
        jdbcTemplate.update("update efaz_login set user_password=? where login_id=?", encodedPassword, login_id);
        return jdbcTemplate.update("update efaz_registration set registeration_password=? where registeration_email=? AND registration_isActive=1", encodedPassword, user_email);
    }

    public void updateActiveState(int login_id, int is_active) {
        jdbcTemplate.update("update efaz_login set is_active=? where login_id=?", is_active, login_id);
    }

    public void deleteUser(int id, String user_email) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        jdbcTemplate.update("DELETE FROM efaz_login WHERE login_id=?;", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        jdbcTemplate.update("DELETE FROM efaz_registration WHERE  registeration_email=? AND registration_isActive=1", user_email);

    }


    public List<LoginModel> getInActiveCompanies() {
        return jdbcTemplate.query("SELECT * FROM efaz_login WHERE is_active=0;",
                (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
    }

    public List<LoginModel> getActiveCompanies() {
        return jdbcTemplate.query("SELECT * FROM efaz_login WHERE is_active=1;",
                (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
    }

    public void activeLogin(int id) {
        jdbcTemplate.update("update efaz_login set is_active=1 WHERE login_id=?", id);
    }

    public void inActiveLogin(int id) {
        jdbcTemplate.update("update efaz_login set is_active=0 WHERE login_id=?", id);
    }

}
