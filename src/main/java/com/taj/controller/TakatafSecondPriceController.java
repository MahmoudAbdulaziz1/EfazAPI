package com.taj.controller;

import com.taj.model.TakatafFirstPriceModel;
import com.taj.model.TakatafSecondPriceModel;
import com.taj.repository.TakatafFirstPriceRepo;
import com.taj.repository.TakatafSecondPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@RequestMapping("/takatafSecond")
@RestController
@CrossOrigin
public class TakatafSecondPriceController {

    @Autowired
    TakatafSecondPriceRepo repo;

    /**
     * @return list of company categories
     */

    @GetMapping("/getPrices")
    public List<TakatafSecondPriceModel> getCategories() {
        return repo.getTkatafFirstPrices();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/getPrice/{id}")
    public TakatafSecondPriceModel getCategory(@PathVariable int id) {
        return repo.getTkatafSecondPrice(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/addPrice")
    public void addCategory(@RequestBody TakatafSecondPriceModel model) {
        repo.addTkatafSecondPrice(model.getS_from(), model.getS_to(), model.getS_price());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/updatePrice")
    public void updateCategory(@RequestBody TakatafSecondPriceModel model) {
        repo.updateTkatafSecondPrice(model.getS_id(), model.getS_from(), model.getS_to(), model.getS_price());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deletePrice")
    public void deleteCategory(@RequestBody TakatafSecondPriceModel model) {
        repo.deleteTkatafSecondPrice(model.getS_id());

    }

}
