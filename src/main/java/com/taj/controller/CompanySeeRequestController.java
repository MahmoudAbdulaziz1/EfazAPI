package com.taj.controller;

import com.taj.model.CompanySeeRequestModel;
import com.taj.model.SchoolRequestCategoryModel;
import com.taj.repository.CompanySeeRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@RequestMapping("/seen")
@RestController
@CrossOrigin
public class CompanySeeRequestController {


    @Autowired
    CompanySeeRequestRepo repo;

    @PostMapping("/add")
    public int addCompanySeeRequest(@RequestBody CompanySeeRequestModel model) {
        return repo.addCompanySeeRequest(model.getRequest_id(), model.getRequest_company_id());
    }

    @GetMapping("/getAll")
    public List<CompanySeeRequestModel> getCompanySeeRequest() {
        return repo.getCompanySeeRequests();
    }

    @GetMapping("/getRequest/{id}")
    public List<CompanySeeRequestModel> getCompanySeeRequestByRequestId(@PathVariable int id) {
        return repo.getCompanySeeRequestsByRequestId(id);
    }

    @GetMapping("/getCompany/{id}")
    public List<CompanySeeRequestModel> getCompanySeeRequestByCompanyId(@PathVariable int id) {
        return repo.getCompanySeeRequestsByCompanyId(id);
    }

    @GetMapping("/getSeen/{id}")
    public CompanySeeRequestModel getCompanySeeRequestBySeenId(@PathVariable int id) {
        return repo.getCompanySeeRequest(id);
    }

    @GetMapping("/getBoth/{requestId}/{companyId}")
    public List<CompanySeeRequestModel> getCompanySeeRequestByBoth(@PathVariable int requestId, @PathVariable int companyId) {
        return repo.getCompanySeeRequestsByRequestIdANDCompanyId(requestId, companyId);
    }

    /**
     *
     * @param model
     * update current category
     */

    @PutMapping("/updateCompanySeeRequest")
    public void updateCategory(@RequestBody CompanySeeRequestModel model){
        repo.updateCompanySeeRequest(model.getSeen_id(), model.getRequest_company_id(), model.getRequest_id());

    }

    /**
     *
     * @param model
     *
     * delete current category
     */

    @PutMapping("/deleteCompanySeeRequest")
    public void deleteCategory(@RequestBody CompanySeeRequestModel model){
        repo.deleteCompanySeeRequest(model.getSeen_id());

    }
}
