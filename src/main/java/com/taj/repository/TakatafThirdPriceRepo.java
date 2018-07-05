package com.taj.repository;

import com.taj.model.TakatafThirdPriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@Repository
public class TakatafThirdPriceRepo {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTkatafThirdPrice(int s_from, int s_to, double s_price) {
        return jdbcTemplate.update("INSERT INTO takatf_third_price VALUES (?,?,?,?)", null, s_from, s_to, s_price);
    }

    public List<TakatafThirdPriceModel> getTkatafThirdPrices() {
        return jdbcTemplate.query("SELECT * FROM takatf_third_price",
                ((resultSet, i) -> new TakatafThirdPriceModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDouble(4))));
    }

    public TakatafThirdPriceModel getTkatafThirdPrice(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM takatf_third_price WHERE t_id=?", new Object[]{id},
                ((resultSet, i) -> new TakatafThirdPriceModel(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDouble(4))));
    }

    public int updateTkatafSThirdPrice(int id, int from, int to, double price) {
        return jdbcTemplate.update("UPDATE takatf_third_price SET t_from=?, t_to=?,t_price=? WHERE t_id=?",
                from, to, price, id);
    }

    public int deleteTkatafThirdPrice(int id) {
        return jdbcTemplate.update("DELETE FROM takatf_third_price WHERE t_id=?", id);
    }

}
