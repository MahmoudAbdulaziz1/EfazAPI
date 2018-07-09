package com.taj.controller;

import com.taj.model.TakatafFirstPriceModel;
import com.taj.repository.TakatafFirstPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@RequestMapping("/takatafFirst")
@RestController
@CrossOrigin
public class TakatafFirstPriceController {


    @Autowired
    TakatafFirstPriceRepo repo;

    /**
     * @return list of company categories
     */

    @GetMapping("/getPrices")
    public List<TakatafFirstPriceModel> getCategories() {
        return repo.getTkatafFirstPrices();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/getPrice/{id}")
    public TakatafFirstPriceModel getCategory(@PathVariable int id) {
        return repo.getTkatafFirstPrice(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/addPrice")
    public void addCategory(@RequestBody TakatafFirstPriceModel model) {
        repo.addTkatafFirstPrice(model.getF_from(), model.getF_to(), model.getF_price());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/updatePrice")
    public void updateCategory(@RequestBody TakatafFirstPriceModel model) {
        repo.updateTkatafFirstPrice(model.getF_id(), model.getF_from(), model.getF_to(), model.getF_price());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deletePrice")
    public void deleteCategory(@RequestBody TakatafFirstPriceModel model) {
        repo.deleteTkatafFirstPrice(model.getF_id());

    }

}
