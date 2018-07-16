package com.taj.controller;

import com.taj.model.SchoolReceivePlaceModel;
import com.taj.model.SchoolRequestCategoryModel;
import com.taj.repository.SchoolReceivePlaceRepo;
import com.taj.repository.SchoolRequestCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

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
    public int addCategory(@RequestBody SchoolReceivePlaceModel model) {
        return repo.addSchoolPlace(model.getPlace_name());

    }

    /**
     * @param model update current category
     */

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/update")
    public int  updateCategory(@RequestBody SchoolReceivePlaceModel model) {
        return repo.updateSchoolPlace(model.getPlace_id(), model.getPlace_name());

    }

    /**
     * @param model delete current category
     */

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/delete")
    public int  deleteCategory(@RequestBody SchoolReceivePlaceModel model) {
        return repo.deleteSchoolPlace(model.getPlace_id());

    }


}
