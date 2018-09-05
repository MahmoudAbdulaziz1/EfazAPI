package com.taj.repository;

import com.taj.model.CompantProfileDto;
import com.taj.model.MultiCategoryProfileDTOS;
import com.taj.model.MultiCategoryProfileGetAllDTO;
import com.taj.model.TakatfTenderCategoryPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by User on 9/4/2018.
 */
@Repository
public class MultiCategoryProfileRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addProfileWithCategories(int company_id, String company_name, byte[] company_logo_image, String company_address,
                                        String company_category_id, String company_link_youtube, String company_website_url, float school_lng,
                                        float school_lat, byte[] company_cover_image, String company_phone_number, String company_desc,
                                        List<TakatfTenderCategoryPOJO> category) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement("INSERT INTO efaz_company_profile VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, company_id);
                ps.setString(2, company_name);
                ps.setBytes(3, company_logo_image);
                ps.setString(4, company_address);
                ps.setString(5, company_category_id);
                ps.setString(6, company_link_youtube);
                ps.setString(7, company_website_url);
                ps.setFloat(8, school_lng);
                ps.setFloat(9, school_lat);
                ps.setBytes(10, company_cover_image);
                ps.setString(11, company_phone_number);
                ps.setString(12, company_desc);
                return ps;
            }

        }, key);
        //int log_id = jdbcTemplate.update("INSERT INTO efaz_login VALUES (?,?,?,?,?)", null, user_email,
        //        encodedPassword, is_active, login_type);
        int profileID = key.getKey().intValue();


