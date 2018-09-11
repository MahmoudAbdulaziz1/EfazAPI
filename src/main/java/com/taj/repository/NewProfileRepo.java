package com.taj.repository;

import com.taj.model.NewProfileDto;
import com.taj.model.NewProfileDto2;
import com.taj.model.NewProfileModel;
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
 * Created by User on 9/11/2018.
 */
@Repository
public class NewProfileRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean isExist(int companyId) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM efaz_company_profile WHERE company_id=?;",
                Integer.class, companyId);
        return cnt != null && cnt > 0;
    }


    public int addProfileWithCategories(int companyId, String companyName, byte[] companyLogoImage, String companyAddress,
                                        String companyLinkYoutube, String companyWebsiteUrl, float schoolLng,
                                        float schoolLat, byte[] companyCoverImage, String companyPhoneNumber, String companyDesc,
                                        List<TakatfTenderCategoryPOJO> category) {
        jdbcTemplate.update("SET FOREIGN_KEY_CHECKS=0;");

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement("INSERT INTO efaz_company_profile VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, companyId);
                ps.setString(2, companyName);
                ps.setBytes(3, companyLogoImage);
                ps.setString(4, companyAddress);
                ps.setInt(5, 1);
                ps.setString(6, companyLinkYoutube);
                ps.setString(7, companyWebsiteUrl);
                ps.setFloat(8, schoolLng);
                ps.setFloat(9, schoolLat);
                ps.setBytes(10, companyCoverImage);
                ps.setString(11, companyPhoneNumber);
                ps.setString(12, companyDesc);
                return ps;
            }

        }, key);

        int profileID = key.getKey().intValue();


        for (int i = 0; i < category.size(); i++) {
            int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                    Integer.class, "%" + category.get(i).getCategory_name().trim() + "%");

            jdbcTemplate.update("INSERT INTO efaz_company.efaz_company_profile_cats VALUES  (?,?,?)", null, profileID, categorys);
        }


        return profileID;
    }

    public List<NewProfileModel> getProfiles() {

        String sql = "SELECT\n" +
                "\tcompany_id,\n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\tcompany_address,\n" +
                "\tcompany_link_youtube,\n" +
                "\tcompany_website_url,\n" +
                "\tcompany_lng,\n" +
                "\tcompany_lat,\n" +
                "\tcompany_cover_image,\n" +
                "\tcompany_phone_number,\n" +
                "\tcount( DISTINCT follow_id ) AS follower_count,\n" +
                "\tcount( DISTINCT offer_id ) AS order_count,\n" +
                "\tcompany_desc,\n" +
                "\tCOUNT( DISTINCT id ) AS category_num,\n" +
                "\tcity,\n" +
                "\tarea \n" +
                "FROM\n" +
                "\t(\n" +
                "\t\t(\n" +
                "\t\t\t( efaz_company.efaz_company_profile AS PROFILE LEFT JOIN efaz_company.efaz_organization_following AS follow ON PROFILE.company_id = follow.follower_id )\n" +
                "\t\t\tLEFT JOIN efaz_company_offer AS offer ON PROFILE.company_id = offer.offer_company_id \n" +
                "\t\t)\n" +
                "\t\tLEFT JOIN efaz_company.efaz_company_profile_cats AS cats ON PROFILE.company_id = cats.company_profile_id \n" +
                "\t)\n" +
                "\tLEFT JOIN efaz_company.efaz_login AS pc ON PROFILE.company_id = pc.login_id \n" +
                "GROUP BY\n" +
                "\tPROFILE.company_id;";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> new NewProfileModel(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getFloat(7), resultSet.getFloat(8),
                        resultSet.getBytes(9), resultSet.getString(10), resultSet.getInt(11), resultSet.getInt(12),
                        resultSet.getString(13), resultSet.getInt(14), resultSet.getString(15), resultSet.getString(16)));
    }


    public List<NewProfileDto> getProfile(int profile) {
        String sql = "SELECT\n" +
                "\tcompany_id,\n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\tcompany_address,\n" +
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
                "\tcategory.category_name,\n" +
                "\tcity,\n" +
                "\tarea \n" +
                "FROM\n" +
                "\t(\n" +
                "\t\t(\n" +
                "\t\t\t( efaz_company.efaz_company_profile AS PROFILE LEFT JOIN efaz_company.efaz_organization_following AS follow ON PROFILE.company_id = follow.follower_id )\n" +
                "\t\t\tLEFT JOIN efaz_company_offer AS offer ON PROFILE.company_id = offer.offer_company_id \n" +
                "\t\t)\n" +
                "\t\tLEFT JOIN efaz_company.efaz_company_profile_cats AS cats ON PROFILE.company_id = cats.company_profile_id\n" +
                "\t\tLEFT JOIN efaz_company.efaz_login AS pc ON PROFILE.company_id = pc.login_id\n" +
                "\t\tINNER JOIN efaz_company_category AS category ON cats.company_cat_id = category.category_id \n" +
                "\t) \n" +
                "WHERE\n" +
                "\tPROFILE.company_id = ? \n" +
                "GROUP BY\n" +
                "\tcats.company_cat_id;";
        return jdbcTemplate.query(sql, new Object[]{profile},
                (resultSet, i) -> new NewProfileDto(resultSet.getInt(1), resultSet.getString(2), resultSet.getBytes(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getFloat(7), resultSet.getFloat(8), resultSet.getBytes(9), resultSet.getString(10),
                        resultSet.getInt(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getInt(14),
                        resultSet.getString(15), resultSet.getString(16), resultSet.getString(17)));
    }


    public int updateProfile(int companyId, String companyName, byte[] companyLogoImage, String companyAddress,
                             String companyLinkYoutube, String companyWebsiteUrl, float schoolLng,
                             float schoolLat, byte[] companyCoverImage, String companyPhoneNumber, String companyDesc,
                             String city, String area, List<TakatfTenderCategoryPOJO> category) {
        jdbcTemplate.update("DELETE FROM efaz_company.efaz_company_profile_cats WHERE company_profile_id=?;", companyId);

        for (int i = 0; i < category.size(); i++) {
            int categorys = jdbcTemplate.queryForObject("SELECT category_id  FROM  efaz_company.efaz_company_category WHERE  category_name LIKE ?;",
                    Integer.class, "%" + category.get(i).getCategory_name().trim() + "%");

            jdbcTemplate.update("INSERT INTO efaz_company.efaz_company_profile_cats VALUES  (?,?,?)", null, companyId, categorys);
        }

        jdbcTemplate.update("update efaz_company.efaz_login set city=?," +
                        "area=?  "+
                        " where login_id=?;", city, area, companyId);

        return jdbcTemplate.update("update efaz_company_profile set company_name=?," +
                        "company_logo_image=?, company_address=?," +
                        "company_category_id=?, company_link_youtube=?, company_website_url=?, company_lng=?, company_lat=?," +
                        " company_cover_image=?, company_phone_number=?, company_desc=? " +
                        " where company_id=?;", companyName, companyLogoImage, companyAddress, 2
                , companyLinkYoutube, companyWebsiteUrl, schoolLng, schoolLat, companyCoverImage, companyPhoneNumber, companyDesc, companyId);


    }


    public List<NewProfileDto2> getProfileByCategory(String id) {

        String sql = "SELECT\n" +
                "\tcompany_id,\n" +
                "\tcompany_name,\n" +
                "\tcompany_logo_image,\n" +
                "\tcompany_address,\n" +
                "\tcompany_link_youtube,\n" +
                "\tcompany_website_url,\n" +
                "\tcompany_lng,\n" +
                "\tcompany_lat,\n" +
                "\tcompany_cover_image,\n" +
                "\tcompany_phone_number,\n" +
                "\tcount( DISTINCT follow_id ) AS follower_count,\n" +
                "\tcount( DISTINCT offer_id ) AS order_count,\n" +
                "\tcompany_desc,\n" +
                "\tcity,\n" +
                "\tarea \n" +
                "FROM\n" +
                "\t(\n" +
                "\t\t(\n" +
                "\t\t\t( efaz_company.efaz_company_profile AS PROFILE LEFT JOIN efaz_company.efaz_organization_following AS follow ON PROFILE.company_id = follow.follower_id )\n" +
                "\t\t\tLEFT JOIN efaz_company_offer AS offer ON PROFILE.company_id = offer.offer_company_id\n" +
                "\t\t\tLEFT JOIN efaz_company.efaz_company_profile_cats AS cats ON PROFILE.company_id = cats.company_profile_id\n" +
                "\t\t\tLEFT JOIN efaz_company.efaz_login AS pc ON PROFILE.company_id = pc.login_id \n" +
                "\t\t) \n" +
                "\t) \n" +
                "WHERE\n" +
                "\tcats.company_cat_id = ? \n" +
                "GROUP BY\n" +
                "\tPROFILE.company_id;";
        int category = jdbcTemplate.queryForObject("SELECT category_id FROM efaz_company_category WHERE  category_name LIKE ?;",
                Integer.class, "%" + id + "%");
        return jdbcTemplate.query(sql, new Object[]{category},
                (resultSet, i) -> new NewProfileDto2(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBytes(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getFloat(7), resultSet.getFloat(8),
                        resultSet.getBytes(9), resultSet.getString(10), resultSet.getInt(11), resultSet.getInt(12),
                        resultSet.getString(13), resultSet.getString(14), resultSet.getString(15)));
    }


    public int isCategoryExist(String catName) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM efaz_company_category WHERE  category_name=?;",
                Integer.class, catName);
    }


}
