package com.taj.controller;

import com.taj.model.RequestEnquiriesModel;
import com.taj.model.RequestEnquiryResponseModel;
import com.taj.repository.RequestEnquiriesRepo;
import com.taj.repository.RequestEnquiryResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/3/2018.
 */
@RequestMapping("/response")
@RestController
@CrossOrigin
public class RequestEnquiryResponseController {


    @Autowired
    RequestEnquiryResponseRepo repo;

    @PostMapping("/add")
    public int addRequestEnquiry(@RequestBody RequestEnquiryResponseModel model){
        return repo.addNewResponse(model.getRequest_enquiry_id(), model.getResponse_message());
    }

    @GetMapping("/getAll")
    public List<RequestEnquiryResponseModel> getRequestEnquiry(){
        return repo.getNewResponses();
    }

    @GetMapping("/get/{id}")
    public RequestEnquiryResponseModel getNewEnquiry(@PathVariable int id) {
        return repo.getNewResponse(id);
    }

    @GetMapping("/getReq/{id}")
    public List<RequestEnquiryResponseModel> getNewEnquiries(@PathVariable int id) {
        return repo.getNewResponses(id);
    }

    /**
     *
     * @param model
     * update current category
     */

    @PutMapping("/updateEnquiry")
    public void updateCategory(@RequestBody RequestEnquiryResponseModel model){
        repo.updateNewResponse(model.getResponse_id(), model.getRequest_enquiry_id(), model.getResponse_message());

    }

    /**
     *
     * @param model
     *
     * delete current category
     */

    @PutMapping("/deleteEnquiry")
    public void deleteCategory(@RequestBody RequestEnquiryResponseModel model){
        repo.deleteNewResponse(model.getResponse_id());

    }

}
