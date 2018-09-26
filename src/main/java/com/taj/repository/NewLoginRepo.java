package com.taj.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taj.model.LoginModel;
import com.taj.model.NewRegisterModel;
import com.taj.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Created by User on 9/11/2018.
 */
@Repository
public class NewLoginRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    JwtGenerator generator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JavaMailSender sender;


    public LoginModel loginUser(String userEmail, String userPassword, int isActive, String loginRole, String loginToken, String city, String area) {


        NewRegisterModel model = null;
        if (isExist(userEmail, loginRole)) {

            model = jdbcTemplate.queryForObject("select * from efaz_registration WHERE registeration_email=?"
                    , new Object[]{userEmail},
                    (resultSet, i) -> new NewRegisterModel(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                            resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10),
                            resultSet.getTimestamp(11).getTime(), resultSet.getString(12), resultSet.getString(13),
                            resultSet.getInt(14), resultSet.getInt(15), resultSet.getFloat(16), resultSet.getFloat(17)));
            if (bCryptPasswordEncoder.matches(userPassword, model.getRegisterationPassword())) {
                if (isActive == 1) {
                    String encodedPassword = bCryptPasswordEncoder.encode(userPassword);
                    KeyHolder key = new GeneratedKeyHolder();
                    jdbcTemplate.update(new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            final PreparedStatement ps = connection.prepareStatement("INSERT INTO efaz_login VALUES (?,?,?,?,?,?,?,?,?)",
                                    Statement.RETURN_GENERATED_KEYS);
                            ps.setString(1, null);
                            ps.setString(2, userEmail);
                            ps.setString(3, encodedPassword);/////////
                            ps.setInt(4, isActive);
                            ps.setString(5, loginRole);
                            ps.setString(6, loginToken);
                            ps.setString(7, city);
                            ps.setString(8, area);
                            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                            return ps;
                        }

                    }, key);
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

    public boolean isExist(String email, String loginRole) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_registration WHERE registeration_email=? AND registration_role=?;",
                Integer.class, email, loginRole);
        return cnt != null && cnt > 0;
    }

    public LoginModel getLoggedUser(int id) {

        return jdbcTemplate.queryForObject("select * from efaz_login WHERE login_id=?;", new Object[]{id},
                (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6)));
    }


}
