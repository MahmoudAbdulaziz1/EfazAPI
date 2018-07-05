package com.taj.repository;

import com.taj.model.TakatafFirstPriceModel;
import com.taj.model.TakatafSecondPriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@Repository
public class TakatafSecondPriceRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTkatafSecondPrice(int s_from, int s_to, double s_price) {
        return jdbcTemplate.update("INSERT INTO takatf_second_price VALUES (?,?,?,?)", null, s_from, s_to, s_price);
    }

    public List<TakatafSecondPriceModel> getTkatafFirstPrices() {
        return jdbcTemplate.query("SELECT * FROM takatf_second_price",
                ((resultSet, i) -> new TakatafSecondPriceModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDouble(4))));
    }

    public TakatafSecondPriceModel getTkatafSecondPrice(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM takatf_second_price WHERE s_id=?", new Object[]{id},
                ((resultSet, i) -> new TakatafSecondPriceModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDouble(4))));
    }

    public int updateTkatafSecondPrice(int id, int from, int to, double price) {
        return jdbcTemplate.update("UPDATE takatf_second_price SET s_from=?, s_to=?,s_price=? WHERE s_id=?",
                from, to, price, id);
    }

    public int deleteTkatafSecondPrice(int id) {
        return jdbcTemplate.update("DELETE FROM takatf_second_price WHERE s_id=?", id);
    }


}
