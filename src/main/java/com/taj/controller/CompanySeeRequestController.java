package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CompanySeeRequestModel;
import com.taj.repository.CompanySeeRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/add")
    public ObjectNode addCompanySeeRequest(@Valid @RequestBody CompanySeeRequestModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return objectNode;
        }
        if (repo.isExist(model.getRequest_company_id())) {

            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Already is Exist");
            return objectNode;

        } else {
            if (repo.isExistCompany(model.getRequest_company_id())) {
                if (repo.isExistRequest(model.getRequest_id())) {
                    int res = repo.addCompanySeeRequest(model.getRequest_id(), model.getRequest_company_id());

                    if (res == 1) {
                        ObjectNode objectNode = mapper.createObjectNode();
                        objectNode.put("request_id", model.getRequest_id());
                        objectNode.put("request_company_id", model.getRequest_company_id());

                        return objectNode;
                    } else {
                        ObjectNode objectNode = mapper.createObjectNode();
                        objectNode.put("value", "not success");

                        return objectNode;
                    }
                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("state", 400);
                    objectNode.put("message", "request id not found");
                    return objectNode;
                }
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 400);
                objectNode.put("message", "company id not found");
                return objectNode;
            }


        }


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
    public ObjectNode updateCategory(@Valid @RequestBody CompanySeeRequestModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }

        int res = repo.updateCompanySeeRequest(model.getSeen_id(), model.getRequest_company_id(), model.getRequest_id());

        if (res == 0) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success" + res);

            return objectNode;

        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("seen_id", model.getSeen_id());
            objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_company_id", model.getRequest_company_id());

            return objectNode;
        }

    }

    /**
     * @param model delete current category
     */

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/delete")
    public ObjectNode deleteCategory(@Valid @RequestBody CompanySeeRequestModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.deleteCompanySeeRequest(model.getSeen_id());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

}
