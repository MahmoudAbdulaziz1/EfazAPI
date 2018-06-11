package com.taj.controller;

import com.taj.model.CategoryModel;
import com.taj.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Taj 51 on 6/10/2018.
 */
@RequestMapping("/cat")
@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryRepo repo;

    @GetMapping("/getCategories")
    public List<CategoryModel> getCategories(){
        return  repo.getCategories();
    }

    @GetMapping("/getCategory/{id}")
    public CategoryModel getCategory(@PathVariable int id){
        return  repo.getCategory(id);
    }

}
