package com.taj.controller;

import com.taj.model.RequestEnquiriesModel;
import com.taj.repository.RequestEnquiriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@RequestMapping("/enquiry")
@RestController
@CrossOrigin
public class RequestEnquiriesController {

    @Autowired
    RequestEnquiriesRepo repo;

    @PostMapping("/add")
    public int addRequestEnquiry(@RequestBody RequestEnquiriesModel model) {
        return repo.addNewEnquiry(model.getSchool_request_id(), model.getEnquiry_message());
    }

    @GetMapping("/getAll")
    public List<RequestEnquiriesModel> getRequestEnquiry() {
        return repo.getNewEnquiries();
    }

    @GetMapping("/get/{id}")
    public RequestEnquiriesModel getNewEnquiry(@PathVariable int id) {
        return repo.getNewEnquiry(id);
    }

    @GetMapping("/getReq/{id}")
    public List<RequestEnquiriesModel> getNewEnquiries(@PathVariable int id) {
        return repo.getNewEnquiries(id);
    }

    /**
     * @param model update current category
     */

    @PutMapping("/updateEnquiry")
    public void updateCategory(@RequestBody RequestEnquiriesModel model) {
        repo.updateNewEnquiry(model.getEnquiry_id(), model.getSchool_request_id(), model.getEnquiry_message());

    }

    /**
     * @param model delete current category
     */

    @PutMapping("/deleteEnquiry")
    public void deleteCategory(@RequestBody RequestEnquiriesModel model) {
        repo.deleteNewEnquiry(model.getEnquiry_id());

    }
}
