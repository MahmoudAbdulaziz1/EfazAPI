package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.GetSchoolsRequestOffersWitCoast;
import com.taj.model.SchoolRequestOfferModel;
import com.taj.repository.SchoolRequestOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/8/2018.
 */
///evvaz
@RequestMapping("/school/request/offer")
@RestController
@CrossOrigin
public class SchoolRequestOfferController {

    @Autowired
    SchoolRequestOfferRepo repo;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/add")
    public JsonNode addSchoolRequestOffer(@Valid @RequestBody SchoolRequestOfferModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.addSchoolRequestOffer(model.getRequsted_school_id(), model.getRequsted_offer_id(), model.getIs_accepted(), model.getRequest_offer_count());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("request_id", model.getRequest_id());
            objectNode.put("requsted_school_id", model.getRequsted_school_id());
            objectNode.put("requsted_offer_id", model.getRequsted_offer_id());
            objectNode.put("request_offer_count", model.getRequest_offer_count());
            objectNode.put("is_accepted", model.getIs_accepted());
            objectNode.put("updated", 1);

            return objectNode;
        } else if (res == -1000) {

            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "can not updated");
            objectNode.put("updated", 0);
            return objectNode;

        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

    @GetMapping("/getAll")
    public List<SchoolRequestOfferModel> getSchoolRequestsOffer() {
        return repo.getSchoolRequestOffer();
    }

    @GetMapping("/get/{id}")
    public SchoolRequestOfferModel getSchoolRequestOffer(@PathVariable int id) {
        return repo.getSchoolRequestOffer(id);
    }

    @GetMapping("/getSchool/{schoolId}")
    public List<SchoolRequestOfferModel> getSchoolRequestOfferBySchool(@PathVariable int schoolId) {
        return repo.getSchoolRequestOfferBySchool(schoolId);
    }

    @GetMapping("/getOffer/{offerId}")
    public List<SchoolRequestOfferModel> SchoolRequestOfferByOffer(@PathVariable int offerId) {
        return repo.getSchoolRequestOfferByOffer(offerId);
    }

    @GetMapping("/getApproved/{accept}")
    public List<SchoolRequestOfferModel> SchoolRequestOfferByAprove(@PathVariable int accept) {
        return repo.getSchoolRequestOfferByAccept(accept);
    }

    @PutMapping("/accept/{id}")
    public int acceptSchoolRequestOffer(@PathVariable int id) {
        return repo.acceptSchoolRequestOffer(id);
    }

    @PutMapping("/refuse/{id}")
    public int refuseSchoolRequestOffer(@PathVariable int id) {
        return repo.refuseSchoolRequestOffer(id);
    }

    @PutMapping("/update")
    public ObjectNode updateSchoolRequestOffer(@Valid @RequestBody SchoolRequestOfferModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateResponseSchoolRequest(model.getRequest_id(), model.getRequsted_school_id(), model.getRequsted_offer_id(), model.getIs_accepted(), model.getRequest_offer_count());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("request_id", model.getRequest_id());
            objectNode.put("requsted_school_id", model.getRequsted_school_id());
            objectNode.put("requsted_offer_id", model.getRequsted_offer_id());
            objectNode.put("is_accepted", model.getIs_accepted());
            objectNode.put("request_offer_count", model.getRequest_offer_count());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not success");

            return objectNode;
        }

    }

    @DeleteMapping("/delete/{id}")
    public ObjectNode deleteSchoolRequestOffer(@PathVariable int id) {
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

    @GetMapping("/com/{id}")
    public GetSchoolsRequestOffersWitCoast getSchoolRequestOfferByCompany(@PathVariable int id) {
        return repo.getSchoolRequestOfferByCompany(id);
    }

}
