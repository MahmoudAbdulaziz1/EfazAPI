package com.taj.controller;

import com.taj.model.RequestEnquiryResponseModel;
import com.taj.repository.RequestEnquiryResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    @PostMapping("/add")
    public int addRequestEnquiry(@RequestBody RequestEnquiryResponseModel model) {
        return repo.addNewResponse(model.getRequest_enquiry_id(), model.getResponse_message());
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
    public void updateCategory(@RequestBody RequestEnquiryResponseModel model) {
        repo.updateNewResponse(model.getResponse_id(), model.getRequest_enquiry_id(), model.getResponse_message());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deleteEnquiry")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public void deleteCategory(@RequestBody RequestEnquiryResponseModel model) {
        repo.deleteNewResponse(model.getResponse_id());

    }

}
