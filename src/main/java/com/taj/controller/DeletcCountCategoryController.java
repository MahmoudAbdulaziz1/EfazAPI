package com.taj.controller;

import com.taj.repository.CategoryCountDemoREpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by User on 9/2/2018.
 */
@RequestMapping("/del")
@RestController
@CrossOrigin
public class DeletcCountCategoryController {
    @Autowired
    CategoryCountDemoREpo repo;
    @DeleteMapping("/{id}")
    public  int delete(@PathVariable int id){
        return  repo.deleteCat(id);
    }
}
