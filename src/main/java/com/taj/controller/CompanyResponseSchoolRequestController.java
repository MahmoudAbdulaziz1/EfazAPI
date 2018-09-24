package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CompanyResponseSchoolRequestModel;
import com.taj.repository.CompanyResponseSchoolRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@RequestMapping("/response/school/request")
@RestController
@CrossOrigin
public class CompanyResponseSchoolRequestController {

    @Autowired
    CompanyResponseSchoolRequestRepo repo;
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/add")
    public ObjectNode addCompanyResponseSchoolRequest(@Valid @RequestBody CompanyResponseSchoolRequestModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.addResponseSchoolRequest(model.getResponsed_company_id(), model.getResponsed_request_id(), model.getResponsed_from(),
                model.getResponsed_to(), model.getResponsed_cost(), model.getIs_aproved());

        if (res == 200) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("response_id", model.getResponse_id());
            objectNode.put("responsed_company_id", model.getResponsed_company_id());
            objectNode.put("responsed_request_id", model.getResponsed_request_id());
            objectNode.put("responsed_from", model.getResponsed_from());
            objectNode.put("responsed_from", model.getResponsed_to());
            objectNode.put("responsed_cost", model.getResponsed_cost());
            objectNode.put("is_aproved", model.getIs_aproved());
            objectNode.put("response_date", timestamp.getTime());
            return objectNode;
        } else if (res == 100) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("response_id", model.getResponse_id());
            objectNode.put("responsed_company_id", model.getResponsed_company_id());
            objectNode.put("responsed_request_id", model.getResponsed_request_id());
            objectNode.put("responsed_from", model.getResponsed_from());
            objectNode.put("responsed_from", model.getResponsed_to());
            objectNode.put("responsed_cost", model.getResponsed_cost());
            objectNode.put("is_aproved", model.getIs_aproved());
            objectNode.put("updated", 1);
            return objectNode;
        } else if (res == -100) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "cant not updated");
            objectNode.put("updated", 0);
            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "fail");
            return objectNode;
        }

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

    @GetMapping("/approval/{accept}")
    public List<CompanyResponseSchoolRequestModel> CompanyResponseSchoolRequestByAprove(@PathVariable int accept) {
        return repo.getResponseSchoolRequestByAccept(accept);
    }

    @PutMapping("/accept/{id}")
    public JsonNode acceptCompanyResponseSchoolRequest(@PathVariable int id) {

        int res = repo.acceptResponseSchoolRequest(id);
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 200);
            objectNode.put("message", "accepted");
            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "failed");
            return objectNode;

        }
    }

    @PutMapping("/refuse/{id}")
    public JsonNode refuseCompanyResponseSchoolRequest(@PathVariable int id) {
        int res = repo.refuseResponseSchoolRequest(id);
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 200);
            objectNode.put("message", "accepted");
            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "failed");
            return objectNode;

        }
    }

    @PutMapping("/update")
    public ObjectNode updateCompanyResponseSchoolRequest(@Valid @RequestBody CompanyResponseSchoolRequestModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateResponseSchoolRequest(model.getResponse_id(), model.getResponsed_company_id(), model.getResponsed_request_id(),
                model.getResponsed_from(), model.getResponsed_to(), model.getResponsed_cost(), model.getIs_aproved());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("response_id", model.getResponse_id());
            objectNode.put("responsed_company_id", model.getResponsed_company_id());
            objectNode.put("responsed_request_id", model.getResponsed_request_id());
            objectNode.put("responsed_from", model.getResponsed_from());
            objectNode.put("responsed_from", model.getResponsed_to());
            objectNode.put("responsed_cost", model.getResponsed_cost());
            objectNode.put("is_aproved", model.getIs_aproved());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ObjectNode deleteCompanyResponseSchoolRequest(@PathVariable int id) {
        int res = repo.deleteResponseSchoolRequest(id);

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
