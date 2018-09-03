package com.taj.controller;

import com.taj.model.CollectiveTenderDetailsForCompanyModel;
import com.taj.repository.CollectiveTenderDetailsForCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 9/3/2018.
 */
@RequestMapping("/tender/details/company")
@RestController
@CrossOrigin
public class CollectiveTenderDetailsForCompanyController {


    @Autowired
    CollectiveTenderDetailsForCompanyRepo repo;


    @GetMapping("/{id}")
    public List<CollectiveTenderDetailsForCompanyModel> getTenderDetails(@PathVariable int id){
        return repo.getTenderDetails(id);
    }
}
