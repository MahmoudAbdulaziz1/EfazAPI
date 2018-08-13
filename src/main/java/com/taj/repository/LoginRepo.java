package com.taj.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.LoginModel;
import com.taj.model.RegistrationModel;
import com.taj.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
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
    ObjectMapper mapper;

    @Autowired
    JwtGenerator generator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JavaMailSender sender;

    public LoginModel loginUser(String user_email, String user_password, int is_active, String login_role, String login_token) {


        RegistrationModel model = null;
        if (isExist(user_email, login_role)) {

            model = jdbcTemplate.queryForObject("select * from efaz_registration WHERE registeration_email=?"
                    , new Object[]{user_email},
                    (resultSet, i) -> new RegistrationModel(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                            resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10)));
            if (bCryptPasswordEncoder.matches(user_password, model.getRegisteration_password())) {
                //if (user_password.equals(model.getRegisteration_password())){
                if (is_active == 1) {
                    String encodedPassword = bCryptPasswordEncoder.encode(user_password);
                    KeyHolder key = new GeneratedKeyHolder();
                    jdbcTemplate.update(new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            final PreparedStatement ps = connection.prepareStatement("INSERT INTO efaz_login VALUES (?,?,?,?,?,?)",
                                    Statement.RETURN_GENERATED_KEYS);
                            ps.setString(1, null);
                            ps.setString(2, user_email);
                            ps.setString(3, encodedPassword);/////////
                            ps.setInt(4, is_active);
                            ps.setString(5, login_role);
                            ps.setString(6, login_token);
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

    public boolean isExist(String email, String login_role) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_registration WHERE registeration_email=? AND registration_role=?;",
                Integer.class, email, login_role);
        return cnt != null && cnt > 0;
    }

    public ObjectNode isLogged(String user_email, String user_passwords, String login_role) {
        //String encodedPassword = bCryptPasswordEncoder.encode(user_password);

        LoginModel model = null;

        if (isExist(user_email, login_role)) {

            try {
                model = jdbcTemplate.queryForObject("select * from efaz_login WHERE user_email=? AND login_role=?"
                        , new Object[]{user_email, login_role},
                        (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6)));
            }catch (Exception e){
                model = null;
            }
            if (model == null){
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 401);
                objectNode.put("message", "in correct password");
                return objectNode;
            }else {
                System.out.println(user_passwords);
                if (bCryptPasswordEncoder.matches(user_passwords, model.getUser_password())) {

                    Integer cnt = jdbcTemplate.queryForObject(
                            "SELECT count(*) FROM efaz_login WHERE user_email=? AND login_role=?;",//"//AND user_password = ?  ;
                            Integer.class, user_email, login_role);//, bCryptPasswordEncoder.encode(user_passwords.trim()));

                    boolean check = cnt != null && cnt > 0;

                    if (check) {
                        ObjectNode objectNode = mapper.createObjectNode();
                        objectNode.put("state", 201);
                        objectNode.put("login_id", model.getLogin_id());
                        objectNode.put("user_email", model.getUser_email());
                        objectNode.put("user_password", model.getUser_password());
                        objectNode.put("is_active", model.getIs_active());
                        objectNode.put("login_role", model.getLogin_role());
                        objectNode.put("login_token", model.getLogin_token());
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


    public LoginModel getLoggedId(String user_email, String user_passwords, String login_role) {
        LoginModel model = null;
        if (isExist(user_email, login_role)) {

            model = jdbcTemplate.queryForObject("select * from efaz_login WHERE user_email=?"
                    , new Object[]{user_email},
                    (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5),resultSet.getString(6)));
            if (bCryptPasswordEncoder.matches(user_passwords, model.getUser_password())) {


                return jdbcTemplate.queryForObject("select * from efaz_login WHERE user_email=? AND login_role=?;", new Object[]{user_email, login_role},
                        (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getInt(4),  resultSet.getString(5),resultSet.getString(6)));


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
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5),resultSet.getString(6)));
    }

//    public int deleteLoggedUser(int id){
//        return jdbcTemplate.update("DELETE FROM efaz_login WHERE login_id=?", id);
//    }

    public LoginModel getLoggedUser(int id) {

        return jdbcTemplate.queryForObject("select * from efaz_login WHERE login_id=?;", new Object[]{id},
                (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5),resultSet.getString(6)));
    }




    public int  updateActiveState(int login_id, int is_active) {
        return jdbcTemplate.update("update efaz_login set is_active=? where login_id=?", is_active, login_id);
    }

    public int  deleteUser(int id, String user_email) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        jdbcTemplate.update("DELETE FROM efaz_login WHERE login_id=?;", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        return jdbcTemplate.update("DELETE FROM efaz_registration WHERE  registeration_email=? AND registration_isActive=1", user_email);

    }


    public List<LoginModel> getInActiveCompanies() {
        return jdbcTemplate.query("SELECT * FROM efaz_login WHERE is_active=0;",
                (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6)));
    }

    public List<LoginModel> getActiveCompanies() {
        return jdbcTemplate.query("SELECT * FROM efaz_login WHERE is_active=1;",
                (resultSet, i) -> new LoginModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6)));
    }

    public int activeLogin(int id) {
        RegistrationModel models = new RegistrationModel();
        return jdbcTemplate.update("update efaz_login set is_active=1, login_token=?  WHERE login_id=?", "Token="+ generator.generate(models), id);
    }

    public int inActiveLogin(int id) {
        return jdbcTemplate.update("update efaz_login set is_active=0 WHERE login_id=?", id);
    }



    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    private String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }


    public int updatePassword(int login_id, String user_email, String user_password) {
        String encodedPassword = bCryptPasswordEncoder.encode(user_password);
        jdbcTemplate.update("update efaz_login set user_password=? where login_id=?", encodedPassword, login_id);
        return jdbcTemplate.update("update efaz_registration set registeration_password=? where registeration_email=? AND registration_isActive=1",
                encodedPassword, user_email);
    }

    public int restPassword( String user_email, String role) {

        String user_password = randomString(8);
        String encodedPassword = bCryptPasswordEncoder.encode(user_password);

        int register = jdbcTemplate.update("update efaz_registration set registeration_password=? where registeration_email=? AND registration_role=? AND registration_isActive=1",
                encodedPassword, user_email, role);
        int login = jdbcTemplate.update("update efaz_login set user_password=? where user_email=? AND login_role=?;", encodedPassword, user_email, role);
        if (login>0 && register>0){
            System.out.println(login+" "+ register + " "+ user_password);

            try {
                restPasswordEmail(user_email, user_password);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 1;
        }else {
            return 0;
        }
    }



    private void restPasswordEmail(String email, String newPassword) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setText("Just Few Moments to Reset Password");
        helper.setText(
                "<table>\n" +
                        "    <tr>" +
                        "        <td style=\"background-color: #4ecdc4;border-color: #4c5764;border: 2px solid #45b7af;padding: 10px;text-align: center;\">" +
                        "            <P style=\"display: block;color: #ffffff;font-size: 12px;text-decoration: none;\">" +
                                       "Your new Password is   " + newPassword +
                        "            </p>" +
                        "        </td>" +
                        "    </tr>" +
                        "</table>"
                , true);
        helper.setSubject("Change Password for " + email);
        sender.send(message);
    }

}
