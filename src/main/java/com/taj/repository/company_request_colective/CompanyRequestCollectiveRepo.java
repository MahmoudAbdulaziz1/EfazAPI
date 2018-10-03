package com.taj.repository.company_request_colective;

import com.taj.model.company_request_collective.CompanyRequestCollectiveByTnders;
import com.taj.model.company_request_collective.CompanyRequestCollectiveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 10/3/2018.
 */
@Repository
public class CompanyRequestCollectiveRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addRequestToCollective(int response_takataf_company_id,
                                      int response_takataf_request_id, double responsed_cost, String response_desc) {
        return jdbcTemplate.update("INSERT INTO takataf_company_request_tender VALUES (?,?,?,?,?,?,?,?,?);", null,
                response_takataf_company_id, response_takataf_request_id, responsed_cost, 0, new Timestamp(System.currentTimeMillis()), 0, 0, response_desc);

    }


    public boolean isExistOffer(int company_id, int tender_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.takataf_company_request_tender WHERE response_takataf_company_id=? AND response_takataf_request_id=?;",
                Integer.class, company_id, tender_id);
        return cnt != null && cnt > 0;
    }

    public boolean isExistTender(int tender_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.takatf_tender WHERE tender_id=?;",
                Integer.class, tender_id);
        return cnt != null && cnt > 0;
    }


    public List<CompanyRequestCollectiveModel> getAllCompanyResponses() {
        return jdbcTemplate.query("SELECT * FROM efaz_company.takataf_company_request_tender",
                (resultSet, i) -> new CompanyRequestCollectiveModel(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getDouble(4), resultSet.getInt(5), resultSet.getTimestamp(6).getTime(),
                        resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9)));
    }

    public CompanyRequestCollectiveModel getCompanyResponse(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_company.takataf_company_request_tender WHERE response_id=?", new Object[]{id},
                (resultSet, i) -> new CompanyRequestCollectiveModel(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getDouble(4), resultSet.getInt(5), resultSet.getTimestamp(6).getTime(),
                        resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9)));
    }

    public List<CompanyRequestCollectiveByTnders> getCompanyResponseByCompany(int companyId) {
        String sql = "SELECT\n" +
                "\tresponse_id,\n" +
                "\tresponsed_cost,\n" +
                "\tresponse_date,\n" +
                "\tresponse_desc,\n" +
                "\tcompany_id, \n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\ttender_id,\n" +
                "\ttender_title, \n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\ttender_company_expired_date,\n" +
                "\ttender_company_display_date\n" +
                "FROM\n" +
                "\tefaz_company.takataf_company_request_tender AS ct\n" +
                "\tLEFT JOIN efaz_company.efaz_company_profile AS pro ON ct.response_takataf_company_id = pro.company_id\n" +
                "\tLEFT JOIN efaz_company.takatf_tender AS tender ON tender.tender_id = ct.\tresponse_takataf_request_id\n" +
                "\tWHERE company_id = ?;";

        return jdbcTemplate.query(sql, new Object[]{companyId},
                (resultSet, i) -> new CompanyRequestCollectiveByTnders(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getTimestamp(3).getTime(),
                        resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getBytes(7), resultSet.getInt(8), resultSet.getString(9),
                        resultSet.getString(10), resultSet.getTimestamp(11).getTime(), resultSet.getTimestamp(12).getTime(),
                        resultSet.getTimestamp(13).getTime(), resultSet.getTimestamp(14).getTime()));
    }


    public List<CompanyRequestCollectiveByTnders> getCompanyResponseByTender(int tenderId) {
        String sql = "SELECT\n" +
                "\tresponse_id,\n" +
                "\tresponsed_cost,\n" +
                "\tresponse_date,\n" +
                "\tresponse_desc,\n" +
                "\tcompany_id, \n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\ttender_id,\n" +
                "\ttender_title, \n" +
                "\ttender_explain,\n" +
                "\ttender_display_date,\n" +
                "\ttender_expire_date,\n" +
                "\ttender_company_expired_date,\n" +
                "\ttender_company_display_date\n" +
                "FROM\n" +
                "\tefaz_company.takataf_company_request_tender AS ct\n" +
                "\tLEFT JOIN efaz_company.efaz_company_profile AS pro ON ct.response_takataf_company_id = pro.company_id\n" +
                "\tLEFT JOIN efaz_company.takatf_tender AS tender ON tender.tender_id = ct.\tresponse_takataf_request_id\n" +
                "\tWHERE tender_id = ?;";

        return jdbcTemplate.query(sql, new Object[]{tenderId},
                (resultSet, i) -> new CompanyRequestCollectiveByTnders(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getTimestamp(3).getTime(),
                        resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getBytes(7), resultSet.getInt(8), resultSet.getString(9),
                        resultSet.getString(10), resultSet.getTimestamp(11).getTime(), resultSet.getTimestamp(12).getTime(),
                        resultSet.getTimestamp(13).getTime(), resultSet.getTimestamp(14).getTime()));
    }

    public int updateCompanyResponses(int response_takataf_company_id,
                                      int response_takataf_request_id, double responsed_cost, String response_desc) {
        if (isExistTender(response_takataf_request_id)) {
            if (isExistOffer(response_takataf_company_id, response_takataf_request_id)) {
                return jdbcTemplate.update("UPDATE efaz_company.takataf_company_request_tender SET responsed_cost=? , response_desc=? WHERE" +
                        " response_takataf_company_id=? AND  response_takataf_request_id=?;", responsed_cost, response_desc, response_takataf_company_id, response_takataf_request_id);
            } else {
                return -1000;
            }
        } else {
            return -100;
        }

    }

    /// to make update or add request not done

    public int addORUpdateRequestToCollective(int response_takataf_company_id,
                                              int response_takataf_request_id, double responsed_cost, String response_desc) {

        if (isExistTender(response_takataf_request_id)) {
            if (isExistOffer(response_takataf_company_id, response_takataf_request_id)) {
                return jdbcTemplate.update("UPDATE efaz_company.takataf_company_request_tender SET responsed_cost=? , response_desc=? WHERE" +
                        " response_takataf_company_id=? AND  response_takataf_request_id=?;",
                        responsed_cost, response_desc, response_takataf_company_id, response_takataf_request_id);
            } else {
                return jdbcTemplate.update("INSERT INTO takataf_company_request_tender VALUES (?,?,?,?,?,?,?,?,?);", null,
                        response_takataf_company_id, response_takataf_request_id, responsed_cost, 0, new Timestamp(System.currentTimeMillis()), 0, 0, response_desc);
            }


        }else {
            return -1001;
        }
    }


}
