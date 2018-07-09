package com.taj.controller;

import com.taj.model.CompanyResponseSchoolRequestModel;
import com.taj.repository.CompanyResponseSchoolRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@RequestMapping("/responseSchoolRequest")
@RestController
@CrossOrigin
public class CompanyResponseSchoolRequestController {

    @Autowired
    CompanyResponseSchoolRequestRepo repo;

    @PostMapping("/add")
    public int addCompanyResponseSchoolRequest(@RequestBody CompanyResponseSchoolRequestModel model) {
        return repo.addResponseSchoolRequest(model.getResponse_id(), model.getResponsed_company_id(), model.getResponsed_request_id(), model.getResponsed_from(),
                model.getResponsed_to(), model.getResponsed_cost(), model.getIs_aproved());
    }

    @GetMapping("/getAll")
    public List<CompanyResponseSchoolRequestModel> getCompanyResponseSchoolRequest() {
        return repo.getResponseSchoolRequest();
    }

    @GetMapping("/get/{id}")
    public CompanyResponseSchoolRequestModel getCompanyResponseSchoolRequest(@PathVariable int id) {
        return repo.getResponseSchoolRequest(id);
    }

    @GetMapping("/getCompany/{companyId}")
    public List<CompanyResponseSchoolRequestModel> getCompanyResponseSchoolRequestByCompany(@PathVariable int companyId) {
        return repo.getResponseSchoolRequestByCompany(companyId);
    }

    @GetMapping("/getRequest/{requestId}")
    public List<CompanyResponseSchoolRequestModel> CompanyResponseSchoolRequestByRequest(@PathVariable int requestId) {
        return repo.getResponseSchoolRequestByRequest(requestId);
    }

    @GetMapping("/getAccept/{accept}")
    public List<CompanyResponseSchoolRequestModel> CompanyResponseSchoolRequestByAprove(@PathVariable int accept) {
        return repo.getResponseSchoolRequestByAccept(accept);
    }

    @PutMapping("/accept/{id}")
    public int acceptCompanyResponseSchoolRequest(@PathVariable int id) {
        return repo.acceptResponseSchoolRequest(id);
    }
    @PutMapping("/refuse/{id}")
    public int refuseCompanyResponseSchoolRequest(@PathVariable int id) {
        return repo.refuseResponseSchoolRequest(id);
    }

    @PutMapping("/update")
    public int updateCompanyResponseSchoolRequest(@RequestBody CompanyResponseSchoolRequestModel model) {
        return repo.updateResponseSchoolRequest(model.getResponse_id(), model.getResponsed_company_id(), model.getResponsed_request_id(),
                model.getResponsed_from(), model.getResponsed_to(), model.getResponsed_cost(), model.getIs_aproved());
    }

    @PutMapping("/delete/{id}")
    public int deleteCompanyResponseSchoolRequest(@PathVariable int id) {
        return repo.deleteResponseSchoolRequest(id);
    }
}
