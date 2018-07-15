package com.taj.controller;

import com.taj.model.CompanyResponseSchoolRequestModel;
import com.taj.repository.CompanyResponseSchoolRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@RequestMapping("/evvaz/response/school/request")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CompanyResponseSchoolRequestController {

    @Autowired
    CompanyResponseSchoolRequestRepo repo;

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/add")
    public int addCompanyResponseSchoolRequest(@RequestBody CompanyResponseSchoolRequestModel model) {
        return repo.addResponseSchoolRequest(model.getResponse_id(), model.getResponsed_company_id(), model.getResponsed_request_id(), model.getResponsed_from(),
                model.getResponsed_to(), model.getResponsed_cost(), model.getIs_aproved());
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getAll")
    public List<CompanyResponseSchoolRequestModel> getCompanyResponseSchoolRequest() {
        return repo.getResponseSchoolRequest();
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/get/{id}")
    public CompanyResponseSchoolRequestModel getCompanyResponseSchoolRequest(@PathVariable int id) {
        return repo.getResponseSchoolRequest(id);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getCompany/{companyId}")
    public List<CompanyResponseSchoolRequestModel> getCompanyResponseSchoolRequestByCompany(@PathVariable int companyId) {
        return repo.getResponseSchoolRequestByCompany(companyId);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getRequest/{requestId}")
    public List<CompanyResponseSchoolRequestModel> CompanyResponseSchoolRequestByRequest(@PathVariable int requestId) {
        return repo.getResponseSchoolRequestByRequest(requestId);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/approval/{accept}")
    public List<CompanyResponseSchoolRequestModel> CompanyResponseSchoolRequestByAprove(@PathVariable int accept) {
        return repo.getResponseSchoolRequestByAccept(accept);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/accept/{id}")
    public int acceptCompanyResponseSchoolRequest(@PathVariable int id) {
        return repo.acceptResponseSchoolRequest(id);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/refuse/{id}")
    public int refuseCompanyResponseSchoolRequest(@PathVariable int id) {
        return repo.refuseResponseSchoolRequest(id);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/update")
    public int updateCompanyResponseSchoolRequest(@RequestBody CompanyResponseSchoolRequestModel model) {
        return repo.updateResponseSchoolRequest(model.getResponse_id(), model.getResponsed_company_id(), model.getResponsed_request_id(),
                model.getResponsed_from(), model.getResponsed_to(), model.getResponsed_cost(), model.getIs_aproved());
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/delete/{id}")
    public int deleteCompanyResponseSchoolRequest(@PathVariable int id) {
        return repo.deleteResponseSchoolRequest(id);
    }
}
