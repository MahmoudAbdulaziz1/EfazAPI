package com.taj.controller;

import com.taj.model.TakatafCategoryModel;
import com.taj.repository.TakatafCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/5/2018.
 */
@RequestMapping("/evvaz/takataf/category")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TakatafCategoryController {

    @Autowired
    TakatafCategoryRepo repo;

    /**
     * @return list of company categories
     */

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('company')")
    public List<TakatafCategoryModel> getCategories() {
        return repo.getTakatafCategories();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('company')")
    public TakatafCategoryModel getCategory(@PathVariable int id) {
        return repo.getTakatafCategory(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public int  addCategory(@RequestBody TakatafCategoryModel model) {
        return repo.addTkatafCategories(model.getCat_name());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin')")
    public int updateCategory(@RequestBody TakatafCategoryModel model) {
        return repo.updateTakatafCategory(model.getCat_id(), model.getCat_name());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public int deleteCategory(@RequestBody TakatafCategoryModel model) {
        return repo.deleteTakatafCategory(model.getCat_id());

    }

}
