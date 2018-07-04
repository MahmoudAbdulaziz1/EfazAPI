package com.taj.controller;

import com.taj.model.SchoolReceivePlaceModel;
import com.taj.model.SchoolRequestCategoryModel;
import com.taj.repository.SchoolReceivePlaceRepo;
import com.taj.repository.SchoolRequestCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/4/2018.
 */
@RequestMapping("/receivePlace")
@RestController
@CrossOrigin
public class SchoolReceivePlaceController {

    @Autowired
    SchoolReceivePlaceRepo repo;

    /**
     * @return list of company categories
     */

    @GetMapping("/getAll")
    public List<SchoolReceivePlaceModel> getCategories() {
        return repo.getSchoolPlaces();
    }

    /**
     * @param id
     * @return category by id
     */

    @GetMapping("/get/{id}")
    public SchoolReceivePlaceModel getCategory(@PathVariable int id) {
        return repo.getPlace(id);
    }


    /**
     * @param model add company category to database
     */
    @PostMapping("/add")
    public void addCategory(@RequestBody SchoolReceivePlaceModel model) {
        repo.addSchoolPlace(model.getPlace_name());

    }

    /**
     * @param model update current category
     */

    @PutMapping("/update")
    public void updateCategory(@RequestBody SchoolReceivePlaceModel model) {
        repo.updateSchoolPlace(model.getPlace_id(), model.getPlace_name());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/delete")
    public void deleteCategory(@RequestBody SchoolReceivePlaceModel model) {
        repo.deleteSchoolPlace(model.getPlace_id());

    }


}
