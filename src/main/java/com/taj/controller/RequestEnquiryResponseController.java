package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.RequestEnquiryResponseModel;
import com.taj.repository.RequestEnquiryResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@RequestMapping("/evvaz/enquiry/response")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RequestEnquiryResponseController {


    @Autowired
    RequestEnquiryResponseRepo repo;
    @Autowired
    ObjectMapper mapper;

    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    @PostMapping("/add")
    public ObjectNode addRequestEnquiry(@Valid @RequestBody RequestEnquiryResponseModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.addNewResponse(model.getRequest_enquiry_id(), model.getResponse_message());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("enquiry_id", model.getEnquiry_id());
            objectNode.put("request_enquiry_id", model.getRequest_enquiry_id());
            objectNode.put("response_message", model.getResponse_message());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<RequestEnquiryResponseModel> getRequestEnquiry() {
        return repo.getNewResponses();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public RequestEnquiryResponseModel getNewEnquiry(@PathVariable int id) {
        return repo.getNewResponse(id);
    }

    @GetMapping("/getReq/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<RequestEnquiryResponseModel> getNewEnquiries(@PathVariable int id) {
        return repo.getNewResponses(id);
    }

    /**
     * @param model update current category
     */

    @PutMapping("/updateEnquiry")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode updateCategory(@Valid @RequestBody RequestEnquiryResponseModel model, Errors errors) {


        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateNewResponse(model.getResponse_id(), model.getRequest_enquiry_id(), model.getResponse_message());


        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("response_id", model.getResponse_id());
        objectNode.put("request_enquiry_id", model.getRequest_enquiry_id());
        objectNode.put("response_message", model.getResponse_message());

        return objectNode;


    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deleteEnquiry")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode deleteCategory(@Valid @RequestBody RequestEnquiryResponseModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.deleteNewResponse(model.getResponse_id());

        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("response_id", model.getResponse_id());
        objectNode.put("request_enquiry_id", model.getRequest_enquiry_id());
        objectNode.put("response_message", model.getResponse_message());

        return objectNode;

    }

}
