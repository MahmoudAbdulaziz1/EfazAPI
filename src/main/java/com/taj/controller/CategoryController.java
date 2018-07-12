package com.taj.controller;

import com.taj.model.CategoryModel;
import com.taj.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Taj 51 on 6/10/2018.
 */
@RequestMapping("/evvaz/cat")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CategoryController {

    @Autowired
    CategoryRepo repo;

    /**
     * @return list of company categories
     */
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getCategories")
    public List<CategoryModel> getCategories() {
        return repo.getCategories();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/getCategory/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public CategoryModel getCategory(@PathVariable int id) {
        return repo.getCategory(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/addCategory")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public void addCategory(@RequestBody CategoryModel model) {
        repo.addCategory(model.getCategory_name());

    }

    /**
     * @param model update current category
     */


    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @PutMapping("/updateCategory")
    public void updateCategory(@RequestBody CategoryModel model) {
        repo.updateCategory(model.getCategory_id(), model.getCategory_name());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deleteCategory")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public void deleteCategory(@RequestBody CategoryModel model) {
        repo.deleteCategory(model.getCategory_id());

    }

}
