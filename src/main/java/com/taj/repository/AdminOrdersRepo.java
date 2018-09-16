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
                "\n" +
                "                requsted_offer_id AS offer_id,\n" +
                "                    image_one AS offer_image,\n" +
                "                    offer.offer_title,\n" +
                "                    offer.offer_explaination,\n" +
                "                    offer.offer_display_date,\n" +
                "                    offer.offer_expired_date,\n" +
                "                request_offer_count,\n" +
                "                school_id,\n" +
                "               school_logo_image,\n" +
                "                offer_company_id AS company_id,\n" +
                "                offer_cost,\n" +
                "                company_logo_image,\n" +
                "\t\t\t\t\t\t\t\tIFNULL(ship,0) AS ship\n" +
                "\t\t\t\t\t\t\t\n" +
                "                FROM\n" +
                "                efaz_company.efaz_school_request_offer AS req\n" +
                "                LEFT JOIN efaz_company.efaz_school_profile AS spro ON req.requsted_school_id = spro.school_id\n" +
                "                LEFT JOIN efaz_company.efaz_company_offer AS offer ON req.requsted_offer_id = offer.offer_id\n" +
                "                LEFT JOIN efaz_company.efaz_company_profile AS cpro ON offer.offer_company_id = cpro.company_id\n" +
                "                    Left JOIN efaz_company.company_offer_images AS image ON offer.offer_image_id = image.images_id\n" +
                "\t\t\t\t\t\t\t\t\t\tLEFT JOIN efaz_company.efaz_company_offer_shipping as ship ON requsted_offer_id = ship.ship_company_offer_id\n" +
                "                WHERE\n" +
                "                \t is_accepted = 0 AND requsted_offer_id=?;";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> new AdminSingleOrderModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getTimestamp(5).getTime(), resultSet.getTimestamp(6).getTime(),
                        resultSet.getInt(7), resultSet.getInt(8), resultSet.getBytes(9), resultSet.getInt(10), resultSet.getDouble(11),
                        resultSet.getBytes(12), resultSet.getInt(13)));
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
                "\tis_accepted = 1 OR offer_expired_date< NOW() ;";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> new AdminHistoryOrdersModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getBytes(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getBytes(7),
                        resultSet.getTimestamp(8).getTime(), resultSet.getTimestamp(9).getTime()));
    }

    public AdminSingleOrderHistoryModel getHistoryOrder(int id) {
        String sql = "SELECT\n" +
                "\trequsted_offer_id AS offer_id,\n" +
                "\timage_one AS offer_image,\n" +
                "\toffer.offer_title,\n" +
                "\toffer.offer_explaination,\n" +
                "\toffer.offer_display_date,\n" +
                "\toffer.offer_expired_date,\n" +
                "\toffer.offer_deliver_date,\n" +
                "\trequest_offer_count,\n" +
                "\tschool_id,\n" +
                "\tschool_logo_image,\n" +
                "\toffer_company_id AS company_id,\n" +
                "\toffer_cost,\n" +
                "\tcompany_logo_image,\n" +
                "\tIFNULL( ship, 0 ) AS ship \n" +
                "FROM\n" +
                "\tefaz_company.efaz_school_request_offer AS req\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile AS spro ON req.requsted_school_id = spro.school_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer AS offer ON req.requsted_offer_id = offer.offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_profile AS cpro ON offer.offer_company_id = cpro.company_id\n" +
                "\tLEFT JOIN efaz_company.company_offer_images AS image ON offer.offer_image_id = image.images_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer_shipping AS ship ON requsted_offer_id = ship.ship_company_offer_id \n" +
                "WHERE\n" +
                "\tis_accepted = 1 \n" +
                "\tAND requsted_offer_id = ?;";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> new AdminSingleOrderHistoryModel(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getTimestamp(5).getTime(), resultSet.getTimestamp(6).getTime(), resultSet.getTimestamp(7).getTime(),
                        resultSet.getInt(8), resultSet.getInt(9), resultSet.getBytes(10), resultSet.getInt(11), resultSet.getDouble(12),
                        resultSet.getBytes(13), resultSet.getInt(14)));
    }

    public int addShipping(double ship, int ship_company_offer_id){
        if (isExistShip(ship_company_offer_id)){
            return jdbcTemplate.update("UPDATE efaz_company.efaz_company_offer_shipping SET ship=? WHERE ship_company_offer_id=? ;", ship, ship_company_offer_id);

        }else {
            return jdbcTemplate.update("INSERT INTO efaz_company.efaz_company_offer_shipping VALUES (?,?,?);", null, ship, ship_company_offer_id);

        }
    }


    public boolean isExist(int requsted_offer_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.efaz_school_request_offer WHERE requsted_offer_id=?;",
                Integer.class, requsted_offer_id);
        return cnt != null && cnt > 0;
    }

    public boolean isExistShip(int ship_company_offer_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company.efaz_company_offer_shipping WHERE ship_company_offer_id=?;",
                Integer.class, ship_company_offer_id);
        return cnt != null && cnt > 0;
    }


}
