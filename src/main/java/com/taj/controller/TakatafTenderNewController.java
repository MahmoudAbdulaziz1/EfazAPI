package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.*;
import com.taj.repository.TakatafTenderNewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 8/19/2018.
 */
@RequestMapping("/takataf/tenders")
@RestController
@CrossOrigin
public class TakatafTenderNewController {

    @Autowired
    TakatafTenderNewRepo repo;
    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ObjectNode addTender(@RequestBody @Valid TakatafTenderNewModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.addTender( model.getTender_logo(), model.getTender_title(), model.getTender_explain(),
                model.getTender_display_date(), model.getTender_expire_date(), model.getTender_company_display_date(),
                model.getTender_company_expired_date(), model.getCats());

        if (res >0) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("tender_id", model.getTender_id());
            objectNode.put("tender_logo", model.getTender_logo());
            objectNode.put("tender_title", model.getTender_title());
            objectNode.put("tender_explain", model.getTender_explain());
            objectNode.put("tender_display_date", model.getTender_display_date());
            objectNode.put("tender_expire_date", model.getTender_expire_date());
            objectNode.put("tender_company_display_date", model.getTender_company_display_date());
            objectNode.put("tender_company_expire_date", model.getTender_company_expired_date());


            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

    @GetMapping("/")
    public List<TakatafTenderPOJO> getTenders() {
        return repo.getTenders();

    }

    @GetMapping("/{id}")
    public ResponseEntity<TakatafTenderPOJO> getTender(@PathVariable int id) {
        if (repo.isExist(id)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.getTender(id));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(repo.getTender(id));
        }


    }

    @GetMapping("/admin")
    public List<TakatafMyTenderPageDTO> getAdminTenders() {
        return repo.getAdminTenders();

    }


//    @GetMapping("/detail/{id}")
//    public TakatafTenderWithCompanies getTendersByCompanies(@PathVariable int id){
//        return repo.getSingleTenderDetails(id);
//    }
//
//    @PutMapping("/accept/{request_id}")
//    public ResponseEntity<ObjectNode> acceptOffer(@PathVariable int request_id) {
//        if (repo.isExist(request_id)) {
//            int response = repo.acceptOffer(request_id);
//            if (response == 1) {
//                ObjectNode objectNode = mapper.createObjectNode();
//                objectNode.put("message", "success");
//                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
//            } else {
//                ObjectNode objectNode = mapper.createObjectNode();
//                objectNode.put("message", "not sucess");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
//            }
//        } else {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("message", "no exist request");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
//        }
//
//    }
//
//    @PutMapping("/cancel/{request_id}")
//    public ResponseEntity<ObjectNode> cancelOffer(@PathVariable int request_id) {
//        if (repo.isExist(request_id)) {
//            int response = repo.cancelOffer(request_id);
//            if (response == 1) {
//                ObjectNode objectNode = mapper.createObjectNode();
//                objectNode.put("message", "success");
//                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
//            } else {
//                ObjectNode objectNode = mapper.createObjectNode();
//                objectNode.put("message", "not sucess");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
//            }
//        } else {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("message", "no exist request");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
//        }
//    }
//
//    @PutMapping("/")
//    public JsonNode updateRequestModel(@RequestBody @Valid TakatafTenderNewModel model, Errors errors) {
//        if (errors.hasErrors()) {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("state", 400);
//            objectNode.put("message", "Validation Failed");
//
//            return objectNode;
//        }
//        int res = repo.updateRequest(model.getTender_id(), model.getTender_logo(), model.getTender_title(),
//                model.getTender_explain(), model.getTender_display_date(), model.getTender_expire_date(),
//                model.getTender_company_display_date(), model.getTender_company_expired_date(), model.getTender_cat_id());
//
//        if (res == 1) {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("request_id", model.getTender_cat_id());
//            objectNode.put("request_logo", model.getTender_logo());
//            objectNode.put("request_title", model.getTender_title());
//            objectNode.put("request_explaination", model.getTender_explain());
//            objectNode.put("request_display_date", model.getTender_display_date());
//            objectNode.put("request_expired_date", model.getTender_expire_date());
//            objectNode.put("tender_company_display_date", model.getTender_company_display_date());
//            objectNode.put("tender_company_expire_date", model.getTender_company_expired_date());
//            objectNode.put("request_category_id", model.getTender_cat_id());
//
//            return objectNode;
//        } else {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("status", 400);
//            objectNode.put("message", "not success");
//
//            return objectNode;
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ObjectNode deleteSchoolRequest(@PathVariable int id) {
//        if (repo.isExist(id)) {
//            int res = repo.deleteSchoolRequest(id);
//            if (res == 1) {
//                ObjectNode objectNode = mapper.createObjectNode();
//                objectNode.put("message", "success");
//
//                return objectNode;
//            } else {
//                ObjectNode objectNode = mapper.createObjectNode();
//                objectNode.put("message", "not success");
//
//                return objectNode;
//            }
//        } else {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("message", "not element by this id");
//
//            return objectNode;
//        }
//
//    }


}