//        int a = jdbcTemplate.update("INSERT INTO takatf_tender VALUES (?,?,?,?,?,?,?,?,?,?)", null, tender_logo, tender_title, tender_explain,
//                new Timestamp(tender_display_date), new Timestamp(tender_expire_date), 0, 1, new Timestamp(tender_company_display_date),
//                new Timestamp(tender_company_expired_date));
        for (int i = 0; i < category.size(); i++) {
            int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                    Integer.class, "%" + category.get(i).getCategory_name().trim() + "%");

            jdbcTemplate.update("INSERT INTO efaz_company.efaz_company_profile_cats VALUES  (?,?,?)", null, profileID, categorys);
        }


        return profileID;
    }


    public List<MultiCategoryProfileGetAllDTO> getProfiles() {

        String sql = "SELECT\n" +
                "\tcompany_id,\n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\tcompany_address,\n" +
                "\tcategory_name,\n" +
                "\tcompany_link_youtube,\n" +
                "\tcompany_website_url,\n" +
                "\tcompany_lng,\n" +
                "\tcompany_lat,\n" +
                "\tcompany_cover_image,\n" +
                "\tcompany_phone_number,\n" +
                "\tcount( DISTINCT follow_id ) AS follower_count,\n" +
                "\tcount( DISTINCT offer_id ) AS order_count,\n" +
                "\tcompany_desc,\n" +
                "\tCOUNT( DISTINCT id ) AS category_num \n" +
                "FROM\n" +
                "\t(\n" +
                "\t\t(\n" +
                "\t\t\t( efaz_company.efaz_company_profile AS PROFILE LEFT JOIN efaz_company.efaz_organization_following AS follow ON PROFILE.company_id = follow.follower_id )\n" +
                "\t\t\tLEFT JOIN efaz_company_offer AS offer ON PROFILE.company_id = offer.offer_company_id \n" +
                "\t\t)\n" +
                "\t\tINNER JOIN efaz_company_category AS cat ON PROFILE.company_category_id = cat.category_id\n" +
                "\t\tLEFT JOIN efaz_company.efaz_company_profile_cats AS cats ON PROFILE.company_id = cats.company_profile_id \n" +
                "\t) \n" +
                "GROUP BY\n" +
                "\tPROFILE.company_id;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new MultiCategoryProfileGetAllDTO(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9),
                        resultSet.getBytes(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getInt(13),
                        resultSet.getString(14), resultSet.getInt(15)));
    }

    public List<MultiCategoryProfileDTOS> getProfile(int profile) {
        String sql = "SELECT\n" +
                "\tcompany_id,\n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\tcompany_address,\n" +
                "\tcat.category_name,\n" +
                "\tcompany_link_youtube,\n" +
                "\tcompany_website_url,\n" +
                "\tcompany_lng,\n" +
                "\tcompany_lat,\n" +
                "\tcompany_cover_image,\n" +
                "\tcompany_phone_number,\n" +
                "\tcount( DISTINCT follow_id ) AS follower_count,\n" +
                "\tcount( DISTINCT offer_id ) AS order_count,\n" +
                "\tcompany_desc,\n" +
                "\tcompany_cat_id,\n" +
                "\tcategory.category_name \n" +
                "FROM\n" +
                "\t(\n" +
                "\t\t(\n" +
                "\t\t\t( efaz_company.efaz_company_profile AS PROFILE LEFT JOIN efaz_company.efaz_organization_following AS follow ON PROFILE.company_id = follow.follower_id )\n" +
                "\t\t\tLEFT JOIN efaz_company_offer AS offer ON PROFILE.company_id = offer.offer_company_id \n" +
                "\t\t)\n" +
                "\t\tINNER JOIN efaz_company_category AS cat ON PROFILE.company_category_id = cat.category_id\n" +
                "\t\tLEFT JOIN efaz_company.efaz_company_profile_cats AS cats ON PROFILE.company_id = cats.company_profile_id\n" +
                "\t\tINNER JOIN efaz_company_category AS category ON cats.company_cat_id = category.category_id \n" +
                "\t) \n" +
                "WHERE\n" +
                "\tPROFILE.company_id = ? \n" +
                "GROUP BY\n" +
                "\tcats.company_cat_id;";
        return jdbcTemplate.query(sql, new Object[]{profile},
                (resultSet, i) -> new MultiCategoryProfileDTOS(resultSet.getInt(1), resultSet.getString(2), resultSet.getBytes(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getFloat(8), resultSet.getFloat(9), resultSet.getBytes(10), resultSet.getString(11),
                        resultSet.getInt(12), resultSet.getInt(13), resultSet.getString(14), resultSet.getInt(15), resultSet.getString(16)));
    }

    public int updateProfile(int company_id, String company_name, byte[] company_logo_image, String company_address,
                             String company_category_id, String company_link_youtube, String company_website_url, float school_lng,
                             float school_lat, byte[] company_cover_image, String company_phone_number, String company_desc,
                             List<TakatfTenderCategoryPOJO> category) {
        jdbcTemplate.update("DELETE FROM efaz_company.efaz_company_profile_cats WHERE company_profile_id=?;", company_id);

        for (int i = 0; i < category.size(); i++) {
            int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                    Integer.class, "%" + category.get(i).getCategory_name().trim() + "%");

            jdbcTemplate.update("INSERT INTO efaz_company.efaz_company_profile_cats VALUES  (?,?,?)", null, company_id, categorys);
        }

        int category_ids = jdbcTemplate.queryForObject("SELECT category_id FROM efaz_company_category WHERE  category_name LIKE ?;",
                Integer.class, "%" + company_category_id + "%");


        return jdbcTemplate.update("update efaz_company_profile set company_name=?," +
                        "company_logo_image=?, company_address=?," +
                        "company_category_id=?, company_link_youtube=?, company_website_url=?, company_lng=?, company_lat=?," +
                        " company_cover_image=?, company_phone_number=?, company_desc=? " +
                        " where company_id=?;", company_name, company_logo_image, company_address, category_ids
                , company_link_youtube, company_website_url, school_lng, school_lat, company_cover_image, company_phone_number, company_desc, company_id);


    }


    public List<CompantProfileDto> getProfileByCategory(String id) {

        String sql = "SELECT\n" +
                "\tcompany_id,\n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\tcompany_address,\n" +
                "\tcategory_name,\n" +
                "\tcompany_link_youtube,\n" +
                "\tcompany_website_url,\n" +
                "\tcompany_lng,\n" +
                "\tcompany_lat,\n" +
                "\tcompany_cover_image,\n" +
                "\tcompany_phone_number,\n" +
                "\tcount( DISTINCT follow_id ) AS follower_count,\n" +
                "\tcount( DISTINCT offer_id ) AS order_count,\n" +
                "\tcompany_desc \n" +
                "FROM\n" +
                "\t(\n" +
                "\t\t(\n" +
                "\t\t\t( efaz_company.efaz_company_profile AS PROFILE LEFT JOIN efaz_company.efaz_organization_following AS follow ON PROFILE.company_id = follow.follower_id )\n" +
                "\t\t\tLEFT JOIN efaz_company_offer AS offer ON PROFILE.company_id = offer.offer_company_id\n" +
                "\t\t\tLEFT JOIN efaz_company.efaz_company_profile_cats AS cats ON PROFILE.company_id = cats.company_profile_id \n" +
                "\t\t)\n" +
                "\t\tINNER JOIN efaz_company_category AS cat ON cats.company_cat_id = cat.category_id \n" +
                "\t) \n" +
                "WHERE\n" +
                "\tcats.company_cat_id = ? \n" +
                "GROUP BY\n" +
                "\tPROFILE.company_id;";
        int category = jdbcTemplate.queryForObject("SELECT category_id FROM efaz_company_category WHERE  category_name LIKE ?;",
                Integer.class, "%" + id + "%");
        return jdbcTemplate.query(sql, new Object[]{category},
                (resultSet, i) -> new CompantProfileDto(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), resultSet.getFloat(9),
                        resultSet.getBytes(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getString(14)));
    }


    public boolean isExist(int company_id) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company_profile WHERE company_id=?;",
                Integer.class, company_id);
        return cnt != null && cnt > 0;
    }

    public int isCategoryExist(String catName) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM efaz_company_category WHERE  category_name=?;",
                Integer.class, catName);
    }

}
