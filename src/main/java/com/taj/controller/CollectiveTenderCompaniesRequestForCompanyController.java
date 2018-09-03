package com.taj.controller;

import com.taj.model.CollectiveTenderCompaniesRequestForCompanyModel;
import com.taj.repository.CollectiveTenderCompaniesRequestForCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 9/3/2018.
 */
@RequestMapping("/tender/companies")
@CrossOrigin
@RestController
public class CollectiveTenderCompaniesRequestForCompanyController {

    @Autowired
    CollectiveTenderCompaniesRequestForCompanyRepo repo;

    @GetMapping("/{id}")
    public List<CollectiveTenderCompaniesRequestForCompanyModel> getTenderCompanies(@PathVariable int id){
        return repo.getTenderCompanies(id);
    }

    @PutMapping("/{company_id}/{request_id}")
    public int approveRequest(@PathVariable int company_id,@PathVariable int request_id){
        return repo.approveRequest(company_id, request_id);
    }
}
