package com.taj.controller;

import com.taj.model.CompanyRequestCollectiveTenderModel;
import com.taj.repository.CompanyRequestCollectiveTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 9/2/2018.
 */
@RequestMapping("/company/request/collective")
@RestController
@CrossOrigin
public class CompanyRequestCollectiveTenderController {

    @Autowired
    CompanyRequestCollectiveTenderRepo repo;

    @PostMapping("/")
    public int addRequest(@RequestBody CompanyRequestCollectiveTenderModel model){
        return repo.addRequest(model.getResponse_takataf_company_id(), model.getResponse_takataf_request_id(), model.getResponsed_cost(), model.getIs_aproved(),
                model.getResponse_date(), model.getResponsed_from(), model.getResponsed_to());
    }
    @GetMapping("/")
    public List<CompanyRequestCollectiveTenderModel> getAll(){
        return  repo.getAll();
    }



}
