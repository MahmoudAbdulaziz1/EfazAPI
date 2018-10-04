package com.taj.repository.admin_login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.NewLoginModelDto;
import com.taj.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 10/4/2018.
 */
@Repository
public class AdminLoginRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    JwtGenerator generator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public ObjectNode isLogged(String user_email, String user_passwords, String login_role) {
        //String encodedPassword = bCryptPasswordEncoder.encode(user_password);

        NewLoginModelDto model = null;

        if (isExistILogin(user_email)) {

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
                    "registration_organization_name\n" +
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
                        (resultSet, i) -> new NewLoginModelDto(resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(10)));
            } catch (Exception e) {
                model = null;
            }
            if (model == null) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 401);
                objectNode.put("message", "in correct password");
                return objectNode;
            } else {
                System.out.println(user_passwords);
                if (bCryptPasswordEncoder.matches(user_passwords, model.getUser_password())) {

                    Integer cnt = jdbcTemplate.queryForObject(
                            "SELECT count(*) FROM efaz_login WHERE user_email=? AND login_role=?;",//"//AND user_password = ?  ;
                            Integer.class, user_email, login_role);//, bCryptPasswordEncoder.encode(user_passwords.trim()));

                    boolean check = cnt != null && cnt > 0;

                    if (check) {
                        String token = "Token=" + generator.generate(model);
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
                        return objectNode;
                    } else {
                        ObjectNode objectNode = mapper.createObjectNode();
                        objectNode.put("state", 401);
                        objectNode.put("message", "unauthorized");
                        return objectNode;
                    }

                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("state", 400);
                    objectNode.put("message", "in correct password");
                    return objectNode;
                }
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "email with this role not exist");
            return objectNode;
        }

    }

    public boolean isExistILogin(String email) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.admin_login WHERE email=? AND (login_role='admin' OR login_role='super_admin');",
                Integer.class, email);
        return cnt != null && cnt > 0;
    }


}
