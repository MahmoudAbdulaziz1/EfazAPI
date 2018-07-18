package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.SchoolReceivePlaceModel;
import com.taj.model.SchoolRequestCategoryModel;
import com.taj.repository.SchoolReceivePlaceRepo;
import com.taj.repository.SchoolRequestCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/4/2018.
 */
@RequestMapping("/evvaz/receive/place")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolReceivePlaceController {

    @Autowired
    SchoolReceivePlaceRepo repo;
    @Autowired
    ObjectMapper mapper;

    /**
     * @return list of company categories
     */

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getAll")
    public List<SchoolReceivePlaceModel> getCategories() {
        return repo.getSchoolPlaces();
    }

    /**
     * @param id
     * @return category by id
     */

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/get/{id}")
    public SchoolReceivePlaceModel getCategory(@PathVariable int id) {
        return repo.getPlace(id);
    }


    /**
     * @param model add company category to database
     */
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/add")
    public ObjectNode addCategory(@Valid @RequestBody SchoolReceivePlaceModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res =  repo.addSchoolPlace(model.getPlace_name());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("login_id", model.getCategory_id());
            objectNode.put("place_name", model.getPlace_name());

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

    /**
     * @param model update current category
     */

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/update")
    public ObjectNode  updateCategory(@Valid @RequestBody SchoolReceivePlaceModel model, Errors errors) {
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateSchoolPlace(model.getPlace_id(), model.getPlace_name());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("place_id", model.getPlace_id());
            objectNode.put("place_name", model.getPlace_name());

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

    /**
     * @param model delete current category
     */

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/delete")
    public ObjectNode  deleteCategory(@Valid @RequestBody SchoolReceivePlaceModel model, Errors errors) {
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.deleteSchoolPlace(model.getPlace_id());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("login_id", model.getCategory_id());
            objectNode.put("value", "success");

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }


}
