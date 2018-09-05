package com.taj.repository;

import com.taj.model.AdminHistoryOrdersModel;
import com.taj.model.AdminOrdersModel;
import com.taj.model.AdminSingleOrderHistoryModel;
import com.taj.model.AdminSingleOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 9/3/2018.
 */
@Repository
public class AdminOrdersRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<AdminOrdersModel> getAllOrders() {

        String sql = "SELECT\n" +
                "\trequsted_offer_id AS offer_id,\n" +
                "\trequest_offer_count,\n" +
                "\tschool_id,\n" +
                "\tschool_logo_image,\n" +
                "\toffer_company_id AS company_id,\n" +
                "\toffer_cost,\n" +
                "\tcompany_logo_image,\n" +
                "\toffer_display_date \n" +
                "FROM\n" +
                "\tefaz_company.efaz_school_request_offer AS req\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile AS spro ON req.requsted_school_id = spro.school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer AS offer ON req.requsted_offer_id = offer.offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_profile AS cpro ON offer.offer_company_id = cpro.company_id \n" +
                "WHERE\n" +
                "\tis_accepted = 0;";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> new AdminOrdersModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getBytes(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getBytes(7),
                        resultSet.getTimestamp(8).getTime()));
    }

    public AdminSingleOrderModel getOrder(int id) {
        String sql = "SELECT\n" +
                "\trequsted_offer_id AS offer_id,\n" +
                "    image_one AS offer_image,\n" +
                "    offer.offer_title,\n" +
                "    offer.offer_explaination,\n" +
                "    offer.offer_display_date,\n" +
                "    offer.offer_expired_date,\n" +
                "\trequest_offer_count,\n" +
                "\tschool_id,\n" +
                "\tschool_logo_image,\n" +
                "\toffer_company_id AS company_id,\n" +
                "\toffer_cost,\n" +
                "\tcompany_logo_image\n" +
                "\t \n" +
                "FROM\n" +
                "\tefaz_company.efaz_school_request_offer AS req\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile AS spro ON req.requsted_school_id = spro.school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer AS offer ON req.requsted_offer_id = offer.offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_profile AS cpro ON offer.offer_company_id = cpro.company_id\n" +
                "    Left JOIN efaz_company.company_offer_images AS image ON offer.offer_image_id = image.images_id\n" +
                "WHERE\n" +
                "\t is_accepted = 0 AND requsted_offer_id=?;";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> new AdminSingleOrderModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getTimestamp(5).getTime(), resultSet.getTimestamp(6).getTime(),
                        resultSet.getInt(7), resultSet.getInt(8), resultSet.getBytes(9), resultSet.getInt(10), resultSet.getDouble(11),
                        resultSet.getBytes(12)));
    }


    public List<AdminHistoryOrdersModel> getAllHistoryOrders() {

        String sql = "SELECT\n" +
                "\trequsted_offer_id AS offer_id,\n" +
                "\trequest_offer_count,\n" +
                "\tschool_id,\n" +
                "\tschool_logo_image,\n" +
                "\toffer_company_id AS company_id,\n" +
                "\toffer_cost,\n" +
                "\tcompany_logo_image,\n" +
                "\toffer_display_date," +
                " offer.offer_deliver_date " +
                "FROM\n" +
                "\tefaz_company.efaz_school_request_offer AS req\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile AS spro ON req.requsted_school_id = spro.school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer AS offer ON req.requsted_offer_id = offer.offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_profile AS cpro ON offer.offer_company_id = cpro.company_id \n" +
                "WHERE\n" +
                "\tis_accepted = 1;";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> new AdminHistoryOrdersModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getBytes(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getBytes(7),
                        resultSet.getTimestamp(8).getTime(), resultSet.getTimestamp(9).getTime()));
    }

    public AdminSingleOrderHistoryModel getHistoryOrder(int id) {
        String sql = "SELECT\n" +
                "\trequsted_offer_id AS offer_id,\n" +
                "    image_one AS offer_image,\n" +
                "    offer.offer_title,\n" +
                "    offer.offer_explaination,\n" +
                "    offer.offer_display_date,\n" +
                "    offer.offer_expired_date, " +
                " offer.offer_deliver_date, " +
                "\trequest_offer_count,\n" +
                "\tschool_id,\n" +
                "\tschool_logo_image,\n" +
                "\toffer_company_id AS company_id,\n" +
                "\toffer_cost,\n" +
                "\tcompany_logo_image\n" +
                "\t \n" +
                "FROM\n" +
                "\tefaz_company.efaz_school_request_offer AS req\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile AS spro ON req.requsted_school_id = spro.school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer AS offer ON req.requsted_offer_id = offer.offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_profile AS cpro ON offer.offer_company_id = cpro.company_id\n" +
                "    Left JOIN efaz_company.company_offer_images AS image ON offer.offer_image_id = image.images_id\n" +
                "WHERE\n" +
                "\tis_accepted = 1 AND requsted_offer_id=?;";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> new AdminSingleOrderHistoryModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getTimestamp(5).getTime(), resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(),
                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getBytes(10), resultSet.getInt(11), resultSet.getDouble(12),
                        resultSet.getBytes(13)));
    }


    public boolean isExist(int requsted_offer_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.efaz_school_request_offer WHERE requsted_offer_id=?;",
                Integer.class, requsted_offer_id);
        return cnt != null && cnt > 0;
    }


}
