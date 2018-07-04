package com.taj.controller;

import com.taj.model.SchoolRequestCategoryModel;
import com.taj.repository.SchoolRequestCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@RequestMapping("/schoolCategory")
@RestController
@CrossOrigin
public class SchoolRequestCategoryController {

    @Autowired
    SchoolRequestCategoryRepo repo;

    /**
     * @return list of company categories
     */

    @GetMapping("/getCategories")
    public List<SchoolRequestCategoryModel> getCategories() {
        return repo.getSchoolRequestCategories();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/getCategory/{id}")
    public SchoolRequestCategoryModel getCategory(@PathVariable int id) {
        return repo.getSchoolRequestCategory(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/addCategory")
    public void addCategory(@RequestBody SchoolRequestCategoryModel model) {
        repo.addSchoolRequestCategories(model.getRequest_category_name());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/updateCategory")
    public void updateCategory(@RequestBody SchoolRequestCategoryModel model) {
        repo.updateSchoolRequestCategory(model.getRequest_category_id(), model.getRequest_category_name());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deleteCategory")
    public void deleteCategory(@RequestBody SchoolRequestCategoryModel model) {
        repo.deleteSchoolRequestCategory(model.getRequest_category_id());

    }
}
