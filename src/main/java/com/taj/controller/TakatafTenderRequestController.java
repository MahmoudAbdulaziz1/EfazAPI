package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.TakatafSchoolSeeTenderModel;
import com.taj.model.TakatafTenderRequestModel;
import com.taj.repository.TakatafSchoolSeeTenderRepo;
import com.taj.repository.TakatafTenderRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
@RequestMapping("/evvaz/tender/request")
@RestController
@CrossOrigin
public class TakatafTenderRequestController {

    @Autowired
    TakatafTenderRequestRepo repo;
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode addTenderRequest(@Valid @RequestBody TakatafTenderRequestModel model, Errors errors){

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.add$Request(model.getRequest_school_id(), model.getRequest_tender_id(), model.getIs_aproved());
        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_school_id", model.getRequest_school_id());
            objectNode.put("request_tender_id", model.getRequest_tender_id());
            objectNode.put("is_aproved", model.getIs_aproved());

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderRequestModel> getTendersSeen(){
        return repo.getTenderRequests();
    }
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public TakatafTenderRequestModel getTenderRequests(@PathVariable int id){
        return repo.getTenderRequest(id);
    }

    @GetMapping("/getSchool/{schoolId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderRequestModel> getTenderRequestsBySchool(@PathVariable int schoolId){
        return repo.getTenderRequestsBySchool(schoolId);
    }

    @GetMapping("/getTender/{tenderId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderRequestModel> getTenderRequestsByTender(@PathVariable int tenderId){
        return repo.getTenderRequestsByTender(tenderId);
    }

    @GetMapping("/getApproval/{aproveId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<TakatafTenderRequestModel> getTenderRequestsByAprove(@PathVariable int aproveId){
        return repo.getTenderRequestsByAprove(aproveId);
    }

    @PutMapping("/accept/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int acceptTenderRequest(@PathVariable int id){
        return repo.acceptTenderRequest(id);
    }
    @PutMapping("/refuse/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int refuseTenderRequest(@PathVariable int id){
        return repo.refuseTenderRequest(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode updateTenderRequest(@Valid @RequestBody TakatafTenderRequestModel model, Errors errors){
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateTenderRequest(model.getRequest_id(), model.getRequest_school_id(), model.getRequest_tender_id(), model.getIs_aproved());
        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_school_id", model.getRequest_school_id());
            objectNode.put("request_tender_id", model.getRequest_tender_id());
            objectNode.put("is_aproved", model.getIs_aproved());

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }
    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode deleteTenderRequest(@PathVariable int id){
        int res = repo.deleteTenderRequest(id);
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
