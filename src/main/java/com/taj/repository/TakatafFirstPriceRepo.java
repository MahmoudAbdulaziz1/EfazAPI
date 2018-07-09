package com.taj.repository;

import com.taj.model.TakatafFirstPriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@Repository
public class TakatafFirstPriceRepo {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTkatafFirstPrice(int f_from, int f_to, double f_price) {
        return jdbcTemplate.update("INSERT INTO takatf_first_price VALUES (?,?,?,?)", null, f_from, f_to, f_price);
    }

    public List<TakatafFirstPriceModel> getTkatafFirstPrices() {
        return jdbcTemplate.query("SELECT * FROM takatf_first_price",
                ((resultSet, i) -> new TakatafFirstPriceModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDouble(4))));
    }

    public TakatafFirstPriceModel getTkatafFirstPrice(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM takatf_first_price WHERE f_id=?", new Object[]{id},
                ((resultSet, i) -> new TakatafFirstPriceModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDouble(4))));
    }

    public int updateTkatafFirstPrice(int id, int from, int to, double price) {
        return jdbcTemplate.update("UPDATE takatf_first_price SET f_from=?, f_to=?,f_price=? WHERE f_id=?",
                from, to, price, id);
    }

    public int deleteTkatafFirstPrice(int id) {
        return jdbcTemplate.update("DELETE FROM takatf_first_price WHERE f_id=?", id);
    }

}
