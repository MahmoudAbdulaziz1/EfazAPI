package com.taj.repository;

import com.taj.model.*;
import com.taj.model.company_offer_response_count.CompanyOfferModelDtoModel;
import com.taj.model.company_offer_response_count.CustomeCompanyOfferModel2DToModel;
import com.taj.model.new_company_history.CompanyHistoryDto;
import com.taj.model.new_company_history.CompanyHistoryDto2;
import com.taj.model.offer_description.CompanyOfferModelDto;
import com.taj.model.offer_description.CustomCompanyModelWithViewAndDesc;
import com.taj.model.offer_description.CustomCompanyModelWithViewAndDescRes;
import com.taj.model.offer_description.CustomeCompanyOfferModel2DTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 8/5/2018.
 */
@Repository
public class CustomCompanyOfferRepo {

    @Autowired
    AddOfferImageRepo imgeRepo;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkIfExist(int offer_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_company_offer WHERE offer_id=?;",
                Integer.class, offer_id);
        return cnt != null && cnt > 0;
    }

    public int addOfferEdeited(byte[] image_one, byte[] image_two, byte[] image_third, byte[] image_four, String offer_title, String offer_explaination, double offer_cost,
                               long offer_display_date, long offer_expired_date, long offer_deliver_date, int company_id, int offer_count, String city, String area,
                               float lng, float lat) {

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement("INSERT INTO company_offer_images VALUES (?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, null);
                ps.setBytes(2, image_one);
                ps.setBytes(3, image_two);/////////
                ps.setBytes(4, image_third);
                ps.setBytes(5, image_four);
                return ps;
            }

        }, key);

        int images_id = key.getKey().intValue();

        if (images_id > 0) {

            KeyHolder key2 = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    final PreparedStatement ps2 = connection.prepareStatement("INSERT INTO efaz_company_offer VALUES (?,?,?,?,?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps2.setString(1, null);
                    ps2.setInt(2, images_id);
                    ps2.setString(3, offer_title);/////////
                    ps2.setString(4, offer_explaination);
                    ps2.setDouble(5, offer_cost);
                    ps2.setTimestamp(6, new Timestamp(offer_display_date));
                    ps2.setTimestamp(7, new Timestamp(offer_expired_date));
                    ps2.setTimestamp(8, new Timestamp(offer_deliver_date));
                    ps2.setInt(9, company_id);
                    ps2.setInt(10, offer_count);
                    return ps2;
                }

            }, key2);
            int offer_id = key2.getKey().intValue();

            return jdbcTemplate.update("INSERT INTO efaz_company.efaz_company_offer_place VALUES (?,?,?,?,?)", offer_id, city, area, lng,
                    lat);
        } else {
            return 0;
        }

    }

    public List<CustomCompanyOfferModel> getAllOffers() {
        System.out.println(new Date());
        String sql = "SELECT\n" +
                "\toffer_id,\n" +
                "\timage_one,\n" +
                "\timage_two,\n" +
                "\timage_three,\n" +
                "\timage_four,\n" +
                "\toffer_title,\n" +
                "\toffer_explaination,\n" +
                "\toffer_cost,\n" +
                "\toffer_display_date,\n" +
                "\toffer_expired_date,\n" +
                "\toffer_deliver_date,\n" +
                "\toffer_company_id,\n" +
                "\toffer_count,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tlng,\n" +
                "\tlat \n" +
                "FROM\n" +
                "\tefaz_company.efaz_company_offer AS offer\n" +
                "\tINNER JOIN efaz_company.company_offer_images AS image ON offer.offer_image_id = image.images_id\n" +
                "\tINNER JOIN efaz_company_offer_place AS p ON p.id = offer_id;";
        List<CustomCompanyOfferModel> list2 = jdbcTemplate.query(sql, (resultSet, i) -> new CustomCompanyOfferModel(resultSet.getInt(1),
                resultSet.getBytes(2), resultSet.getBytes(3), resultSet.getBytes(4), resultSet.getBytes(5)
                , resultSet.getString(6), resultSet.getString(7), resultSet.getDouble(8), resultSet.getTimestamp(9).getTime(), resultSet.getTimestamp(10).getTime(),
                resultSet.getTimestamp(11).getTime(), resultSet.getInt(12), resultSet.getInt(13), resultSet.getString(14), resultSet.getString(15), resultSet.getFloat(16), resultSet.getFloat(17)));

        System.out.println(new Date());
        //int images_id = list.get
        return list2;
    }


    public CustomCompanyModelWithViewAndDescRes getCompanyOfferWithDesc(int id) {
        String sql = "SELECT\n" +
                "\toffer_id,\n" +
                "\toffer_image_id,\n" +
                "\toffer_title,\n" +
                "\toffer_explaination,\n" +
                "\toffer_cost,\n" +
                "\toffer_display_date,\n" +
                "\toffer_expired_date,\n" +
                "\toffer_deliver_date,\n" +
                "\toffer_company_id,\n" +
                "\toffer_count,\n" +
                "\tcount( request_id ) AS request_count,\n" +
                "\tCOUNT( seen_id ) AS view_count,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tlng,\n" +
                "\tlat \n" +
                "FROM\n" +
                "\tefaz_company_offer AS offer\n" +
                "\tLEFT JOIN efaz_company.efaz_school_request_offer AS req ON offer.offer_id = req.requsted_offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_see_offer AS see ON offer.offer_id = see.seen_offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer_place AS d ON offer.offer_id = d.id \n" +
                "WHERE\n" +
                "\toffer_id = ? \n" +
                "GROUP BY\n" +
                "\toffer.offer_id;";

        CustomCompanyModelWithViewAndDesc data = jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> new CustomCompanyModelWithViewAndDesc(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12),
                        resultSet.getString(13), resultSet.getString(14), resultSet.getFloat(15), resultSet.getFloat(16)));

        int offer_id = data.getOffer_id();
        byte[] image_one = imgeRepo.getCompanyOfferOneImage(data.getOffer_images_id());
        byte[] image_two = imgeRepo.getCompanyOfferTwoImage(data.getOffer_images_id());
        byte[] image_three = imgeRepo.getCompanyOfferThreeImage(data.getOffer_images_id());
        byte[] image_four = imgeRepo.getCompanyOfferFourImage(data.getOffer_images_id());
        String offer_title = data.getOffer_title();
        String offer_explain = data.getOffer_explaination();
        double offer_cost = data.getOffer_cost();
        long display = data.getOffer_display_date().getTime();
        long expired = data.getOffer_expired_date().getTime();
        long deliver = data.getOffer_deliver_date().getTime();
        int company_id = data.getCompany_id();
        int count = data.getOffer_count();

        CustomCompanyModelWithViewAndDescRes test = new CustomCompanyModelWithViewAndDescRes(offer_id, image_one, image_two, image_three, image_four, offer_title, offer_explain,
                offer_cost, display, expired, deliver, company_id, count, data.getRequest_count(), data.getView_count(), data.getCity(), data.getArea(), data.getLng(), data.getLat());

        return test;


    }


    public CustomCompanyModelWithView getCompanyOffer(int id) {
        String sql = "SELECT\n" +
                "\toffer_id,\n" +
                "\toffer_image_id,\n" +
                "\toffer_title,\n" +
                "\toffer_explaination,\n" +
                "\toffer_cost,\n" +
                "\toffer_display_date,\n" +
                "\toffer_expired_date,\n" +
                "\toffer_deliver_date,\n" +
                "\toffer_company_id,\n" +
                "\toffer_count,\n" +
                "\tcount( request_id ) AS request_count,\n" +
                "\tCOUNT( seen_id ) AS view_count \n" +
                "FROM\n" +
                "\tefaz_company_offer AS offer\n" +
                "\tLEFT JOIN efaz_company.efaz_school_request_offer AS req ON offer.offer_id = req.requsted_offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_see_offer AS see ON offer.offer_id = see.seen_offer_id \n" +
                "WHERE\n" +
                "\toffer_id = ? \n" +
                "GROUP BY\n" +
                "\toffer.offer_id;";
        CompanyOfferByRequestAndViewModel data = jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> new CompanyOfferByRequestAndViewModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12)));

        int offer_id = data.getOffer_id();
        byte[] image_one = imgeRepo.getCompanyOfferOneImage(data.getOffer_images_id());
        byte[] image_two = imgeRepo.getCompanyOfferTwoImage(data.getOffer_images_id());
        byte[] image_three = imgeRepo.getCompanyOfferThreeImage(data.getOffer_images_id());
        byte[] image_four = imgeRepo.getCompanyOfferFourImage(data.getOffer_images_id());
        String offer_title = data.getOffer_title();
        String offer_explain = data.getOffer_explaination();
        double offer_cost = data.getOffer_cost();
        long display = data.getOffer_display_date().getTime();
        long expired = data.getOffer_expired_date().getTime();
        long deliver = data.getOffer_deliver_date().getTime();
        int company_id = data.getCompany_id();
        int count = data.getOffer_count();

        CustomCompanyModelWithView test = new CustomCompanyModelWithView(offer_id, image_one, image_two, image_three, image_four, offer_title, offer_explain,
                offer_cost, display, expired, deliver, company_id, count, data.getRequest_count(), data.getView_count());

        return test;


    }


    public List<CustomeCompanyOfferModel2DToModel> getCompanyOffersWithDesc(int id) {

        String sql = "SELECT\n" +
                "\toffer_id,\n" +
                "\toffer_image_id,\n" +
                "\toffer_title,\n" +
                "\toffer_explaination,\n" +
                "\toffer_cost,\n" +
                "\toffer_display_date,\n" +
                "\toffer_expired_date,\n" +
                "\toffer_deliver_date,\n" +
                "\toffer_company_id,\n" +
                "\toffer_count,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tlng,\n" +
                "\tlat, " +
                " (SELECT COUNT(*) FROM  efaz_company.efaz_school_request_offer WHERE requsted_offer_id = offer_id)AS request_count " +
                "FROM\n" +
                "\tefaz_company_offer AS offer\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer_place AS d ON offer.offer_id = d.id \n" +
                "WHERE\n" +
                "\toffer_company_id = ?;";

        List<CompanyOfferModelDtoModel> list = jdbcTemplate.query(sql, new Object[]{id},
                (resultSet, i) -> new CompanyOfferModelDtoModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getString(11), resultSet.getString(12),
                        resultSet.getFloat(13), resultSet.getFloat(14), resultSet.getInt(15)));
        List<CustomeCompanyOfferModel2DToModel> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            int offer_id = list.get(i).getOffer_id();
            byte[] image_one = imgeRepo.getCompanyOfferOneImage(list.get(i).getOffer_images_id());
            byte[] image_two = imgeRepo.getCompanyOfferTwoImage(list.get(i).getOffer_images_id());
            byte[] image_three = imgeRepo.getCompanyOfferThreeImage(list.get(i).getOffer_images_id());
            byte[] image_four = imgeRepo.getCompanyOfferFourImage(list.get(i).getOffer_images_id());
            String offer_title = list.get(i).getOffer_title();
            String offer_explain = list.get(i).getOffer_explaination();
            double offer_cost = list.get(i).getOffer_cost();
            long display = list.get(i).getOffer_display_date().getTime();
            long expired = list.get(i).getOffer_expired_date().getTime();
            long deliver = list.get(i).getOffer_deliver_date().getTime();
            int company_id = list.get(i).getCompany_id();
            int count = list.get(i).getOffer_count();
            String city = list.get(i).getCity();
            String area = list.get(i).getArea();
            float lng = list.get(i).getLng();
            float lat = list.get(i).getLat();
            int request_count = list.get(i).getRequest_count();
            CustomeCompanyOfferModel2DToModel test = new CustomeCompanyOfferModel2DToModel(offer_id, image_one, image_two, image_three, image_four, offer_title, offer_explain,
                    offer_cost, display, expired, deliver, company_id, count, city, area, lng, lat, request_count);
            list2.add(i, test);
        }

        //int images_id = list.get
        return list2;

    }


    public List<CustomeCompanyOfferModel2> getCompanyOffers(int id) {


        List<CompanyOfferModel> list = jdbcTemplate.query("SELECT * FROM efaz_company_offer WHERE offer_company_id=?;", new Object[]{id},
                (resultSet, i) -> new CompanyOfferModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)
                        , resultSet.getString(4), resultSet.getDouble(5), resultSet.getTimestamp(6), resultSet.getTimestamp(7),
                        resultSet.getTimestamp(8), resultSet.getInt(9), resultSet.getInt(10)));
        List<CustomeCompanyOfferModel2> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            int offer_id = list.get(i).getOffer_id();
            byte[] image_one = imgeRepo.getCompanyOfferOneImage(list.get(i).getOffer_images_id());
            byte[] image_two = imgeRepo.getCompanyOfferTwoImage(list.get(i).getOffer_images_id());
            byte[] image_three = imgeRepo.getCompanyOfferThreeImage(list.get(i).getOffer_images_id());
            byte[] image_four = imgeRepo.getCompanyOfferFourImage(list.get(i).getOffer_images_id());
            String offer_title = list.get(i).getOffer_title();
            String offer_explain = list.get(i).getOffer_explaination();
            double offer_cost = list.get(i).getOffer_cost();
            long display = list.get(i).getOffer_display_date().getTime();
            long expired = list.get(i).getOffer_expired_date().getTime();
            long deliver = list.get(i).getOffer_deliver_date().getTime();
            int company_id = list.get(i).getCompany_id();
            int count = list.get(i).getOffer_count();
            CustomeCompanyOfferModel2 test = new CustomeCompanyOfferModel2(offer_id, image_one, image_two, image_three, image_four, offer_title, offer_explain,
                    offer_cost, display, expired, deliver, company_id, count);
            list2.add(i, test);
        }

        //int images_id = list.get
        return list2;

    }

    public int updateCompanyOfferWithImages(int offer_id, byte[] image_one, byte[] image_two, byte[] image_third, byte[] image_four, String offer_title, String offer_explaination, double offer_cost,
                                            long offer_display_date, long offer_expired_date, long offer_deliver_date, int company_id, int offer_count,
                                            String city, String area, float lng, float lat) {

        int image_id = jdbcTemplate.queryForObject("SELECT offer_image_id FROM efaz_company_offer WHERE offer_id=?", Integer.class, offer_id);
        int res = jdbcTemplate.update("update company_offer_images set image_one=?," +
                "image_two=?, image_three=?," + "image_four=?" + " where images_id=?", image_one, image_two, image_third, image_four, image_id);


        if (res == 1) {
             jdbcTemplate.update("update efaz_company_offer set offer_image_id=?," +
                            "offer_title=?, offer_explaination=?," + "offer_cost=?, offer_display_date=?, offer_expired_date=?," +
                            "offer_deliver_date=?, offer_company_id=?, offer_count=?" + " where offer_id=?", image_id, offer_title,
                    offer_explaination, offer_cost, new Timestamp(offer_display_date), new Timestamp(offer_expired_date), new Timestamp(offer_deliver_date),
                     company_id, offer_count, offer_id);
            return jdbcTemplate.update("UPDATE efaz_company.efaz_company_offer_place SET city=?, area=?, lng=?, lat=? WHERE id=?",
                    city, area, lng,lat,offer_id);
        } else {
            return 0;
        }

    }

    public List<CompanyHistoryDto> getCompanyHistory(int companyId){

        String sql ="SELECT\n" +
                "\toffer_id,\n" +
                "\timage_one,\n" +
                "\timage_two,\n" +
                "\timage_three,\n" +
                "\timage_four,\n" +
                "\toffer_title,\n" +
                "\toffer_explaination,\n" +
                "\toffer_cost,\n" +
                "\toffer_display_date,\n" +
                "\tdate,\n" +
                "\toffer_count,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tlng,\n" +
                "\tlat,\n" +
                "\trequest_id,\n" +
                "\trequsted_school_id,\n" +
                "\trequest_offer_count,\n" +
                "\tcount( requsted_offer_id ) AS request_count \n" +
                "FROM\n" +
                "\tefaz_company.efaz_company_offer AS offer\n" +
                "\tLEFT JOIN efaz_company.company_offer_images AS img ON img.images_id = offer.offer_image_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer_place AS p ON p.id = offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_request_offer_date AS d ON d.id = offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_request_offer AS res ON res.requsted_offer_id = offer_id \n" +
                "WHERE\n" +
                "\toffer_company_id = ? \n" +
                "\tAND is_accepted = 1 \n" +
                "GROUP BY\n" +
                "\toffer_id,\n" +
                "\timage_one,\n" +
                "\timage_two,\n" +
                "\timage_three,\n" +
                "\timage_four,\n" +
                "\toffer_title,\n" +
                "\toffer_explaination,\n" +
                "\toffer_cost,\n" +
                "\toffer_display_date,\n" +
                "\tdate,\n" +
                "\toffer_count,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tlng,\n" +
                "\tlat,\n" +
                "\trequest_id,\n" +
                "\trequsted_school_id,\n" +
                "\trequest_offer_count;";
        return jdbcTemplate.query(sql, new Object[]{companyId},
                (resultSet, i) -> new CompanyHistoryDto(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3), resultSet.getBytes(4),
                        resultSet.getBytes(5), resultSet.getString(6), resultSet.getString(7), resultSet.getDouble(8), resultSet.getTimestamp(9).getTime(),
                        resultSet.getTimestamp(10).getTime(), resultSet.getInt(11), resultSet.getString(12), resultSet.getString(13), resultSet.getFloat(14),
                        resultSet.getFloat(15), resultSet.getInt(16), resultSet.getInt(17), resultSet.getInt(18), resultSet.getInt(19)));
    }


    public List<CompanyHistoryDto2> getCompanyHistory2(int companyId){

        String sql ="SELECT\n" +
                "\toffer_id,\n" +
                "\timage_one,\n" +
                "\timage_two,\n" +
                "\timage_three,\n" +
                "\timage_four,\n" +
                "\toffer_title,\n" +
                "\toffer_explaination,\n" +
                "\toffer_cost,\n" +
                "\toffer_display_date,\n" +
                "\tdate,\n" +
                "\toffer_count,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tlng,\n" +
                "\tlat,\n" +
                "\trequest_id,\n" +
                "\trequest_offer_count,\n" +
                "\tcount( requsted_offer_id ) AS request_count,\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image \n" +
                "FROM\n" +
                "\tefaz_company.efaz_company_offer AS offer\n" +
                "\tLEFT JOIN efaz_company.company_offer_images AS img ON img.images_id = offer.offer_image_id\n" +
                "\tLEFT JOIN efaz_company.efaz_company_offer_place AS p ON p.id = offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_request_offer_date AS d ON d.id = offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_request_offer AS res ON res.requsted_offer_id = offer_id\n" +
                "\tLEFT JOIN efaz_company.efaz_school_profile AS sp ON sp.school_id = requsted_school_id \n" +
                "WHERE\n" +
                "\toffer_company_id = ? \n" +
                "\tAND is_accepted = 1 \n" +
                "GROUP BY\n" +
                "\toffer_id,\n" +
                "\timage_one,\n" +
                "\timage_two,\n" +
                "\timage_three,\n" +
                "\timage_four,\n" +
                "\toffer_title,\n" +
                "\toffer_explaination,\n" +
                "\toffer_cost,\n" +
                "\toffer_display_date,\n" +
                "\tdate,\n" +
                "\toffer_count,\n" +
                "\tcity,\n" +
                "\tarea,\n" +
                "\tlng,\n" +
                "\tlat,\n" +
                "\trequest_id,\n" +
                "\trequest_offer_count,\n" +
                "\tschool_id,\n" +
                "\tschool_name,\n" +
                "\tschool_logo_image;";
        return jdbcTemplate.query(sql, new Object[]{companyId},
                (resultSet, i) -> new CompanyHistoryDto2(resultSet.getInt(1), resultSet.getBytes(2), resultSet.getBytes(3), resultSet.getBytes(4),
                        resultSet.getBytes(5), resultSet.getString(6), resultSet.getString(7), resultSet.getDouble(8), resultSet.getTimestamp(9).getTime(),
                        resultSet.getTimestamp(10).getTime(), resultSet.getInt(11), resultSet.getString(12), resultSet.getString(13), resultSet.getFloat(14),
                        resultSet.getFloat(15), resultSet.getInt(16), resultSet.getInt(17), resultSet.getInt(18), resultSet.getInt(19), resultSet.getString(20),
                        resultSet.getBytes(21)));
    }





    public int deleteCompanyOffer(int id) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");
        int image_id = jdbcTemplate.queryForObject("SELECT offer_image_id FROM efaz_company_offer WHERE offer_id=?", Integer.class, id);
        jdbcTemplate.update("DELETE FROM company_offer_images WHERE images_id=?", image_id);
        int response = jdbcTemplate.update("DELETE FROM efaz_company_offer WHERE offer_id=?", id);
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=1;");
        return response;
    }


    public int getCompanyOfferCount(int id) {

        return jdbcTemplate.queryForObject("SELECT count(*) FROM efaz_company_offer WHERE offer_company_id=?;", Integer.class, id);

    }


}
