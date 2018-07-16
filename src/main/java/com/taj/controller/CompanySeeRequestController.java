package com.taj.controller;

import com.taj.model.CompanySeeRequestModel;
import com.taj.repository.CompanySeeRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@RequestMapping("/evvaz/seen")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CompanySeeRequestController {


    @Autowired
    CompanySeeRequestRepo repo;

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/add")
    public int addCompanySeeRequest(@RequestBody CompanySeeRequestModel model) {
        return repo.addCompanySeeRequest(model.getRequest_id(), model.getRequest_company_id());
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getAll")
    public List<CompanySeeRequestModel> getCompanySeeRequest() {
        return repo.getCompanySeeRequests();
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getRequest/{id}")
    public List<CompanySeeRequestModel> getCompanySeeRequestByRequestId(@PathVariable int id) {
        return repo.getCompanySeeRequestsByRequestId(id);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getCompany/{id}")
    public List<CompanySeeRequestModel> getCompanySeeRequestByCompanyId(@PathVariable int id) {
        return repo.getCompanySeeRequestsByCompanyId(id);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getSeen/{id}")
    public CompanySeeRequestModel getCompanySeeRequestBySeenId(@PathVariable int id) {
        return repo.getCompanySeeRequest(id);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getBoth/{requestId}/{companyId}")
    public List<CompanySeeRequestModel> getCompanySeeRequestByBoth(@PathVariable int requestId, @PathVariable int companyId) {
        return repo.getCompanySeeRequestsByRequestIdANDCompanyId(requestId, companyId);
    }

    /**
     * @param model update current category
     */

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/update")
    public int  updateCategory(@RequestBody CompanySeeRequestModel model) {
        return repo.updateCompanySeeRequest(model.getSeen_id(), model.getRequest_company_id(), model.getRequest_id());

    }

    /**
     * @param model delete current category
     */

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/delete")
    public int  deleteCategory(@RequestBody CompanySeeRequestModel model) {
        return repo.deleteCompanySeeRequest(model.getSeen_id());

    }
}
