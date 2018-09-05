package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.SchoolSeeRequest;
import com.taj.model.getSchoolSeeRequest;
import com.taj.model.getSchoolSeeRequestById;
import com.taj.repository.SchoolSeeRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/9/2018.
 */
@RequestMapping("/offer/seen")
@RestController
@CrossOrigin
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolSeeRequestController {


    @Autowired
    SchoolSeeRequestRepo repo;
    @Autowired
    ObjectMapper mapper;


    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/add")
    public ResponseEntity<ObjectNode> addOfferSeen(@Valid @RequestBody SchoolSeeRequest model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


        if (!(repo.isExistOrganizationAndOffer(model.getSeen_offer_id(), model.getSeen_offer_school_id()))) {

            if (repo.isExistOffer(model.getSeen_offer_id()) && repo.isExistOrganization(model.getSeen_offer_school_id())) {
                int res = repo.addSeen(model.getSeen_offer_id(), model.getSeen_offer_school_id());

                if (res == 1) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 200);
                    objectNode.put("seen_offer_id", model.getSeen_offer_id());
                    objectNode.put("seen_offer_school_id", model.getSeen_offer_school_id());

                    return ResponseEntity.status(HttpStatus.OK).body(objectNode);
                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 400);
                    objectNode.put("message", "failed to add");

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                }
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "school id or offer id not found");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }

        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "school already see offer");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ResponseEntity<getSchoolSeeRequest> getOffersSeen() {

        List<SchoolSeeRequest> schools = repo.getOffersSeen();
        return ResponseEntity.status(HttpStatus.OK).body(new getSchoolSeeRequest("200", schools));

    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ResponseEntity<getSchoolSeeRequestById> getOffersSeen(@PathVariable int id) {
        if (repo.isExist(id)) {
            SchoolSeeRequest school = repo.getRequestSeen(id);
            return ResponseEntity.status(HttpStatus.OK).body(new getSchoolSeeRequestById("200", school));
        } else {
            SchoolSeeRequest school = repo.getRequestSeen(id);
            return ResponseEntity.status(HttpStatus.OK).body(new getSchoolSeeRequestById("400", school));
        }

    }

    @GetMapping("/getSchool/{schoolId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ResponseEntity<getSchoolSeeRequest> getOffersSeenBySchool(@PathVariable int schoolId) {
        if (repo.isExistOrganization(schoolId)) {
            List<SchoolSeeRequest> offers = repo.getOffersSeenBySchool(schoolId);
            return ResponseEntity.status(HttpStatus.OK).body(new getSchoolSeeRequest("200", offers));
        } else {
            List<SchoolSeeRequest> offers = repo.getOffersSeenBySchool(schoolId);
            return ResponseEntity.status(HttpStatus.OK).body(new getSchoolSeeRequest("400", offers));
        }

    }

    @GetMapping("/getOffer/{offerId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ResponseEntity<getSchoolSeeRequest> getOffersSeenByOffer(@PathVariable int offerId) {
        if (repo.isExistOffer(offerId)) {
            List<SchoolSeeRequest> schools = repo.getOffersSeenByOffer(offerId);
            return ResponseEntity.status(HttpStatus.OK).body(new getSchoolSeeRequest("200", schools));
        } else {
            List<SchoolSeeRequest> schools = repo.getOffersSeenByOffer(offerId);
            return ResponseEntity.status(HttpStatus.OK).body(new getSchoolSeeRequest("400", schools));
        }

    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ResponseEntity<ObjectNode> updateOffersSeen(@Valid @RequestBody SchoolSeeRequest model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        }
        if (repo.isExist(model.getSeen_id()) && repo.isExistOrganization(model.getSeen_offer_school_id()) && repo.isExistOffer(model.getSeen_offer_id())) {
            int res = repo.updateOffersSeen(model.getSeen_id(), model.getSeen_offer_id(), model.getSeen_offer_school_id());
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("seen_id", model.getSeen_id());
                objectNode.put("seen_offer_id", model.getSeen_offer_id());
                objectNode.put("seen_offer_school_id", model.getSeen_offer_school_id());

                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {

            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "no item with this id");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }


    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ResponseEntity<ObjectNode> deleteOffersSeen(@PathVariable int id) {
        if (repo.isExist(id)) {
            int res = repo.deleteOffersSeen(id);
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                objectNode.put("message", "success");

                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "no item  found bu this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }

    }
}
