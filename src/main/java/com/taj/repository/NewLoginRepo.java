package com.taj.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.LoginModel;
import com.taj.model.NewLoginModelDto;
import com.taj.model.NewRegisterModel;
import com.taj.model.login.NewLoginDto2;
import com.taj.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    public LoginModel loginUser(String userEmail, String userPassword, int isActive, String loginRole, String loginToken, String city, String area, float lng, float lat) {


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
                            final PreparedStatement ps = connection.prepareStatement("INSERT INTO efaz_login VALUES (?,?,?,?,?,?,?,?,?,?,?)",
                                    Statement.RETURN_GENERATED_KEYS);
                            ps.setString(1, null);
                            ps.setString(2, userEmail);
                            ps.setString(3, encodedPassword);/////////
                            ps.setInt(4, isActive);
                            ps.setString(5, loginRole);
                            ps.setString(6, loginToken);
                            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                            ps.setString(8, city);
                            ps.setString(9, area);
                            ps.setFloat(10, lng);
                            ps.setFloat(11, lat);
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


    public ResponseEntity<ObjectNode> isLogged(String user_email, String user_passwords, String login_role) {

        NewLoginDto2 model = null;

        if (isExist(user_email, login_role)) {

            String sql = "SELECT\n" +
                    "login_id,\n" +
                    "user_email,\n" +
                    "user_password,\n" +
                    "is_active,\n" +
                    "login_role,\n" +
                    "login_token,\n" +
                    "login_date,\n" +
                    "city,\n" +
                    "area,\n" +
                    "registration_organization_name," +
                    "registration_id\n" +
                    "FROM\n" +
                    "\tefaz_login AS log\n" +
                    "\tLEFT JOIN efaz_company.efaz_registration AS reg ON log.user_email = reg.registeration_email \n" +
                    "\tAND log.login_role = reg.registration_role \n" +
                    "\tAND reg.registration_isActive = 1 \n" +
                    "WHERE\n" +
                    "\tuser_email = ? \n" +
                    "\tAND login_role = ?";
            try {
                model = jdbcTemplate.queryForObject(sql
                        , new Object[]{user_email, login_role},
                        (resultSet, i) -> new NewLoginDto2(resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getTimestamp(7).getTime(),
                                resultSet.getString(6), resultSet.getString(10), resultSet.getInt(11)));
                System.out.println("done        ssss "+ model.getUser_email());

            } catch (Exception e) {
                model = null;
                System.out.println("null                      ssssss");

            }
            if (model == null) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 401);
                objectNode.put("message", "in correct password");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            } else {
                System.out.println(user_passwords);
                if (bCryptPasswordEncoder.matches(user_passwords, model.getUser_password())) {

                    Integer cnt = jdbcTemplate.queryForObject(
                            "SELECT count(*) FROM efaz_login WHERE user_email=? AND login_role=?;",//"//AND user_password = ?  ;
                            Integer.class, user_email, login_role);//, bCryptPasswordEncoder.encode(user_passwords.trim()));

                    boolean check = cnt != null && cnt > 0;

                    if (check) {
                        NewLoginModelDto dto = new NewLoginModelDto(model.getLogin_id(), model.getUser_email(), model.getUser_password(),
                                model.getIs_active(), model.getLogin_role(), model.getLogin_date(), model.getLogin_token(), model.getRegistration_organization_name());
                        String token = "Token=" + generator.generate(dto);
                        jdbcTemplate.update("UPDATE efaz_company.efaz_login SET login_token = ? WHERE login_id = ?", token, model.getLogin_id());
                        ObjectNode objectNode = mapper.createObjectNode();
                        objectNode.put("state", 201);
                        objectNode.put("login_id", model.getLogin_id());
                        objectNode.put("user_email", model.getUser_email());
                        objectNode.put("user_password", model.getUser_password());
                        objectNode.put("is_active", model.getIs_active());
                        objectNode.put("login_role", model.getLogin_role());
                        objectNode.put("login_token", token);
                        objectNode.put("org_name", model.getRegistration_organization_name());
                        objectNode.put("register_id", model.getRegistration_id());
                        return ResponseEntity.status(HttpStatus.OK).body(objectNode);
                    } else {
                        ObjectNode objectNode = mapper.createObjectNode();
                        objectNode.put("state", 401);
                        objectNode.put("message", "unauthorized");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                    }

                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("state", 400);
                    objectNode.put("message", "in correct password");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                }
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "email with this role not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }


}
