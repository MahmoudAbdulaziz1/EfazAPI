package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CompanyResponseSchoolRequestModel;
import com.taj.repository.CompanyResponseSchoolRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    ObjectMapper mapper;

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/add")
    public ObjectNode addCompanyResponseSchoolRequest(@Valid @RequestBody CompanyResponseSchoolRequestModel model, Errors errors) {
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.addResponseSchoolRequest( model.getResponsed_company_id(), model.getResponsed_request_id(), model.getResponsed_from(),
                model.getResponsed_to(), model.getResponsed_cost(), model.getIs_aproved());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("response_id", model.getResponse_id());
            objectNode.put("responsed_company_id", model.getResponsed_company_id());
            objectNode.put("responsed_request_id", model.getResponsed_request_id());
            objectNode.put("responsed_from", model.getResponsed_from());
            objectNode.put("responsed_from", model.getResponsed_to());
            objectNode.put("responsed_cost", model.getResponsed_cost());
            objectNode.put("is_aproved", model.getIs_aproved());

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

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
    public ObjectNode updateCompanyResponseSchoolRequest(@Valid @RequestBody CompanyResponseSchoolRequestModel model, Errors errors) {
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateResponseSchoolRequest(model.getResponse_id(), model.getResponsed_company_id(), model.getResponsed_request_id(),
                model.getResponsed_from(), model.getResponsed_to(), model.getResponsed_cost(), model.getIs_aproved());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("response_id", model.getResponse_id());
            objectNode.put("responsed_company_id", model.getResponsed_company_id());
            objectNode.put("responsed_request_id", model.getResponsed_request_id());
            objectNode.put("responsed_from", model.getResponsed_from());
            objectNode.put("responsed_from", model.getResponsed_to());
            objectNode.put("responsed_cost", model.getResponsed_cost());
            objectNode.put("is_aproved", model.getIs_aproved());

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/delete/{id}")
    public ObjectNode deleteCompanyResponseSchoolRequest(@PathVariable int id) {
        int res =  repo.deleteResponseSchoolRequest(id);

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }
}
