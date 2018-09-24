package com.taj.repository;

import com.taj.model.RecievesNewsMailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 9/10/2018.
 */
@Repository
public class RecievesNewsMailRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addMail(String mail) {
        if (!checkIfExistByMail(mail)) {
            return jdbcTemplate.update("INSERT INTO efaz_recieve_news_mails VALUES(?,?);", null, mail);
        }
        return 0;
    }

    public List<RecievesNewsMailsModel> getAllMails() {
        return jdbcTemplate.query("SELECT * FROM efaz_recieve_news_mails;",
                (resultSet, i) -> new RecievesNewsMailsModel(resultSet.getInt(1), resultSet.getString(2)));
    }

    public RecievesNewsMailsModel getMailById(int mail) {
        return jdbcTemplate.queryForObject("SELECT * FROM efaz_recieve_news_mails WHERE mail_id=?;", new Object[]{mail},
                (resultSet, i) -> new RecievesNewsMailsModel(resultSet.getInt(1), resultSet.getString(2)));
    }

    public int updateMailById(int id, String mail) {
        return jdbcTemplate.update("UPDATE efaz_recieve_news_mails SET mail=? WHERE mail_id=?;", mail, id);
    }

    public int deleteMailById(int id) {
        return jdbcTemplate.update("DELETE FROM  efaz_recieve_news_mails WHERE mail_id=?", id);
    }

    public boolean checkIfExist(int mailId) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_recieve_news_mails WHERE mail_id=?;",
                Integer.class, mailId);
        return cnt != null && cnt > 0;
    }

    public boolean checkIfExistByMail(String mailId) {
        Integer cnt = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM  efaz_recieve_news_mails WHERE mail LIKE ?;",
                Integer.class, "%" + mailId + "%");
        return cnt != null && cnt > 0;
    }

}
