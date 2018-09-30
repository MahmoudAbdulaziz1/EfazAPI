package com.taj.repository.admin_login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.NewLoginModelDto;
import com.taj.model.admin_login.AdminLogin;
import com.taj.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

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


    public ObjectNode isLogged(String user_email, String user_passwords) {
        //String encodedPassword = bCryptPasswordEncoder.encode(user_password);

        AdminLogin model = null;

        if (isExistILogin(user_email)) {

            String sql = "SELECT\n" +
                    "id,\n" +
                    "email,\n" +
                    "password,\n" +
                    "is_active,\n" +
                    "role,\n" +
                    "token,\n" +
                    "date,\n" +
                    "city,\n" +
                    "area, lng, lat, \n" +
                    " name\n" +
                    "FROM\n" +
                    "\tadmin_login AS log\n" +
                    " WHERE\n" +
                    "\temail = ? \n" +
                    "\tAND (role='admin' OR role='super_admin'); ";
            try {
                model = jdbcTemplate.queryForObject(sql
                        , new Object[]{user_email},
                        (resultSet, i) -> new AdminLogin(resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5),
                                resultSet.getString(6), resultSet.getTimestamp(7).getTime(), resultSet.getString(8),
                                resultSet.getString(9), resultSet.getFloat(10), resultSet.getFloat(11), resultSet.getString(12)));
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
                if (bCryptPasswordEncoder.matches(user_passwords, model.getPassword())) {

                    Integer cnt = jdbcTemplate.queryForObject(
                            "SELECT count(*) FROM efaz_company.admin_login WHERE email=? AND (role='admin' OR role='super_admin');",//"//AND user_password = ?  ;
                            Integer.class, user_email);//, bCryptPasswordEncoder.encode(user_passwords.trim()));

                    boolean check = cnt != null && cnt > 0;

                    if (check) {
                        NewLoginModelDto dto = new NewLoginModelDto(model.getId(), model.getEmail(), model.getPassword(), model.getIs_active(), model.getRole(), model.getDate(), model.getToken(), model.getName());
                        String token = "Token=" + generator.generate(dto);
                        jdbcTemplate.update("UPDATE efaz_company.admin_login SET token = ? WHERE id = ?", token, model.getId());
                        ObjectNode objectNode = mapper.createObjectNode();
                        objectNode.put("state", 201);
                        objectNode.put("login_id", model.getId());
                        objectNode.put("user_email", model.getEmail());
                        objectNode.put("user_password", model.getPassword());
                        objectNode.put("is_active", model.getIs_active());
                        objectNode.put("login_role", model.getRole());
                        objectNode.put("login_token", token);
                        objectNode.put("org_name", model.getName());
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
                "SELECT count(*) FROM efaz_company.admin_login WHERE email=? AND (role='admin' OR role='super_admin');",
                Integer.class, email);
        return cnt != null && cnt > 0;
    }

    public int loginUser(String email,
                         String password, String city, String area, float lng, float lat, String name) {

        if (!isExistILogin(email)) {
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            KeyHolder key = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    final PreparedStatement ps = connection.prepareStatement("INSERT INTO admin_login VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, null);
                    ps.setString(2, email);
                    ps.setString(3, encodedPassword);/////////
                    ps.setInt(4, 1);
                    ps.setString(5, "admin");
                    ps.setString(6, "Token=");
                    ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                    ps.setString(8, city);
                    ps.setString(9, area);
                    ps.setFloat(10, lng);
                    ps.setFloat(11, lat);
                    ps.setString(12, name);
                    return ps;
                }

            }, key);

            //int log_id = jdbcTemplate.update("INSERT INTO efaz_login VALUES (?,?,?,?,?)", null, user_email,
            //        encodedPassword, is_active, login_type);
            return key.getKey().intValue();
        } else {
            return -1000;
        }

    }

    public List<AdminLogin> getAllAdmins() {
        String sql = "SELECT * FROM admin_login;";
        return jdbcTemplate.query(sql, (resultSet, i) -> new AdminLogin(resultSet.getInt(1),
                resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
                resultSet.getTimestamp(7).getTime(), resultSet.getString(8), resultSet.getString(9), resultSet.getFloat(10), resultSet.getFloat(11),
                resultSet.getString(12)));
    }

    public AdminLogin getAdminById(int id) {
        String sql = "SELECT * FROM admin_login WHERE id=?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> new AdminLogin(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getTimestamp(7).getTime(), resultSet.getString(8), resultSet.getString(9), resultSet.getFloat(10), resultSet.getFloat(11),
                        resultSet.getString(12)));
    }

    public List<AdminLogin> getAdminByName(String name) {
        String sql = "SELECT * FROM admin_login WHERE name LIKE ?;";
        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%"},
                (resultSet, i) -> new AdminLogin(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getTimestamp(7).getTime(), resultSet.getString(8), resultSet.getString(9), resultSet.getFloat(10), resultSet.getFloat(11),
                        resultSet.getString(12)));
    }

    public List<AdminLogin> getAdminByCity(String city) {
        String sql = "SELECT * FROM admin_login WHERE city LIKE ?;";
        return jdbcTemplate.query(sql, new Object[]{"%" + city + "%"},
                (resultSet, i) -> new AdminLogin(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getTimestamp(7).getTime(), resultSet.getString(8), resultSet.getString(9), resultSet.getFloat(10), resultSet.getFloat(11),
                        resultSet.getString(12)));
    }
    public List<AdminLogin> getAdminByArea(String area) {
        String sql = "SELECT * FROM admin_login WHERE area LIKE ?;";
        return jdbcTemplate.query(sql, new Object[]{"%" + area + "%"},
                (resultSet, i) -> new AdminLogin(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getTimestamp(7).getTime(), resultSet.getString(8), resultSet.getString(9), resultSet.getFloat(10), resultSet.getFloat(11),
                        resultSet.getString(12)));
    }
}
