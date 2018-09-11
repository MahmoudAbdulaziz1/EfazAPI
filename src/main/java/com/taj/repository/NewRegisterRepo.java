package com.taj.repository;

import com.taj.model.NewRegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.List;

/**
 * Created by User on 9/10/2018.
 */
@Repository
public class NewRegisterRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private JavaMailSender sender;


    public NewRegisterModel addUser(String email, String password, String userName, String phoneNumber, String companyName
            , String address, String website, String registrationRole, long registerationDate, String city, String area) {


        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO efaz_registration VALUES (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, null);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setString(4, userName);
                ps.setString(5, phoneNumber);
                ps.setString(6, companyName);
                ps.setString(7, address);
                ps.setString(8, website);
                ps.setInt(9, 0);
                ps.setString(10, registrationRole);
                ps.setTimestamp(11, new Timestamp(registerationDate));
                return ps;
            }
        }, holder);

        int id = holder.getKey().intValue();

        jdbcTemplate.update("INSERT INTO  efaz_company.complete_register_data VALUES (?,?,?,?,?)", id, city, area, 0, 0);
        String sql = "SELECT " +
                "\tregistration_id, " +
                "\tregisteration_email, " +
                "\tregisteration_password, " +
                "\tregisteration_username, " +
                "\tregisteration_phone_number, " +
                "\tregistration_organization_name, " +
                "\tregistration_address_desc, " +
                "\tregistration_website_url, " +
                "\tregistration_isActive, " +
                "\tregistration_role, " +
                "\tregisteration_date, " +
                "\tcity, " +
                "\tarea, " +
                "\tarchive, " +
                "\tconsider  " +
                "FROM " +
                " efaz_registration AS org" +
                " LEFT JOIN efaz_company.complete_register_data AS DATA ON org.registration_id = DATA.id  " +
                "WHERE " +
                " registration_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                ((resultSet, i) -> new NewRegisterModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10),
                        resultSet.getTimestamp(11).getTime(), resultSet.getString(12), resultSet.getString(13), resultSet.getInt(14), resultSet.getInt(15))));


    }


    public boolean checkIfEmailExist(String email, String role) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_registration WHERE registeration_email=? AND registration_role=?;",
                Integer.class, email, role);
        return cnt != null && cnt > 0;
    }


    public List<NewRegisterModel> getInActiveCompanies() {
        String sql = " SELECT " +
                " registration_id, " +
                " registeration_email, " +
                " registeration_password, " +
                " registeration_username, " +
                " registeration_phone_number, " +
                " registration_organization_name, " +
                " registration_address_desc, " +
                " registration_website_url, " +
                " registration_isActive, " +
                " registration_role, " +
                " registeration_date, " +
                " city, " +
                " area, " +
                " archive, " +
                " consider  " +
                " FROM " +
                " efaz_registration AS org " +
                " LEFT JOIN efaz_company.complete_register_data AS dta ON org.registration_id = dta.id  " +
                " WHERE " +
                " registration_isActive = 0   " +
                " AND archive = 0  " +
                " AND consider = 0;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new NewRegisterModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10),
                        resultSet.getTimestamp(11).getTime(), resultSet.getString(12), resultSet.getString(13),
                        resultSet.getInt(14), resultSet.getInt(15)));


    }

    public int archiveCompanyRequest(int id) {
        String sql = "UPDATE efaz_company.complete_register_data  " +
                " SET archive = 1 " +
                " WHERE " +
                " id = ?;";
        return jdbcTemplate.update(sql, id);
    }

    public int unArchiveCompanyRequest(int id) {
        String sql = "UPDATE efaz_company.complete_register_data  " +
                " SET archive = 0 " +
                " WHERE " +
                " id = ?;";
        return jdbcTemplate.update(sql, id);
    }


    public int considrateCompanyRequest(int id) {
        String sql = "UPDATE efaz_company.complete_register_data  " +
                " SET consider = 1 " +
                " WHERE " +
                " id = ?;";
        return jdbcTemplate.update(sql, id);
    }

    public int unCosidrateCompanyRequest(int id) {
        String sql = "UPDATE efaz_company.complete_register_data  " +
                " SET consider = 0 " +
                " WHERE " +
                " id = ?;";
        return jdbcTemplate.update(sql, id);
    }


    public List<NewRegisterModel> getInActiveCompaniesArchived() {
        String sql = " SELECT  " +
                "\tregistration_id,\n" +
                "\tregisteration_email,\n" +
                "\tregisteration_password,\n" +
                "\tregisteration_username,\n" +
                "\tregisteration_phone_number,\n" +
                "\tregistration_organization_name,\n" +
                "\tregistration_address_desc,\n" +
                "\tregistration_website_url,\n" +
                "\tregistration_isActive,\n" +
                "\tregistration_role,\n" +
                "\tregisteration_date,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tarchive,\n" +
                "\tconsider \n" +
                "FROM\n" +
                "\tefaz_registration AS org\n" +
                "\tLEFT JOIN efaz_company.complete_register_data AS dta ON org.registration_id = dta.id \n" +
                "WHERE\n" +
                "\tregistration_isActive = 0 \n" +
                "\tAND archive = 1;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new NewRegisterModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10),
                        resultSet.getTimestamp(11).getTime(), resultSet.getString(12), resultSet.getString(13),
                        resultSet.getInt(14), resultSet.getInt(15)));


    }

    public List<NewRegisterModel> getInActiveCompaniesConsiderate() {
        String sql = "SELECT " +
                " registration_id," +
                "registeration_email, " +
                "registeration_password, " +
                "registeration_username, " +
                "registeration_phone_number, " +
                "registration_organization_name, " +
                "registration_address_desc, " +
                "registration_website_url, " +
                "registration_isActive, " +
                "registration_role, " +
                "registeration_date, " +
                "city, " +
                "area, " +
                "archive, " +
                "consider  " +
                "FROM " +
                "efaz_registration AS org " +
                "LEFT JOIN efaz_company.complete_register_data AS dta ON org.registration_id = dta.id  " +
                "WHERE " +
                "registration_isActive = 0  " +
                "AND consider = 1;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new NewRegisterModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10),
                        resultSet.getTimestamp(11).getTime(), resultSet.getString(12), resultSet.getString(13),
                        resultSet.getInt(14), resultSet.getInt(15)));


    }


    public List<NewRegisterModel> getInActiveCompaniesBoth() {
        String sql = "SELECT\n" +
                "\tregistration_id,\n" +
                "\tregisteration_email,\n" +
                "\tregisteration_password,\n" +
                "\tregisteration_username,\n" +
                "\tregisteration_phone_number,\n" +
                "\tregistration_organization_name,\n" +
                "\tregistration_address_desc,\n" +
                "\tregistration_website_url,\n" +
                "\tregistration_isActive,\n" +
                "\tregistration_role,\n" +
                "\tregisteration_date,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tarchive,\n" +
                "\tconsider \n" +
                "FROM\n" +
                "\tefaz_registration AS org\n" +
                "\tLEFT JOIN efaz_company.complete_register_data AS dta ON org.registration_id = dta.id \n" +
                "WHERE\n" +
                "\tregistration_isActive = 0 \n" +
                "\tAND archive = 1 \n" +
                "\tAND consider = 1;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new NewRegisterModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10),
                        resultSet.getTimestamp(11).getTime(), resultSet.getString(12), resultSet.getString(13),
                        resultSet.getInt(14), resultSet.getInt(15)));


    }

    public NewRegisterModel getUser(int id) {

        String sql = "SELECT\n" +
                "\tregistration_id,\n" +
                "\tregisteration_email,\n" +
                "\tregisteration_password,\n" +
                "\tregisteration_username,\n" +
                "\tregisteration_phone_number,\n" +
                "\tregistration_organization_name,\n" +
                "\tregistration_address_desc,\n" +
                "\tregistration_website_url,\n" +
                "\tregistration_isActive,\n" +
                "\tregistration_role,\n" +
                "\tregisteration_date,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tarchive,\n" +
                "\tconsider \n" +
                "FROM\n" +
                "\tefaz_registration AS org\n" +
                "\tLEFT JOIN efaz_company.complete_register_data AS dta ON org.registration_id = dta.id \n" +
                "WHERE\n" +
                " registration_id=?; ";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                ((resultSet, i) -> new NewRegisterModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10),
                        resultSet.getTimestamp(11).getTime(), resultSet.getString(12), resultSet.getString(13),
                        resultSet.getInt(14), resultSet.getInt(15))));
    }


    public int confirmEmail(int id) {
        jdbcTemplate.update("update efaz_registration set registration_isActive=1 WHERE registration_id=?", id);
        NewRegisterModel model = getUser(id);

        try {
            sendEmail(model.getRegisterationEmail(), id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jdbcTemplate.update("INSERT INTO efaz_login VALUES (?,?,?,?,?,?,?,?,?)", null, model.getRegisterationEmail(),
                model.getRegisterationPassword(), 0, model.getRegistrationRole(), "Token=", new Timestamp(System.currentTimeMillis())
                , model.getCity(), model.getArea());

    }

    /**
     * @param email
     * @param createdId
     * @throws Exception
     */
    private void sendEmail(String email, int createdId) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setText("Just Few Moments to complete Regsiter");
        helper.setText(
                "<table>\n" +
                        "    <tr>" +
                        "        <td style=\"background-color: #4ecdc4;border-color: #4c5764;border: 2px solid #45b7af;padding: 10px;text-align: center;\">" +
                        "            <P style=\"display: block;color: #ffffff;font-size: 12px;text-decoration: none;text-transform: uppercase;\">" +
                        "                Your Email has been Confirmed" +
                        "            </p>" +
                        "        </td>" +
                        "    </tr>" +
                        "</table>"
                , true);
        helper.setSubject("Complete Registration with id " + createdId);
        sender.send(message);
    }


}
