package com.taj.controller;

import com.taj.model.RequestEnquiriesModel;
import com.taj.repository.RequestEnquiriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@RequestMapping("/evvaz/enquiry")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RequestEnquiriesController {

    @Autowired
    RequestEnquiriesRepo repo;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public int addRequestEnquiry(@RequestBody RequestEnquiriesModel model) {
        return repo.addNewEnquiry(model.getSchool_request_id(), model.getEnquiry_message());
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<RequestEnquiriesModel> getRequestEnquiry() {
        return repo.getNewEnquiries();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public RequestEnquiriesModel getNewEnquiry(@PathVariable int id) {
        return repo.getNewEnquiry(id);
    }

    @GetMapping("/getReq/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<RequestEnquiriesModel> getNewEnquiries(@PathVariable int id) {
        return repo.getNewEnquiries(id);
    }

    /**
     * @param model update current category
     */

    @PutMapping("/updateEnquiry")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public int updateCategory(@RequestBody RequestEnquiriesModel model) {
        return repo.updateNewEnquiry(model.getEnquiry_id(), model.getSchool_request_id(), model.getEnquiry_message());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deleteEnquiry")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public int deleteCategory(@RequestBody RequestEnquiriesModel model) {
        return repo.deleteNewEnquiry(model.getEnquiry_id());

    }
}
