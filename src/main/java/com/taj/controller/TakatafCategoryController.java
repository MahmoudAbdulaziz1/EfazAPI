package com.taj.controller;

import com.taj.model.TakatafCategoryModel;
import com.taj.repository.TakatafCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@RequestMapping("/takatafCategory")
@RestController
@CrossOrigin
public class TakatafCategoryController {

    @Autowired
    TakatafCategoryRepo repo;

    /**
     * @return list of company categories
     */

    @GetMapping("/getCategories")
    public List<TakatafCategoryModel> getCategories() {
        return repo.getTakatafCategories();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/getCategory/{id}")
    public TakatafCategoryModel getCategory(@PathVariable int id) {
        return repo.getTakatafCategory(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/addCategory")
    public void addCategory(@RequestBody TakatafCategoryModel model) {
        repo.addTkatafCategories(model.getCat_name());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/updateCategory")
    public void updateCategory(@RequestBody TakatafCategoryModel model) {
        repo.updateTakatafCategory(model.getCat_id(), model.getCat_name());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deleteCategory")
    public void deleteCategory(@RequestBody TakatafCategoryModel model) {
        repo.deleteTakatafCategory(model.getCat_id());

    }

}
