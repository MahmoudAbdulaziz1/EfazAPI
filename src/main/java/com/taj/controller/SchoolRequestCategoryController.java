package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.SchoolRequestCategoryModel;
import com.taj.model.schoolCategoriesToWEBSITE;
import com.taj.repository.SchoolRequestCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@RequestMapping("/school/category")
@RestController
@CrossOrigin
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolRequestCategoryController {

    @Autowired
    SchoolRequestCategoryRepo repo;

    @Autowired
    ObjectMapper mapper;

    /**
     * @return list of company categories
     */

    @GetMapping("/getAll")
//    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestCategoryModel> getCategories() {
        return repo.getSchoolRequestCategories();
    }
    @GetMapping("/getCat")
    public List<schoolCategoriesToWEBSITE> getSchoolRequestCategoriesForWeb() {
        return  repo.getSchoolRequestCategoriesForWeb();
    }

    @GetMapping("/getCat/{id}")
    public List<schoolCategoriesToWEBSITE> getSchoolRequestCategoriesForWebByID(@PathVariable int id) {
        return  repo.getSchoolRequestCategoriesForWebById(id);
    }
    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/getCategory/{id}")
//    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public SchoolRequestCategoryModel getCategory(@PathVariable int id) {
        return repo.getSchoolRequestCategory(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/addCategory")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public ObjectNode addCategory(@Valid @RequestBody SchoolRequestCategoryModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.addSchoolRequestCategories(model.getRequest_category_name());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("request_category_name", model.getRequest_category_name());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

    /**
     * @param model update current category
     */

    @PutMapping("/updateCategory")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public ObjectNode updateCategory(@Valid @RequestBody SchoolRequestCategoryModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateSchoolRequestCategory(model.getRequest_category_id(), model.getRequest_category_name());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("request_category_id", model.getRequest_category_id());
            objectNode.put("request_category_name", model.getRequest_category_name());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deleteCategory")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public ObjectNode deleteCategory(@Valid @RequestBody SchoolRequestCategoryModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.deleteSchoolRequestCategory(model.getRequest_category_id());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }


    }
}
