package com.taj.controller;

import com.taj.model.SchoolRequestCategoryModel;
import com.taj.repository.SchoolRequestCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@RequestMapping("/evvaz/school/category")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolRequestCategoryController {

    @Autowired
    SchoolRequestCategoryRepo repo;

    /**
     * @return list of company categories
     */

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestCategoryModel> getCategories() {
        return repo.getSchoolRequestCategories();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/getCategory/{id}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public SchoolRequestCategoryModel getCategory(@PathVariable int id) {
        return repo.getSchoolRequestCategory(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/addCategory")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public void addCategory(@RequestBody SchoolRequestCategoryModel model) {
        repo.addSchoolRequestCategories(model.getRequest_category_name());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/updateCategory")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public void updateCategory(@RequestBody SchoolRequestCategoryModel model) {
        repo.updateSchoolRequestCategory(model.getRequest_category_id(), model.getRequest_category_name());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deleteCategory")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public void deleteCategory(@RequestBody SchoolRequestCategoryModel model) {
        repo.deleteSchoolRequestCategory(model.getRequest_category_id());

    }
}
