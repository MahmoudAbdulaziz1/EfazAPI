package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.SchoolSeeRequest;
import com.taj.model.TakatafSchoolSeeTenderModel;
import com.taj.repository.SchoolSeeRequestRepo;
import com.taj.repository.TakatafSchoolSeeTenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
@RequestMapping("/evvaz/offer/seen")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolSeeRequestController {


    @Autowired
    SchoolSeeRequestRepo repo;
    @Autowired
    ObjectMapper mapper;


    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/add")
    public ObjectNode addOfferSeen(@Valid @RequestBody SchoolSeeRequest model, Errors errors){
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res =  repo.addSeen(model.getSeen_offer_id(), model.getSeen_offer_school_id());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("seen_offer_id", model.getSeen_offer_id());
            objectNode.put("seen_offer_school_id", model.getSeen_offer_school_id());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolSeeRequest> getOffersSeen(){
        return repo.getOffersSeen();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public SchoolSeeRequest getOffersSeen(@PathVariable int id){
        return repo.getRequestSeen(id);
    }

    @GetMapping("/getSchool/{schoolId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolSeeRequest> getOffersSeenBySchool(@PathVariable int schoolId){
        return repo.getOffersSeenBySchool(schoolId);
    }

    @GetMapping("/getOffer/{offerId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolSeeRequest> getOffersSeenByOffer(@PathVariable int offerId){
        return repo.getOffersSeenByOffer(offerId);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode updateOffersSeen(@Valid @RequestBody SchoolSeeRequest model, Errors errors){
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateOffersSeen(model.getSeen_id(), model.getSeen_offer_id(), model.getSeen_offer_school_id());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("seen_id", model.getSeen_id());
            objectNode.put("seen_offer_id", model.getSeen_offer_id());
            objectNode.put("seen_offer_school_id", model.getSeen_offer_school_id());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }
    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode deleteOffersSeen(@PathVariable int id){
        int res = repo.deleteOffersSeen(id);
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
